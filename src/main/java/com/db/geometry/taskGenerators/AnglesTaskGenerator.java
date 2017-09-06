package com.db.geometry.taskGenerators;

import com.db.geometry.tasks.Task;
import com.db.geometry.tasks.TaskInfo;
import org.springframework.stereotype.Component;

@Component
@TaskName("Angles")
public class AnglesTaskGenerator implements TaskGenerator {
    @Override
    public Task generateTask(TaskInfo taskInfo) {
        return null;
    }
}
