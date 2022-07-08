package net.mrchar.demo.springsecurity.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractAuditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Getter
@Entity
@Table(name = "local_user")
@EntityListeners(AuditingEntityListener.class)
public class LocalUser extends AbstractAuditable<LocalUser, Long> implements UserDetails {
  @Setter
  @Column(name = "name", unique = true)
  private String name;

  @Setter
  @Column(name = "password")
  private String password;

  @Setter
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "rel_local_user_local_role",
      joinColumns = @JoinColumn(name = "local_user_id"),
      inverseJoinColumns = @JoinColumn(name = "local_role_id"))
  private Set<LocalRole> roles;

  public LocalUser() {}

  public LocalUser(String name) {
    this.name = name;
  }

  public LocalUser(String name, String password) {
    this.name = name;
    this.password = password;
  }

  public LocalUser(String name, String password, Set<LocalRole> roles) {
    this.name = name;
    this.password = password;
    this.roles = roles;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.roles;
  }

  @Override
  public String getPassword() {
    return this.password;
  }

  @Override
  public String getUsername() {
    return this.name;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
