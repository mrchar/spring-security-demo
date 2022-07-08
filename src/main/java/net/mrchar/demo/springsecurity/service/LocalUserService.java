package net.mrchar.demo.springsecurity.service;

import net.mrchar.demo.springsecurity.exception.ResourceNotFoundException;
import net.mrchar.demo.springsecurity.model.LocalUser;
import net.mrchar.demo.springsecurity.repository.LocalUserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.security.RolesAllowed;

@Service
public class LocalUserService implements UserDetailsService {
  private final LocalUserRepository localUserRepository;

  public LocalUserService(LocalUserRepository localUserRepository) {
    this.localUserRepository = localUserRepository;
  }

  /**
   * 获取用户列表
   *
   * @param pageable 分页排序
   * @return 用户列表
   */
  @RolesAllowed({"ROLE_ADMIN"})
  public Page<LocalUser> listAllLocalUsers(Pageable pageable) {
    return this.localUserRepository.findAll(pageable);
  }

  /**
   * 根据用户名获取用户信息
   *
   * @param name 用户名
   * @return 用户信息
   */
  public LocalUser getLocalUser(String name) {
    return localUserRepository
        .findByName(name)
        .orElseThrow(
            () -> {
              throw new ResourceNotFoundException("用户不存在");
            });
  }

  /**
   * 添加用户
   *
   * @param name 用户名称
   * @param password 密码
   * @return 用户信息
   */
  @Transactional
  public LocalUser addLocalUser(String name, String password) {
    LocalUser localUser = new LocalUser(name, password);
    return this.localUserRepository.save(localUser);
  }

  /**
   * 删除用户
   *
   * @param name 用户名
   */
  @Transactional
  public void deleteLocalUser(String name) {
    this.localUserRepository.deleteByName(name);
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return this.localUserRepository
        .findByName(username)
        .orElseThrow(
            () -> {
              throw new UsernameNotFoundException("用户不存在");
            });
  }
}
