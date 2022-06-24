package net.mrchar.demo.springsecurity.rest.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class LoginRequest {
  @Schema(description = "用户名")
  private String username;

  @Schema(description = "密码")
  private String password;
}
