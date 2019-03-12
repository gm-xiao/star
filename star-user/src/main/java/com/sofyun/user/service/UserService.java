package com.sofyun.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sofyun.user.domain.User;
import com.sofyun.user.model.UserBO;

import java.util.List;

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

    List<User> select(UserBO userBO);

    IPage<User> selectPage(UserBO userBO);

    User selectOne(UserBO userBO);

    User getModel(String id);

}
