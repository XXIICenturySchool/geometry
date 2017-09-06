package com.db.geometry.taskGenerators;

import com.db.geometry.services.RandomService;
import com.db.geometry.tasks.Task;
import com.db.geometry.tasks.TaskInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@TaskName("Pythagoras")
public class PythagorasTaskGenerator implements TaskGenerator {

    @Autowired
    RandomService randomService;

    @Override
    public Task generateTask(TaskInfo taskInfo) {
        Task.TaskBuilder taskBuilder = Task.builder();

//        taskBuilder.

        return taskBuilder.build();
    }
}
