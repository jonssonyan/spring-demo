package com.springboot.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("汽车")
public class Car {
    @ApiModelProperty("主键")
    private Integer id;
    @ApiModelProperty("名称")
    private String name;
    @ApiModelProperty("价格")
    private Integer price;
    @ApiModelProperty("颜色")
    private String colour;
    @ApiModelProperty("品牌")
    private String brand;
}
