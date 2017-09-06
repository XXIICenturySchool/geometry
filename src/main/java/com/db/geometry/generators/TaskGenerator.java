package com.db.geometry.generators;

import com.db.geometry.tasks.Task;
import com.db.geometry.tasks.TaskInfo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class TaskGenerator {
    private Random rng = new Random();

    public List<Task> generate(List<TaskInfo> taskInfoList) {
        List<Task> tasks = new ArrayList<>();
        taskInfoList.forEach(taskInfo -> {

        });
        return tasks;
    }
}
