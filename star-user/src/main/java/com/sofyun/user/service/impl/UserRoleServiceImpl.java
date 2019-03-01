package com.sofyun.user.service.impl;

import com.sofyun.user.domain.UserRole;
import com.sofyun.user.mapper.UserRoleMapper;
import com.sofyun.user.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色 服务实现类
 * </p>
 *
 * @author gm
 * @since 2019-02-28
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}
