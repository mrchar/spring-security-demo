package net.mrchar.demo.springsecurity.repository;

import net.mrchar.demo.springsecurity.model.LocalRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface LocalRoleRepository extends JpaRepository<LocalRole, UUID> {
  /**
   * 判断名称对应的角色是否存在
   *
   * @param name
   * @return
   */
  @Query("select count(role)> 0 from LocalRole role where role.name = :name")
  boolean existsByName(String name);

  /**
   * 根据名称查找角色
   *
   * @param name
   * @return
   */
  @Query("select role from LocalRole role where role.name = :name")
  Optional<LocalRole> findByName(String name);
}
