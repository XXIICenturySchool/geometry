package com.db.geometry.drawers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
@Getter
@ToString
public class Point implements Comparable<Point> {

    public static void main(String[] args) {
        Point a = new Point(3, 1);
        Point b = new Point(3, 3);
        Point c = new Point(1, 3);

        System.out.println(Stream.of(b, a, c).sorted()
                .collect(Collectors.toList()));

    }

    private final int x;
    private final int y;


    public Point(int[] cords) {
        this.x = cords[0];
        this.y = cords[1];
    }

    public Point(double[] cords) {
        this.x = (int) cords[0];
        this.y = (int) cords[1];
    }

    public Point middle(Point point) {
        return new Point((x + point.x) / 2, (y + point.y) / 2);
    }

    public Point moveRight(int move) {
        return new Point(x + move, y);
    }

    public Point moveLeft(int move) {
        return moveRight(-move);
    }

    public Point moveBottom(int move) {
        return new Point(x, y + move);
    }

    public Point moveUp(int move) {
        return moveBottom(-move);
    }

//    The rightest the highest is the biggest
    @Override
    public int compareTo(Point o) {
        int compareRes = Integer.compare(x, o.x);
        return compareRes != 0 ? -compareRes : Integer.compare(y, o.y);
    }

}
