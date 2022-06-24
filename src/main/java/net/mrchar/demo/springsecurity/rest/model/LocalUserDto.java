package net.mrchar.demo.springsecurity.rest.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import net.mrchar.demo.springsecurity.model.LocalUser;

@Data
@Schema(description = "用户信息")
public class LocalUserDto {
  @Schema(description = "用户名称")
  private String name;

  public LocalUserDto() {}

  public LocalUserDto(String name) {
    this.name = name;
  }

  public static LocalUserDto fromLocalUser(LocalUser localUser) {
    return new LocalUserDto(localUser.getName());
  }
}
