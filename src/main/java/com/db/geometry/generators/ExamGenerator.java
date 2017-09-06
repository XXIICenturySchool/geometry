package com.db.geometry.generators;

import com.db.geometry.Exam;
import com.db.geometry.tasks.Task;
import com.db.geometry.tasks.TaskInfo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ExamGenerator {
    private Random rng = new Random();

    public Exam generate(List<TaskInfo> taskInfoList) {
        List<Task> tasks = new ArrayList<>();
        taskInfoList.forEach(taskInfo -> tasks.addAll(ExamGenerator.generateByInfo(taskInfo)));
        return new Exam(tasks);
    }

    private static List<Task> generateByInfo(TaskInfo taskInfo) {
        List<Task> tasks = new ArrayList<>();
        for (int i = 0; i < taskInfo.getAmount(); i++) {
            //TODO
        }
        return tasks;
    }
}
