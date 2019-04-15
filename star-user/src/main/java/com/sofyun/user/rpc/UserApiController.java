package com.sofyun.user.rpc;

import com.sofyun.common.util.ResponseBo;
import com.sofyun.user.auth.AuthUser;
import com.sofyun.user.domain.User;
import com.sofyun.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author gm
 * @Date 2019/2/28 14:32
 **/
@Slf4j
@RestController
@RequestMapping(value = "/rpc/user")
@RefreshScope
@Api(value="用户管理",tags={"用户操作接口"})
public class UserApiController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "获取用户")
    @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String", paramType = "query")
    @GetMapping(value = "/findByCode")
    public ResponseBo<AuthUser> findByCode(@RequestParam("username")String username){
        User user = userService.findByCode(username);
        AuthUser authUser = null;
        if (null != user){
            authUser = new AuthUser();
            BeanUtils.copyProperties(user, authUser);
            authUser.setRoles(new ArrayList<>());
        }
        return ResponseBo.ok(authUser);
    }

    @ApiOperation(value = "保存用户")
    @PostMapping(value = "/save")
    public ResponseBo<Boolean> save(@RequestBody AuthUser authUser){
        User user = new User();
        BeanUtils.copyProperties(authUser, user);
        user.setCreateTime(LocalDateTime.now());
        Boolean result = userService.insert(user);
        if (result){
            return ResponseBo.ok(true).setMessage("保存成功");
        }else {
            return ResponseBo.getInstance(false).setMessage("保存失败").setCode(500);
        }
    }

}
