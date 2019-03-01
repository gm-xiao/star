package com.sofyun.star.util;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName TokenUserFactory
 * @Description TODO
 * @Author gm
 * @Date 2019/2/26 17:37
 * @Version 1.0
 **/
public final class TokenUserFactory {

    public static TokenUser create(String id, String  code, String pwd, List<String> roles, Date lastPasswordResetDate) {
        return new TokenUser(id, code, pwd, mapToGrantedAuthorities(roles), lastPasswordResetDate);
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<String> authorities) {
        return authorities.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
