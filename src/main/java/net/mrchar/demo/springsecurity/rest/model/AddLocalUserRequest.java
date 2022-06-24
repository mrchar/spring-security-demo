package net.mrchar.demo.springsecurity.rest.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "创建用户的参数")
public class AddLocalUserRequest {
  @Schema(description = "用户名")
  private String name;

  @Schema(description = "密码")
  private String password;
}
