package com.db.geometry.taskGenerators;

import com.db.geometry.tasks.Task;
import com.db.geometry.tasks.TaskInfo;
import org.springframework.stereotype.Component;

public interface TaskGenerator {
    Task generateTask(TaskInfo taskInfo);
}
