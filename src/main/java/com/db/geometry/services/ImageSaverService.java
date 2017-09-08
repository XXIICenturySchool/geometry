package com.db.geometry.services;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.InetAddress;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class ImageSaverService {

    @Value("${app.port}")
    String port;

    @Value("${app.task.static.folder}")
    String staticFolder;

    @Value("${app.task.images.path}")
    String imagesPath;

    @Value("${app.task.images.url}")
    String imagesUrl;

    @SneakyThrows
    public String saveAndGetAddress(BufferedImage image, String examId, int taskNum) {
        String fileFullName = String.format("%s-%s.gif", examId, taskNum);
        String safeFileName = staticFolder + imagesPath + fileFullName;

        File file = new File(staticFolder + imagesPath);
        if (!file.exists() || !file.isDirectory()) {
            file.mkdir();
        }
        ImageIO.write(image, "gif", new File(safeFileName));
        return "http://" + InetAddress.getLocalHost().getHostName() +  ":" + port + imagesUrl + fileFullName;
    }
}
