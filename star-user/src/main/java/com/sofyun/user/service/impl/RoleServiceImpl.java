package com.sofyun.user.service.impl;

import com.sofyun.user.domain.Role;
import com.sofyun.user.mapper.RoleMapper;
import com.sofyun.user.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author gm
 * @since 2019-02-28
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
