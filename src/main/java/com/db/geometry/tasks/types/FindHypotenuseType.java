package com.db.geometry.tasks.types;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class FindHypotenuseType extends TaskType {
    public FindHypotenuseType() {
        super("FindHypotenuse", Arrays.asList(), Arrays.asList("catheter", "catheter"));
    }
}
