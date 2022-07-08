package net.mrchar.demo.springsecurity.rest.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import net.mrchar.demo.springsecurity.model.LocalUser;

@Getter
@Schema(description = "用户信息")
public class LocalUserDto extends AbstractDto {
  @Schema(description = "用户名称")
  private String name;

  public LocalUserDto() {}

  public LocalUserDto(LocalUser localUser) {
    super(localUser);
    this.name = localUser.getName();
  }

  public static LocalUserDto fromLocalUser(LocalUser localUser) {
    return new LocalUserDto(localUser);
  }
}
