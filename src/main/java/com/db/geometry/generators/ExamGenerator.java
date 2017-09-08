package com.db.geometry.generators;

import com.db.geometry.exam.Exam;
import com.db.geometry.services.RandomService;
import com.db.geometry.tasks.Task;
import com.db.geometry.tasks.TaskInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExamGenerator {

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

    public Exam generate(List<TaskInfo> taskInfoList, int teacherId, String examId) {
        List<Task> tasks = new ArrayList<>();
        int countTasks = 0;
        for (TaskInfo taskInfo: taskInfoList) {
            tasks.addAll(ExamGenerator.generateByInfo(taskInfo, examId, countTasks));
            countTasks += taskInfo.getAmount();
        }
        return new Exam(examId, teacherId, tasks);
    }

    private static List<Task> generateByInfo(TaskInfo taskInfo, String examId, int startingId) {
        List<Task> tasks = new ArrayList<>();
        for (int i = 0; i < taskInfo.getAmount(); i++) {
            TaskGenerator taskGenerator = taskGenerators.get(taskInfo.getType());

            if (taskGenerator == null) {
                throw new RuntimeException("Cannot find task generator for task " + taskInfo.getType());
            }
            tasks.add(taskGenerator.generateTask(taskInfo, examId, startingId + i + 1));
        }
        return tasks;
    }
}
