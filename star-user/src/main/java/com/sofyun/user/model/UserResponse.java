package com.sofyun.user.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @ClassName UserResponse
 * @Description TODO
 * @Author gm
 * @Date 2019/3/11 15:33
 **/
@Data
@ApiModel(value="系统用户", description="系统用户")
public class UserResponse implements Serializable {

    private static final long serialVersionUID = -6694947934873316049L;

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

    @ApiModelProperty(value = "认证时间")
    private LocalDateTime examineTime;

    @ApiModelProperty(value = "最后登录时间")
    private LocalDateTime lastLoginTime;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "状态(0.正常 1.冻结)")
    private String status;

    @ApiModelProperty(value = "邀请码")
    private String invitationCode;

    @ApiModelProperty(value = "注册码")
    private String pollCode;

    @ApiModelProperty(value = "机构id")
    private String ownerId;

    @ApiModelProperty(value = "上级用户姓名")
    private String parentName;

    @ApiModelProperty(value = "角色id")
    private String roleId;

    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @ApiModelProperty(value = "代理等级(0.平台 1.一级代理 2.二级代理)")
    private Integer level;

    @ApiModelProperty(value = "上级用户ID")
    private String parentId;

    @ApiModelProperty(value = "上级用户手机号")
    private String parentMobile;

    @ApiModelProperty(value = "创建人Id")
    private String createId;

    @ApiModelProperty(value = "创建人姓名")
    private String createName;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改人Id")
    private String modifyId;

    @ApiModelProperty(value = "修改人姓名")
    private String modifyName;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "电子邮箱")
    private String email;

    @ApiModelProperty(value = "官网地址")
    private String website;

}
