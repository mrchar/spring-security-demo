package net.mrchar.demo.springsecurity;

import net.mrchar.demo.springsecurity.model.LocalRole;
import net.mrchar.demo.springsecurity.model.LocalUser;
import net.mrchar.demo.springsecurity.repository.LocalRoleRepository;
import net.mrchar.demo.springsecurity.repository.LocalUserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.Set;

import static net.mrchar.demo.springsecurity.constant.LocalRoles.ROLE_ADMIN;
import static net.mrchar.demo.springsecurity.constant.LocalRoles.ROLE_USER;

@SpringBootApplication
public class SpringSecurityDemoApplication {
  private final LocalRoleRepository localRoleRepository;
  private final LocalUserRepository localUserRepository;
  private final PasswordEncoder passwordEncoder;

  public SpringSecurityDemoApplication(
      LocalRoleRepository localRoleRepository,
      LocalUserRepository localUserRepository,
      PasswordEncoder passwordEncoder) {
    this.localRoleRepository = localRoleRepository;
    this.localUserRepository = localUserRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public static void main(String[] args) {
    SpringApplication.run(SpringSecurityDemoApplication.class, args);
  }

  @PostConstruct
  public void initData() {
    LocalRole roleUser =
        this.localRoleRepository
            .findByName(ROLE_USER.getName())
            .orElseGet(
                () ->
                    this.localRoleRepository.save(
                        new LocalRole(ROLE_USER.getName(), ROLE_USER.getDescription())));

    LocalRole roleAdmin =
        this.localRoleRepository
            .findByName(ROLE_ADMIN.getName())
            .orElseGet(
                () ->
                    this.localRoleRepository.save(
                        new LocalRole(ROLE_ADMIN.getName(), ROLE_ADMIN.getDescription())));

    if (!this.localUserRepository.existsByName("user")) {
      this.localUserRepository.save(
          new LocalUser("user", passwordEncoder.encode("password"), Set.of(roleUser)));
    }

    if (!this.localUserRepository.existsByName("admin")) {
      this.localUserRepository.save(
          new LocalUser("admin", passwordEncoder.encode("password"), Set.of(roleUser, roleAdmin)));
    }
  }
}
