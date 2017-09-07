package com.db.geometry.tasks.types;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class FindHypotenuseType extends TaskType {
    public FindHypotenuseType() {
        super("FindHypotenuse", Arrays.asList("canAnswerBeFloat"), Arrays.asList("1-ый катет", "2-ой катет"));
    }
}
