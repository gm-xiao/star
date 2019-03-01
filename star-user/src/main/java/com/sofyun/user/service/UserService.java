package com.sofyun.user.service;

import com.sofyun.user.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author gm
 * @since 2019-02-28
 */
public interface UserService extends IService<User> {

    User findByCode(String code);

    Boolean insert(User user);

}
