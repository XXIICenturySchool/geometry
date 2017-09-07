package com.db.geometry.tasks.types;

import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class FindCatheterTaskType extends TaskType {

    private FindCatheterTaskType() {
        super("FindCatheters", Arrays.asList("canAnswerBeFloat"), Arrays.asList("катет", "гипотенуза"));
    }
}
