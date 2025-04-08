package com.project.modac.web.controller.register;


import com.project.modac.apiPayload.ApiResponse;
import com.project.modac.service.RegisterService;
import com.project.modac.web.dto.RegisterRequestDto;
import com.project.modac.web.dto.RegisterResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegisterController {

    private final RegisterService registerService;

    @PostMapping("/modac/register")
    public ApiResponse<RegisterResponseDto> register(@RequestBody RegisterRequestDto request) {
        String username = registerService.register(request);

        RegisterResponseDto result = RegisterResponseDto.builder()
                .username(username)
                .build();


        return ApiResponse.onSuccess(result);
    }

}
