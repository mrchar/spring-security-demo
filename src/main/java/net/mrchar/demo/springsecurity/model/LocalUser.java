package net.mrchar.demo.springsecurity.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractAuditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Getter
@Entity
@Table(name = "local_user")
@EntityListeners(AuditingEntityListener.class)
public class LocalUser extends AbstractAuditable<LocalUser, UUID> implements UserDetails {
  @Setter
  @Column(name = "name", unique = true)
  private String name;

  @Setter
  @Column(name = "password")
  private String password;

  public LocalUser() {}

  public LocalUser(String name) {
    this.name = name;
  }

  public LocalUser(String name, String password) {
    this.name = name;
    this.password = password;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return new ArrayList<>();
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
