package com.sofyun.star.web;

import com.sofyun.common.util.ResponseBo;
import com.sofyun.star.client.UserClient;
import com.sofyun.star.model.AuthLoginRequest;
import com.sofyun.star.model.AuthRegRequest;
import com.sofyun.user.auth.AuthUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName AuthController
 * @Description TODO
 * @Author gm
 * @Date 2019/3/8 16:52
 **/
@RestController
@RequestMapping(value = "/oauth")
@Api(value="登录注册",tags={"登录注册"})
public class AuthController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserClient userClient;

    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    public ResponseBo<Boolean> login(@RequestBody AuthLoginRequest authLoginRequest){
        if (StringUtils.isBlank(authLoginRequest.getUsername()) && StringUtils.isBlank(authLoginRequest.getPassword())){
            return ResponseBo.getInstance(false).setMessage("参数错误").setCode(403);
        }
        AuthUser authUser = userClient.findByCode(authLoginRequest.getUsername()).getData();
        if (null == authUser){
            return ResponseBo.getInstance(false).setMessage("用户不存在").setCode(403);
        }
        if (!authUser.getPwd().equals(passwordEncoder.encode(authLoginRequest.getPassword()))){
            return ResponseBo.getInstance(false).setMessage("登录密码错误").setCode(403);
        }
        return ResponseBo.ok(true).setMessage("登录成功");
    }

    @ApiOperation(value = "用户注册")
    @PostMapping("/reg")
    public ResponseBo<Boolean> reg(@RequestBody AuthRegRequest authRegRequest){
        AuthUser authUser = userClient.findByCode(authRegRequest.getUsername()).getData();
        if (null != authUser){
            return ResponseBo.getInstance(false).setMessage("用户已存在").setCode(403);
        }
        authUser = new AuthUser();
        authUser.setCode(authRegRequest.getUsername());
        authUser.setPwd(passwordEncoder.encode(authRegRequest.getPassword()));
        authUser.setMobile(authRegRequest.getUsername());
        authUser.setExamine("0");
        authUser.setStatus("0");
        authUser.setIsDelete("0");
        userClient.save(authUser);
        return ResponseBo.ok(true).setMessage("注册成功");
    }

}
