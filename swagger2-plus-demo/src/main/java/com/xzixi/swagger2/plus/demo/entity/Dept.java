package com.xzixi.swagger2.plus.demo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "部门")
public class Dept {

    @ApiModelProperty(value = "部门编号")
    private String deptNo;
    @ApiModelProperty(value = "部门名称")
    private String deptName;

}
