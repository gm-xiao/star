package com.sofyun.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sofyun.common.util.IdUtils;
import com.sofyun.user.domain.User;
import com.sofyun.user.mapper.UserMapper;
import com.sofyun.user.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author gm
 * @since 2019-02-28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IdUtils idUtils;

    @Override
    public User findByCode(String code) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("code", code);
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public Boolean insert(User user) {
        user.setId(idUtils.create());
        return this.save(user);
    }
}
