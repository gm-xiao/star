package com.sofyun.common.bo.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName UserBO
 * @Description TODO
 * @Author gm
 * @Date 2019/2/28 17:41
 **/
@Data
@ApiModel(value="注册", description="注册")
public class UserBO implements Serializable {
    private static final long serialVersionUID = -2561924687907225441L;

    @ApiModelProperty(value = "手机号", required = true)
    private String mobile;

    @ApiModelProperty(value = "密码", required = true)
    private String pwd;

    @ApiModelProperty(value = "验证码", required = true)
    private String vcode;

    @ApiModelProperty(value = "姓名", required = false)
    private String name;
}
