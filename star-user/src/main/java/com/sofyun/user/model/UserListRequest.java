package com.sofyun.user.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName UserListRequest
 * @Description TODO
 * @Author gm
 * @Date 2019/3/11 15:31
 **/
@Data
@ApiModel(value="用户列表", description="用户列表")
public class UserListRequest implements Serializable {

    private static final long serialVersionUID = -8025314543386652398L;

    private long size;

    private long current;

}
