package com.project.modac.global.login.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponse {
  private String username;
  private String name;
}
