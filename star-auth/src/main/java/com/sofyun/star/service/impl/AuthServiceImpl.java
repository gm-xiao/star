package com.sofyun.star.service.impl;

import com.sofyun.common.bo.auth.UserBO;
import com.sofyun.common.dto.auth.AuthUser;
import com.sofyun.common.exception.StarException;
import com.sofyun.star.service.AuthService;
import com.sofyun.star.service.UserService;
import com.sofyun.star.util.TokenUser;
import com.sofyun.star.util.TokenUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @ClassName AuthServiceImpl
 * @Description TODO
 * @Author gm
 * @Date 2019/2/28 15:01
 **/
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private TokenUtil tokenUtil;

    @Value("${auth.tokenHead}")
    private String tokenHead;

    @Override
    public String login(String username, String password) {
        AuthUser user = userService.findByCode(username).getData();
        if (null == user){
            throw new StarException("用户不存在");
        }else if ("1".equals(user.getIsDelete()) || "1".equals(user.getStatus()) ){
            throw new StarException("用户已冻结");
        }

        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final String token = tokenUtil.generateToken(userDetails);

        user.setLastLoginTime(LocalDateTime.now());

        return token;
    }

    @Override
    public Boolean register(UserBO userBO) {
        AuthUser user = new AuthUser();
        BeanUtils.copyProperties(userBO, user);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String rawPassword = user.getPwd();
        user.setPwd(encoder.encode(rawPassword));
        user.setStatus("1");
        user.setExamine("0");
        user.setIsDelete("0");
        return userService.save(user).getData();
    }

    @Override
    public String refresh(String oldToken) {
        final String token = oldToken.substring(tokenHead.length());
        String username = tokenUtil.getUsernameFromToken(token);
        TokenUser user = (TokenUser) userDetailsService.loadUserByUsername(username);
        if (tokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())){
            return tokenUtil.refreshToken(token);
        }
        return null;
    }
}
