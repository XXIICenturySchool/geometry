package com.db.geometry.tasks.types;

import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class PythagorasTaskType extends TaskType {

    private PythagorasTaskType() {
        super("Pythagoras", Arrays.asList("canAnswerBeFloat"), Arrays.asList("a", "b", "c"));
    }
}
