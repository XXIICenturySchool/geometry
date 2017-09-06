package com.db.geometry.tasks;

import lombok.Data;

@Data
public class Constraint {
    private final int lower;
    private final int upper;
    private String variable;
}
