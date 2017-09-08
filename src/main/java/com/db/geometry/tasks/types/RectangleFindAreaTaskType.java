package com.db.geometry.tasks.types;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class RectangleFindAreaTaskType extends TaskType {
    public RectangleFindAreaTaskType() {
        super("RectangleFindArea", Arrays.asList(), Arrays.asList("side", "side"));
    }
}
