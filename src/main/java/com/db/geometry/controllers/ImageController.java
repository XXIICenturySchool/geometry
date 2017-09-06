package com.db.geometry.controllers;

import com.mongodb.gridfs.GridFSDBFile;
import lombok.SneakyThrows;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.*;

//@PathVariable Long userId
@Controller
public class ImageController {
    @RequestMapping(value = "/images/{imageId}.gif", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<InputStreamResource> downloadUserAvatarImage(@PathVariable(value="imageId") String id) throws FileNotFoundException {

        System.out.println("Here!!!!!");
        System.out.println(id);
        File inFile = new File("src/main/resources/static/dinam/4.gif");
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
