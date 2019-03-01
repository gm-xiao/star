package com.sofyun.star.util;

import com.sofyun.common.dto.auth.AuthUser;
import com.sofyun.star.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthUser user = userService.findByCode(username).getData();
        if (null == user) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            Date date = null;
            if (null != user.getLastLoginTime()){
                ZoneId zone = ZoneId.systemDefault();
                Instant instant = user.getLastLoginTime().atZone(zone).toInstant();
                date = Date.from(instant);
            }
            return TokenUserFactory.create(user.getId(), user.getCode(), user.getPwd(), user.getRoles(), date);
        }
    }

}