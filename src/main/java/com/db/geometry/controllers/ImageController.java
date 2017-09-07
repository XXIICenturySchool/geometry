package com.db.geometry.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

@Controller
public class ImageController {

    @Value("${app.hostUrl}")
    String hostUrl;

    @Value("${app.task.static.folder}")
    String staticFolder;

    @Value("${app.task.images.path}")
    String imagesPath;

    @Value("${app.task.images.url}")
    String imagesUrl;

    @RequestMapping(value = "/images/{imageId}.gif", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<InputStreamResource> downloadUserAvatarImage(HttpServletRequest request, @PathVariable(value="imageId") String imageId) throws FileNotFoundException {

        File inFile = new File(staticFolder + imagesPath + imageId + ".gif");
        InputStream in = new FileInputStream(inFile);

        return ResponseEntity.ok()
                .contentLength(inFile.length())
                .contentType(MediaType.IMAGE_GIF)
                .body(new InputStreamResource(in));
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(FileNotFoundException exc) {
        exc.printStackTrace();
        return ResponseEntity.notFound().build();
    }
}
