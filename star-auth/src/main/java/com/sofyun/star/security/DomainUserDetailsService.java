package com.sofyun.star.security;

import com.sofyun.common.dto.auth.AuthUser;
import com.sofyun.star.client.UserClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by wangyunfei on 2017/6/9.
 */
//@Service("userDetailsService")
@Slf4j
public class DomainUserDetailsService implements UserDetailsService {

    @Autowired
    private UserClient userClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String lowcaseUsername = username.toLowerCase();
        AuthUser authUser = userClient.findByCode(username).getData();
        if (null == authUser){
            throw new UsernameNotFoundException("用户" + lowcaseUsername + "不存在!");
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        return new User(authUser.getCode(),authUser.getPwd(),grantedAuthorities);
    }
}
