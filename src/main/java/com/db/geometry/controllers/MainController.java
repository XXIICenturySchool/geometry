package com.db.geometry.controllers;

import com.db.geometry.Exam;
import com.db.geometry.ExamDao;
import com.db.geometry.taskGenerators.ExamGenerator;
import com.db.geometry.tasks.TaskInfo;
import com.db.geometry.tasks.types.TaskType;
import com.db.geometry.drawers.TriangularDrawer;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

@RestController
public class MainController {

    @SneakyThrows
    public String test(@RequestBody String json) {

        TriangularDrawer triangularDrawer = new TriangularDrawer();

        BufferedImage bi = triangularDrawer.createOnCathetus(5, 5);
        ImageIO.write(bi, "png", new File("src/main/resources/static/dinam/2.png"));
        ImageIO.write(bi, "gif", new File("src/main/resources/static/dinam/4.gif"));

        System.out.println(json);
        return "success";
    }

    @Autowired
    private ExamDao dao;

    @Autowired
    private ExamGenerator examGenerator;

    @Autowired
    private List<TaskType> tasksTypes;

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
        Exam exam = examGenerator.generate(taskInfoList, id);

        dao.save(exam);
        return id;
    }
}
