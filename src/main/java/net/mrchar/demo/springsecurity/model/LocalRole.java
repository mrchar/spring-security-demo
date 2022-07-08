package net.mrchar.demo.springsecurity.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractAuditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

@Getter
@Entity
@Table(name = "local_role")
@EntityListeners(AuditingEntityListener.class)
public class LocalRole extends AbstractAuditable<LocalUser, Long> implements GrantedAuthority {
  @Setter
  @Column(name = "name", unique = true)
  private String name;

  @Setter
  @Column(name = "description")
  private String description;

  public LocalRole() {}

  public LocalRole(String name, String description) {
    this.name = name;
    this.description = description;
  }

  @Override
  public String getAuthority() {
    return "ROLE_" + this.name.toUpperCase();
  }
}
