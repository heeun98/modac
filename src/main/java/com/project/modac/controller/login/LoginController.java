package com.project.modac.controller.login;


import com.project.modac.global.login.dto.LoginRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {


    @PostMapping("/api/login")
    public String login(@RequestBody LoginRequest request) {
        return request.getUsername();
    }
}
