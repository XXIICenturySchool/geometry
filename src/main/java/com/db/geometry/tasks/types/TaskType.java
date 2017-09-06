package com.db.geometry.tasks.types;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public abstract class TaskType {
    protected final String type;
    protected final List<String> checkboxes;
    protected final List<String> variables;
}
