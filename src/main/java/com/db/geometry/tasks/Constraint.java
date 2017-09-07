package com.db.geometry.tasks;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Constraint {
    private int lower;
    private int upper;
    private String variable;
}
