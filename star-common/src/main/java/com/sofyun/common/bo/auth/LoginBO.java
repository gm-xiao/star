package com.sofyun.common.bo.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName LoginBO
 * @Description TODO
 * @Author gm
 * @Date 2019/2/28 17:33
 **/
@Data
@ApiModel(value="登录", description="登录")
public class LoginBO implements Serializable {

    private static final long serialVersionUID = 5229127649754347472L;

    @ApiModelProperty(value = "用户名", required = true)
    private String username;

    @ApiModelProperty(value = "密码", required = true)
    private String password;

}
