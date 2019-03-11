package com.sofyun.star.web;

import com.sofyun.common.util.ResponseBo;
import com.sofyun.common.util.SmsService;
import com.sofyun.star.client.UserClient;
import com.sofyun.star.config.RedisUtil;
import com.sofyun.star.constant.ConstantKey;
import com.sofyun.star.model.AuthLoginRequest;
import com.sofyun.star.model.AuthRegRequest;
import com.sofyun.user.auth.AuthUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private RedisUtil redisUtil;

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
        if (!passwordEncoder.matches(authLoginRequest.getPassword(), authUser.getPwd())){
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
        if (StringUtils.isBlank(authRegRequest.getVcode())){
            return ResponseBo.getInstance(false).setMessage("参数错误").setCode(403);
        }
        Object object = redisUtil.get(ConstantKey.AUTH_VCODE + authRegRequest.getUsername());
        if (null == object || !authRegRequest.getVcode().equals(object.toString())){
            return ResponseBo.getInstance(false).setMessage("无效的验证码").setCode(403);
        }
        authUser = new AuthUser();
        authUser.setCode(authRegRequest.getUsername());
        authUser.setPwd(passwordEncoder.encode(authRegRequest.getPassword()));
        authUser.setMobile(authRegRequest.getUsername());
        authUser.setExamine("0");
        authUser.setStatus("0");
        authUser.setIsDelete("0");
        userClient.save(authUser);
        redisUtil.del(ConstantKey.AUTH_VCODE + authRegRequest.getUsername());
        return ResponseBo.ok(true).setMessage("注册成功");
    }

    @ApiOperation(value = "获取短信验证码")
    @GetMapping("/vcode")
    public ResponseBo<Boolean> vcode(@RequestParam String mobile){
        if (StringUtils.isBlank(mobile)){
            return ResponseBo.getInstance(false).setMessage("参数错误").setCode(403);
        }
        if (null != redisUtil.get(ConstantKey.AUTH_VCODE_MOBILE + mobile)){
            return ResponseBo.getInstance(false).setMessage("请稍后重试").setCode(403);
        }
        String vcode = SmsService.createCode(6);
        if (SmsService.sendVcode("【全币支付】", mobile, vcode)){
            redisUtil.set(ConstantKey.AUTH_VCODE + mobile, vcode, 5 * 60);
            redisUtil.set(ConstantKey.AUTH_VCODE_MOBILE + mobile, mobile,  60);
            return ResponseBo.ok(true).setMessage("发送成功");
        } else {
            return ResponseBo.getInstance(false).setMessage("发送失败");
        }
    }
}
