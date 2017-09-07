package com.db.geometry.tasks.types;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class RectangleTaskType extends TaskType {
    public RectangleTaskType() {
        super("Rectangle", Arrays.asList("canAnswerBeFloat"), Arrays.asList("a", "b"));
    }
}
