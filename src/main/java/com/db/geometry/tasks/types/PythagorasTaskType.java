package com.db.geometry.tasks.types;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;

@Component
public class PythagorasTaskType extends TaskType {

    private PythagorasTaskType() {
        this.type = "Pythagoras";

        this.checkboxes = new ArrayList<>();
        this.checkboxes.add("canAnswerBeFloat");

        this.variables = new ArrayList<>();
        String[] variables = {"a", "b", "c"};
        this.variables.addAll(Arrays.asList(variables));
    }
}
