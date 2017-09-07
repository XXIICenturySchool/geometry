package com.db.geometry.drawers;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Sign {

    private Point position;
    private String message;

}
