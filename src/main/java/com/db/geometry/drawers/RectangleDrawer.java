package com.db.geometry.drawers;

import lombok.Setter;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Math.*;

public class RectangleDrawer {
    @Setter
    private int padding = 40;
    private Graphics2D graphic;
    private int sizeOfSheet = 360;
    private BufferedImage bi;

    private Random random = new Random();

    public RectangleDrawer() {
        bi = new BufferedImage(sizeOfSheet, sizeOfSheet, BufferedImage.TYPE_INT_ARGB);
        this.graphic = bi.createGraphics();
        graphic.drawImage(bi,0, 0, Color.WHITE, null);
        graphic.setColor(Color.BLACK);
        graphic.setStroke(new BasicStroke(3));
    }

    private BufferedImage drawRectangle(int a, int b, List<Sign> signList) {

        int absoluteDist = sizeOfSheet - padding*2;
        int aSide = max(a, b);
        int bSide = min(a, b);

        b = absoluteDist / aSide * bSide;
        a = absoluteDist;

        int shiftRight = sizeOfSheet/2 - a/2;
        int shiftBottom = sizeOfSheet/2 - b/2;

        graphic.setPaint(new Color(227, 243, 255));
//        g2.fill(new Rectangle2D.Double(x, y, 200, 200));

        graphic.fill(new Rectangle2D.Double(shiftRight, shiftBottom,
                a,
                b
        ));
        graphic.setPaint(Color.black);
        graphic.draw(new Rectangle2D.Double(shiftRight, shiftBottom,
                a,
                b
        ));

        drawSigns(signList, 0, 0);

        return bi;
    }

    private void drawSigns(List<Sign> signs, int shiftRight, int shiftBottom) {
        Font font = new Font("TimesRoman", Font.BOLD , 27);

        graphic.setFont(font);
        graphic.setPaint(Color.BLUE);
        for (Sign sign : signs) {
            Point position = sign.getPosition();
            graphic.drawString(sign.getMessage(), position.getX() + shiftRight, position.getY() + shiftBottom);
        }
    }

    public BufferedImage drawRectangleArea(int a, int b) {
        int absoluteDist = sizeOfSheet - padding*2;
        int aSide = max(a, b);
        int bSide = min(a, b);

        b = absoluteDist / aSide * bSide;
        a = absoluteDist;

        Point aPoint = new Point(sizeOfSheet/2, sizeOfSheet/2 + b/2 + padding/5*4);
        Point bPoint = new Point(sizeOfSheet/2 + a/2 + 5, sizeOfSheet/2+10);
        Point questionPoint = new Point(sizeOfSheet/2, sizeOfSheet/2+10);

        List<Sign> signs = new ArrayList<>();
        signs.add(new Sign(aPoint, Integer.toString(aSide)));
        signs.add(new Sign(bPoint, Integer.toString(bSide)));
        signs.add(new Sign(questionPoint, "?"));

        return drawRectangle(a, b, signs);
    }

    public BufferedImage drawRectangleSideFromArea(int a, int b) {
        int absoluteDist = sizeOfSheet - padding*2;
        int aSide = max(a, b);
        int bSide = min(a, b);

        b = absoluteDist / aSide * bSide;
        a = absoluteDist;

        Point aPoint = new Point(sizeOfSheet/2, sizeOfSheet/2 + b/2 + padding/5*4);
        Point bPoint = new Point(sizeOfSheet/2 + a/2 + 5, sizeOfSheet/2+10);
        Point questionPoint = new Point(sizeOfSheet/2 - 15, sizeOfSheet/2+10);

        List<Sign> signs = new ArrayList<>();
        signs.add(new Sign(aPoint, Integer.toString(aSide)));
        signs.add(new Sign(bPoint, "?"));
        signs.add(new Sign(questionPoint, "S = " + Integer.toString(aSide*bSide)));

        return drawRectangle(a, b, signs);
    }



}