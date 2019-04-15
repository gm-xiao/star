package com.sofyun.star.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName AuthLoginRequest
 * @Description TODO
 * @Author gm
 * @Date 2019/3/8 16:54
 **/
@Data
@ApiModel(value="注册", description="注册")
public class AuthRegRequest implements Serializable {

    private static final long serialVersionUID = -5553925398385607357L;

    @ApiModelProperty(value = "用户名", required = true)
    private String username;

    @ApiModelProperty(value = "密码", required = true)
    private String password;

    @ApiModelProperty(value = "验证码", required = true)
    private String vcode;

}
