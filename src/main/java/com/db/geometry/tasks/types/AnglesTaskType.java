package com.db.geometry.tasks.types;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class AnglesTaskType extends TaskType {

    private AnglesTaskType() {
        super("Angles", new ArrayList<>(), new ArrayList<>());
    }
}