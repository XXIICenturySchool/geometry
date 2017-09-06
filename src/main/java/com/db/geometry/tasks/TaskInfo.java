package com.db.geometry.tasks;

import lombok.Data;

import java.util.List;

@Data
public class TaskInfo {
    private final String type;
    private final int amount;
    private final List<Checkbox> checkboxes;
    private final List<Constraint> constraints;
}
