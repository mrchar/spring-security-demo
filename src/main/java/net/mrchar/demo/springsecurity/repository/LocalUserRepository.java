package net.mrchar.demo.springsecurity.repository;

import net.mrchar.demo.springsecurity.model.LocalUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocalUserRepository extends JpaRepository<LocalUser, Long> {
  /**
   * 根据用户名获取用户信息
   *
   * @param name 用户名称
   * @return
   */
  @Query("select localUser from LocalUser localUser where localUser.name = :name")
  Optional<LocalUser> findByName(@Param("name") String name);

  /**
   * 根据用户名删除用户
   *
   * @param name 用户名称
   */
  @Query("delete from LocalUser localUser where localUser.name = :name")
  void deleteByName(@Param("name") String name);

  /**
   * 检查用户名对应的用户是否存在
   *
   * @param name 用户名称
   * @return
   */
  @Query("select count(localUser) > 0 from LocalUser localUser where localUser.name = :name")
  boolean existsByName(@Param("name") String name);
}
