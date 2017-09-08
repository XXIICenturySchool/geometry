package com.db.geometry.tasks.types;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class RectangleFindSideTaskType extends TaskType {
    public RectangleFindSideTaskType() {
        super("RectangleFindSide", Arrays.asList(), Arrays.asList("side", "side"));
    }
}