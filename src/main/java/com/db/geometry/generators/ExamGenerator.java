package com.db.geometry.generators;

import com.db.geometry.Exam;
import com.db.geometry.tasks.Task;
import com.db.geometry.tasks.TaskInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExamGenerator {
    private Random rng = new Random();

    private static Map<String, TaskGenerator> taskGenerators;

    @Autowired
    void setTaskGenerators(TaskGenerator[] generators) {
        taskGenerators = new HashMap<>();
        for (TaskGenerator generator : generators) {
            taskGenerators.put(
                    generator.getClass().getAnnotation(TaskName.class).value(),
                    generator
                    );
        }
    }


    public Exam generate(List<TaskInfo> taskInfoList, String examId) {
        List<Task> tasks = new ArrayList<>();
        taskInfoList.forEach(taskInfo -> tasks.addAll(ExamGenerator.generateByInfo(taskInfo, examId)));
        return new Exam(examId, tasks);
    }

    private static List<Task> generateByInfo(TaskInfo taskInfo, String examId) {
        List<Task> tasks = new ArrayList<>();
        for (int i = 0; i < taskInfo.getAmount(); i++) {
            Task.TaskBuilder taskBuilder = Task.builder();
            TaskGenerator taskGenerator = taskGenerators.get(taskInfo.getType());

            if (taskGenerator == null) {
                throw new RuntimeException("Cannot find task generator for task " + taskInfo.getType());
            }

            tasks.add(taskGenerator.generateTask(taskInfo, examId, i + 1));

        }
        return tasks;
    }
}
