package net.mrchar.demo.springsecurity.constant;

import net.mrchar.demo.springsecurity.model.LocalRole;

public class LocalRoles {
  public static final LocalRole ROLE_USER = new LocalRole("user", "普通用户");
  public static final LocalRole ROLE_ADMIN = new LocalRole("admin", "系统管理员");
}
