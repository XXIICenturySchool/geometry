package com.db.geometry.controllersRest;

import com.db.geometry.tasksCreation.TriangularDrawer;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

@RestController
public class MainController {

    @PostMapping("/newexam")
    @SneakyThrows
    public String test(@RequestBody String json) {

        TriangularDrawer triangularDrawer = new TriangularDrawer();

        BufferedImage bi = triangularDrawer.createOnCathetus(5, 5);
        ImageIO.write(bi, "png", new File("src/main/resources/static/dinam/2.png"));
        ImageIO.write(bi, "gif", new File("src/main/resources/static/dinam/4.gif"));

        System.out.println(json);
        return "success";
    }


}
