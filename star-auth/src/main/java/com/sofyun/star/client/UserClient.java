package com.sofyun.star.client;

import com.sofyun.common.dto.auth.AuthUser;
import com.sofyun.common.util.ResponseBo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName UserClient
 * @Description TODO
 * @Author gm
 * @Date 2019/2/28 14:38
 **/
@FeignClient("star-user")
@RequestMapping(value = "/user")
public interface UserClient {

    /**
     * 获取用户
     * @param username
     * @return
     */
    @GetMapping(value = "/find/username")
    public ResponseBo<AuthUser> findByCode(@RequestParam("username")String username);

    /**
     * 保存用户
     * @param authUser
     * @return
     */
    @PostMapping(value = "/save")
    public ResponseBo<Boolean> save(@RequestBody AuthUser authUser);

}
