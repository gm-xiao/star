package com.sofyun.common.dto.auth;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName AuthUser
 * @Description TODO
 * @Author gm
 * @Date 2019/2/28 17:08
 **/
@Data
public class AuthUser implements Serializable {

    private static final long serialVersionUID = 4816813428841786597L;

    @ApiModelProperty(value = "Id")
    private String id;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "角色")
    private String role;

    @ApiModelProperty(value = "认证(0.未认证 1.处理中 2.已认证 3.认证失败)")
    private String examine;

    @ApiModelProperty(value = "是否已删除(0.未删除 1.已删除")
    private String isDelete;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "编号")
    private String code;

    @ApiModelProperty(value = "最后登录时间")
    private LocalDateTime lastLoginTime;

    @ApiModelProperty(value = "密码")
    private String pwd;

    @ApiModelProperty(value = "状态(0.正常 1.冻结)")
    private String status;

    @ApiModelProperty(value = "角色列表")
    private List<String> roles;

}
