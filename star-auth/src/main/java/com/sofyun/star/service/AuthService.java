package com.sofyun.star.service;

import com.sofyun.common.bo.auth.UserBO;

public interface AuthService {

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    String login(String username, String password);

    /**
     * 注册用户
     * @param userBO
     * @return
     */
    Boolean register(UserBO userBO);

    /**
     * 刷新Token
     * @param token
     * @return
     */
    String refresh(String token);

}
