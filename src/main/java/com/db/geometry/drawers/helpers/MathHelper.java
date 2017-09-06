package com.db.geometry.drawers.helpers;

public class MathHelper {


    static public double sinDegrees(int degrees) {
        return Math.sin(Math.toRadians(degrees));
    }

    static public double cosDegrees(int degrees) {
        return Math.cos(Math.toRadians(degrees));
    }

    private MathHelper() {}
}
