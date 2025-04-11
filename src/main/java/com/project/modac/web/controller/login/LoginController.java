package com.project.modac.web.controller.login;


import com.project.modac.apiPayload.ApiResponse;
import com.project.modac.web.dto.LoginRequest;
import com.project.modac.web.dto.LoginResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {


    @PostMapping("/modac/login")
    public String login(@RequestBody LoginRequest request) {
        return request.getUsername();
    }
}
