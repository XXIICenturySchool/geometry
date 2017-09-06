package com.db.geometry;

import com.db.geometry.generators.ExamGenerator;
import com.db.geometry.tasks.TaskInfo;
import com.db.geometry.tasks.types.TaskType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MainController {
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
        Exam exam = examGenerator.generate(taskInfoList);
        dao.insert(exam);
        return exam.getId();
    }
}
