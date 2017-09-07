
package com.db.geometry.drawers;

import lombok.Setter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.geom.GeneralPath;
import java.util.stream.Collectors;

import static com.db.geometry.drawers.helpers.MathHelper.cosDegrees;
import static com.db.geometry.drawers.helpers.MathHelper.sinDegrees;
import static java.lang.Math.*;


public class TriangularDrawer {

    private static final int NUMBER_OF_POINTS = 3;
    @Setter
    private int padding = 35;
    private Graphics2D graphic;
    private int sizeOfSheet = 360;
    private BufferedImage bi;

    public TriangularDrawer() {
        bi = new BufferedImage(sizeOfSheet, sizeOfSheet, BufferedImage.TYPE_INT_ARGB);
        this.graphic = bi.createGraphics();
        graphic.drawImage(bi,0, 0, Color.WHITE, null);
        graphic.setColor(Color.BLACK);
        graphic.setStroke(new BasicStroke(3));
    }

    //    in the center by default
    public BufferedImage createOnPoints(List<Point> points, List<Sign> signs) {
        int medianX = points.stream().map(Point::getX).reduce((integer, integer2) -> integer + integer2).get();
        int medianY = points.stream().map(Point::getY).reduce((integer, integer2) -> integer + integer2).get();
        int shiftRight = (int) (sizeOfSheet/2 - medianX/2);
        int shiftBottom = sizeOfSheet/2 - medianY/3;

        return createOnPoints(points, shiftRight, shiftBottom, signs);

    }

    public BufferedImage createOnPoints(List<Point> points, int shiftRight, int shiftBottom, List<Sign> signs) {
        points = points.stream().sorted().collect(Collectors.toList());
        GeneralPath polygon =
                new GeneralPath(GeneralPath.WIND_EVEN_ODD, NUMBER_OF_POINTS);

        if (sizeOfSheet - points.get(2).getY() - shiftBottom < padding) shiftBottom -= padding/2;

        polygon.moveTo(points.get(0).getX() + shiftRight, points.get(0).getY() + shiftBottom);

        for (int index = 1; index < points.size(); index++) {
            Point point = points.get(index);
            polygon.lineTo(point.getX() + shiftRight, point.getY() + shiftBottom);
        }
        polygon.closePath();

        graphic.draw(polygon);
        drawSigns(signs, shiftRight, shiftBottom);

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


    //    The last angle is unknown
    public BufferedImage createOnAngles(List<Integer> angles) {
        int last = angles.get(2);
        boolean flag = false;
        int sumOfAngles = angles.stream().reduce((integer, integer2) -> integer+integer2).get();
        if (sumOfAngles != 180) {
            throw new IllegalArgumentException("SUM OF ANGLES IN TRIANGLE MUST BE 180!, but you give " + angles + " = " + sumOfAngles);
        }

        int absoluteDist = sizeOfSheet - padding*2;
        angles = angles.stream().sorted((x, y) -> Integer.compare(y, x)).collect(Collectors.toList());

//        angles
        int a = angles.get(0), b = angles.get(1), c = angles.get(2);
//        sides
        double sideA, sideB, sideC;
        sideA = absoluteDist;
        sideB = (sideA/ sinDegrees(a)) * sinDegrees(b);
        sideC = (sideA/ sinDegrees(a)) * sinDegrees(c);


        double[] bPoint = {0, 0};
        double[] aPoint = {sideC * sinDegrees(b), sideC * cosDegrees(b)};
        double[] cPoint = {0, (sideB * cosDegrees(c)) + aPoint[1]};

        List<Point> points = Arrays.asList(new Point(aPoint), new Point(bPoint), new Point(cPoint));
        List<Sign> signs = new ArrayList<>();

        if (last != angles.get(0)) {
            signs.add(new Sign(points.get(0), Integer.toString(angles.get(0)) + "°"));
        } else {
            signs.add(new Sign(points.get(0), "?°"));
            flag = true;
        }

        if (last != angles.get(1) || flag) {
            signs.add(new Sign(points.get(1), Integer.toString(angles.get(1)) + "°"));
        } else {
            signs.add(new Sign(points.get(1), "?°"));
            flag = true;
        }

        if (last != angles.get(2) || flag) {
            signs.add(new Sign(points.get(2).moveBottom(padding/2).moveRight(padding/3),
                    Integer.toString(angles.get(2)) + "°"));
        } else {
            signs.add(new Sign(points.get(2).moveBottom(padding/2).moveRight(padding/3),
                    "?°"));
        }

        return createOnPoints(points, signs);
    }

    public BufferedImage createOnCathetus(int a, int b) {
        int absoluteDist = sizeOfSheet - padding * 2;
        int aCathet = max(a, b);
        int bCathet = min(a, b);

        bCathet = (int) (((double) absoluteDist / aCathet) * bCathet);
        aCathet = absoluteDist;

        List<Point> points = Arrays.asList(new Point(0, aCathet), new Point(bCathet, aCathet), new Point(bCathet, 0));
        points = points.stream().sorted().collect(Collectors.toList());

        List<Sign> signs = new ArrayList<>();
        signs.add(new Sign(points.get(0).middle(points.get(1).moveRight(padding/3)), Integer.toString(max(a,b))));
        signs.add(new Sign(points.get(2).middle(points.get(1).moveBottom((int) (padding*1.5))), Integer.toString(min(a,b))));
        signs.add(new Sign(points.get(0).middle(points.get(2).moveUp(padding/2).moveLeft((int) (padding*1.5))), "?"));
        int shiftRight = sizeOfSheet/2 - (points.get(0).getX()-points.get(2).getX())/2;

        return createOnPoints(points, shiftRight, padding, signs);

    }

    public BufferedImage createOnCathetAndHypotenuse(int cathet, int hypot) {
        int absoluteDist = sizeOfSheet - padding * 2;
        int temp;
        boolean flag = false;
        int cathet2 = (int) Math.sqrt(hypot * hypot - cathet * cathet);

        if (cathet2 > cathet) {
            temp = cathet2;
            cathet2 = cathet;
            cathet = temp;
            flag = true;
        }

        int bCathet = (int) (((double) absoluteDist / cathet) * cathet2);


        List<Point> points = Arrays.asList(new Point(0, absoluteDist), new Point(bCathet, absoluteDist), new Point(bCathet, 0));
        points = points.stream().sorted().collect(Collectors.toList());


        int shiftRight = sizeOfSheet/2 - (points.get(0).getX()-points.get(2).getX())/2;

        List<Sign> signs = new ArrayList<>();

        signs.add(new Sign(points.get(0).middle(points.get(1).moveRight(padding/3)), !flag ? Integer.toString(cathet) : "?"));
        signs.add(new Sign(points.get(2).middle(points.get(1).moveBottom((int) (padding*1.5))), !flag ? "?" : Integer.toString((int) cathet2)));
        signs.add(new Sign(points.get(0).middle(points.get(2).moveUp(padding/2).moveLeft((int) (padding*1.5))), Integer.toString(hypot)));

        return createOnPoints(points, shiftRight, padding, signs);

    }
}