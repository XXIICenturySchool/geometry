package com.db.geometry.tasksCreation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Data
public class Sign {

    private Point position;
    private String message;

}
