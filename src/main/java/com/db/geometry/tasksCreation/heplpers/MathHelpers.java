package com.db.geometry.tasksCreation.heplpers;

public class MathHelpers {


    static public double sinDegrees(int degrees) {
        return Math.sin(Math.toRadians(degrees));
    }

    static public double cosDegrees(int degrees) {
        return Math.cos(Math.toRadians(degrees));
    }

    private MathHelpers() {}
}
