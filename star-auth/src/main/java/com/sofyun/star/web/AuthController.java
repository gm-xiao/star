package com.sofyun.star.web;

import com.sofyun.common.bo.auth.LoginBO;
import com.sofyun.common.bo.auth.UserBO;
import com.sofyun.common.exception.StarException;
import com.sofyun.common.util.ResponseBo;
import com.sofyun.star.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName AuthController
 * @Description TODO
 * @Author gm
 * @Date 2019/2/28 14:54
 **/
@Slf4j
@RestController
@RequestMapping(value = "/auth")
@RefreshScope
@Api(value="登录注册",tags={"登录注册接口"})
public class AuthController {

    @Autowired
    private AuthService authService;

    @Value("${auth.header}")
    private String tokenHeader;

    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public ResponseBo<?> login(@RequestBody LoginBO loginBO){
        try {
            String token = authService.login(loginBO.getUsername(),loginBO.getPassword());
            return ResponseBo.ok(token).setMessage("登录成功");
        } catch (StarException e) {
            e.printStackTrace();
            return ResponseBo.getInstance(false).setMessage(e.getMessage());
        }  catch (BadCredentialsException e) {
            e.printStackTrace();
            return ResponseBo.getInstance(false).setMessage("用户名或密码错误");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBo.getInstance(false).setMessage("系统错误");
        }
    }

    @ApiOperation(value = "注册")
    @PostMapping("/register")
    public ResponseBo<Boolean> register(@RequestBody UserBO userBO){
        try {
            authService.register(userBO);
            return ResponseBo.ok(true).setMessage("注册成功");
        } catch (StarException e) {
            e.printStackTrace();
            return ResponseBo.getInstance(false).setMessage(e.getMessage());
        } catch (Exception e) {
            return ResponseBo.getInstance(false).setMessage("系统错误");
        }
    }

    @ApiOperation(value = "刷新token")
    @GetMapping(value = "/refresh")
    public ResponseBo<?> refresh(HttpServletRequest request) throws AuthenticationException {
        String token = request.getHeader(tokenHeader);
        String refreshedToken = authService.refresh(token);
        if(refreshedToken == null) {
            return ResponseBo.ok(token).setMessage("刷新成功");
        } else {
            return ResponseBo.ok("").setMessage("请求失败");
        }
    }

}
