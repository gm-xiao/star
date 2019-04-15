package com.sofyun.common.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName Page
 * @Description TODO
 * @Author gm
 * @Date 2019/3/11 17:00
 **/
@Data
@ApiModel(value = "分页")
public class Page<T> implements Serializable {
    private static final long serialVersionUID = -4976756328313039709L;

    @ApiModelProperty(value = "内容", required = true)
    private List<T> content;

    @ApiModelProperty(value = "当前页数", required = true)
    private long number;

    @ApiModelProperty(value = "每页记录数", required = true)
    private long size;

    @ApiModelProperty(value = "总记录数", required = true)
    private long totalElements;

    @ApiModelProperty(value = "页数", required = true)
    private long totalPages;

}
