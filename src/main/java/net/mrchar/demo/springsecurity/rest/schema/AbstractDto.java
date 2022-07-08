package net.mrchar.demo.springsecurity.rest.schema;

import lombok.Getter;
import lombok.NoArgsConstructor;
import net.mrchar.demo.springsecurity.model.LocalUser;
import org.springframework.data.jpa.domain.AbstractAuditable;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor
public abstract class AbstractDto {
  private String createdBy;
  private ZonedDateTime createdDate;
  private String lastModifiedBy;
  private ZonedDateTime lastModifiedDate;

  protected AbstractDto(AbstractAuditable<LocalUser, UUID> entity) {
    entity.getCreatedBy().ifPresent(item -> this.createdBy = item.getName());

    entity
        .getCreatedDate()
        .ifPresent(item -> this.createdDate = item.atZone(ZoneId.systemDefault()));

    entity.getLastModifiedBy().ifPresent(item -> this.lastModifiedBy = item.getName());

    entity
        .getLastModifiedDate()
        .ifPresent(item -> this.lastModifiedDate = item.atZone(ZoneId.systemDefault()));
  }
}
