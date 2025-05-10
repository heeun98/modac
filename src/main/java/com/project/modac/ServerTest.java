package com.project.modac;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServerTest {


    @GetMapping("/")
    public String home() {
        return "연결 성공";
    }
}
