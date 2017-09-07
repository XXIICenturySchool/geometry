package com.db.geometry.generators;

import com.db.geometry.tasks.Task;
import com.db.geometry.tasks.TaskInfo;

public interface TaskGenerator {
    Task generateTask(TaskInfo taskInfo, String examId, int taskNum);
}
