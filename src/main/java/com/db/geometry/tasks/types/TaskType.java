package com.db.geometry.tasks.types;

import lombok.Getter;

import java.util.List;

@Getter
public abstract class TaskType {
    protected String type;
    protected List<String> checkboxes;
    protected List<String> variables;
}
