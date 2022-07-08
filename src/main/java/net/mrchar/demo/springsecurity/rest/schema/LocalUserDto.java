package net.mrchar.demo.springsecurity.rest.schema;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import net.mrchar.demo.springsecurity.model.LocalRole;
import net.mrchar.demo.springsecurity.model.LocalUser;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Schema(description = "用户信息")
public class LocalUserDto extends AbstractDto {
  @Schema(description = "用户名称")
  private String name;

  private List<String> roles;

  public LocalUserDto() {}

  public LocalUserDto(LocalUser localUser) {
    super(localUser);
    this.name = localUser.getName();
    if (localUser.getRoles() != null && !localUser.getRoles().isEmpty()) {
      this.roles =
          localUser.getRoles().stream().map(LocalRole::getName).collect(Collectors.toList());
    }
  }

  public static LocalUserDto fromLocalUser(LocalUser localUser) {
    return new LocalUserDto(localUser);
  }
}
