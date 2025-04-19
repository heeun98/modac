package com.project.modac.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegisterRequestDto {

    private String username;
    private String password;
    private String nickname;
    private String email;
    private String address;

}
