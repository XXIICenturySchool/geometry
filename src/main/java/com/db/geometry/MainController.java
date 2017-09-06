package com.db.geometry;

import com.db.geometry.generators.TaskGenerator;
import com.db.geometry.tasks.Task;
import com.db.geometry.tasks.TaskInfo;
import com.db.geometry.tasks.types.TaskType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MainController {
    @Autowired
    private TaskGenerator taskGenerator;

    @Autowired
    private List<TaskType> tasksTypes;

    @GetMapping("types")
    public List<TaskType> getTaskTypes() {
        return tasksTypes;
    }

    @PostMapping("/newexam")
    public List<Task> test(@RequestBody List<TaskInfo> taskInfoList) {
        return taskGenerator.generate(taskInfoList);
    }
}
