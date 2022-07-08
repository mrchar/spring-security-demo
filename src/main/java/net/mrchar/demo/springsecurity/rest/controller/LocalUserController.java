package net.mrchar.demo.springsecurity.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import net.mrchar.demo.springsecurity.model.LocalUser;
import net.mrchar.demo.springsecurity.rest.request.AddLocalUserRequest;
import net.mrchar.demo.springsecurity.rest.schema.LocalUserDto;
import net.mrchar.demo.springsecurity.service.LocalUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(name = "用户管理")
@RestController
public class LocalUserController {
  private final LocalUserService localUserService;

  public LocalUserController(LocalUserService localUserService) {
    this.localUserService = localUserService;
  }

  @Operation(summary = "获取用户列表")
  @GetMapping("/api/users")
  public Page<LocalUserDto> listAllLocalUsers(Pageable pageable) {
    return this.localUserService.listAllLocalUsers(pageable).map(LocalUserDto::fromLocalUser);
  }

  @Operation(summary = "获取用户信息")
  @GetMapping("/api/users/{name}")
  public LocalUserDto getLocalUser(@PathVariable("name") String name) {
    LocalUser localUser = this.localUserService.getLocalUser(name);
    return LocalUserDto.fromLocalUser(localUser);
  }

  @Operation(summary = "添加用户")
  @PostMapping("/api/users")
  public LocalUserDto addLocalUser(@RequestBody AddLocalUserRequest request) {
    LocalUser localUser =
        this.localUserService.addLocalUser(request.getName(), request.getPassword());
    return LocalUserDto.fromLocalUser(localUser);
  }

  @Operation(summary = "删除用户")
  @DeleteMapping("/api/users/{name}")
  public void deleteLocalUser(@PathVariable("name") String name) {
    this.localUserService.deleteLocalUser(name);
  }
}
