package com.db.geometry.tasksCreation;

import lombok.Setter;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class RectangleDrawer {
    @Setter
    private int padding = 35;
    private Graphics2D graphic;

    private Random random = new Random();

    public RectangleDrawer(Graphics2D graphic) {
        this.graphic = graphic;
        graphic.setColor(Color.BLACK);
        this.graphic = graphic;
    }

    public void drawRectangle(int a, int b, int sizeOfSheet) {

        int absoluteDist = sizeOfSheet - padding*2;
        int aSide = max(a, b);
        int bSide = min(a, b);

        b = absoluteDist / aSide * bSide;
        a = absoluteDist;

        int shiftRight = sizeOfSheet/2 - a/2;
        int shiftBottom = sizeOfSheet/2 - b/2;

        graphic.draw(new Rectangle2D.Double(shiftRight, shiftBottom,
                a,
                b));
    }

    public void drawRectangleArea(int a, int b, int sizeOfSheet) {
        drawRectangle(a, b, sizeOfSheet);
    }

    public void drawRectangleSideFromArea(int a, int b, int sizeOfSheet) {
        drawRectangle(a, b, sizeOfSheet);
    }



}
