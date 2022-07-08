package net.mrchar.demo.springsecurity;

import net.mrchar.demo.springsecurity.model.LocalUser;
import net.mrchar.demo.springsecurity.repository.LocalUserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableJpaAuditing
public class SpringSecurityDemoApplication {
  private final LocalUserRepository localUserRepository;
  private final PasswordEncoder passwordEncoder;

  public SpringSecurityDemoApplication(
      LocalUserRepository localUserRepository, PasswordEncoder passwordEncoder) {
    this.localUserRepository = localUserRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public static void main(String[] args) {
    SpringApplication.run(SpringSecurityDemoApplication.class, args);
  }

  @PostConstruct
  public void initData() {
    if (!this.localUserRepository.existsByName("admin")) {
      this.localUserRepository.save(new LocalUser("admin", passwordEncoder.encode("password")));
    }
  }
}
