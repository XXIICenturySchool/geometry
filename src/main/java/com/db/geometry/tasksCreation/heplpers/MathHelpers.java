package com.db.geometry.tasksCreation.heplpers;

public class MathHelpers {


    static public double sinDegrees(int degrees) {
        return Math.sin(Math.toRadians(degrees));
    }

    static public double cosDegrees(int degrees) {
        return Math.cos(Math.toRadians(degrees));
    }

    static public double getHypot(int cathet1, int cathet2) {
        return Math.sqrt(cathet1*cathet1 + cathet2*cathet2);
    }

    static public double getCathet(int cathet, int hypot) {
        return Math.sqrt(hypot*hypot + cathet*cathet);
    }

    private MathHelpers() {}
}
