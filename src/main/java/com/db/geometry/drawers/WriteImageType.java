package com.db.geometry.drawers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


// Add triangular poligon maker as class from two constructors

public class WriteImageType {
    static public void main(String args[]) throws Exception {
        try {

            TriangularDrawer triangularDrawer = new TriangularDrawer();

//            triangularDrawer.createOnCathetus(3, 4, 360);
//            triangularDrawer.createOnCathetAndHypotenuse(5, 6, 360);
//            triangularDrawer.createOnAngles(Arrays.asList(60, 60, 60), 360);

//            RectangleDrawer rectangleDrawer = new RectangleDrawer(graphic);
            BufferedImage bi = triangularDrawer.createOnCathetus(5, 10);
            ImageIO.write(bi, "png", new File("yourImageName.png"));
            ImageIO.write(bi, "gif", new File("yourImageName.gif"));

        } catch (IOException ie) {
            ie.printStackTrace();
        }

    }
}