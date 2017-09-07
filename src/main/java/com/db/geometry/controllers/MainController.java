package com.db.geometry.controllers;

import com.db.geometry.Exam;
import com.db.geometry.ExamDao;
import com.db.geometry.ExamInfo;
import com.db.geometry.generators.ExamGenerator;
import com.db.geometry.services.Services;
import com.db.geometry.tasks.TaskInfo;
import com.db.geometry.tasks.types.TaskType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;

@RestController
public class MainController {

    @Autowired
    private ExamDao dao;

    @Autowired
    private ExamGenerator examGenerator;

    @Autowired
    private List<TaskType> tasksTypes;

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("types")
    public List<TaskType> getTaskTypes() {
        return tasksTypes;
    }

    @GetMapping("/id/{id}")
    public Exam getExamById(@PathVariable String id) {
        return dao.findById(id);
    }

    @GetMapping("exams")
    public List<Exam> getAllExams() {
        return dao.findAll();
    }

    @PostMapping("/newexam")
    public String addExam(@RequestBody List<TaskInfo> taskInfoList) {
        Exam tempExam = new Exam();
        dao.insert(tempExam);
        String id = tempExam.getId();
        Exam exam;
        try {
            exam = examGenerator.generate(taskInfoList, id);
        } catch (RuntimeException exception) {
            return exception.getMessage();
        }
        dao.save(exam);
        return saveExam(new ExamInfo(exam));
    }

    @SneakyThrows
    private String saveExam(ExamInfo examInfo) {
        ServiceInstance serviceInstance = Services.MAPLOGIN.pickRandomInstance(discoveryClient);
        System.out.println("URI "+serviceInstance.getUri());
        URL url = serviceInstance.getUri().toURL();
        url = new URL(url.toString()+"/exams/saveExam");
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonExam = objectMapper.writeValueAsString(examInfo);

        HttpEntity<String> entity = new HttpEntity<>(jsonExam, headers);
        ResponseEntity<String> loginResponse = restTemplate.exchange(url.toString(), HttpMethod.POST, entity, String.class);

        if (loginResponse.getStatusCode() == HttpStatus.OK) {
            return "Your exam was successfully saved with id=" + loginResponse.getBody();
        } else {
            return "An error occurred while saving your exam";
        }
    }
}
