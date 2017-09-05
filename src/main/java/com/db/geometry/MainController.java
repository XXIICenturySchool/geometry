package com.db.geometry;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @PostMapping("/newexam")
    public String test(@RequestBody String json) {
        System.out.println(json);
        return "success";
    }
}
