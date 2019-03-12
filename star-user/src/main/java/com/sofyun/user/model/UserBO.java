package com.sofyun.user.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName UserBO
 * @Description TODO
 * @Author gm
 * @Date 2019/3/11 15:35
 **/
@Data
public class UserBO implements Serializable {
    private static final long serialVersionUID = -1944128865644167645L;

    private long size;

    private long current;


}
