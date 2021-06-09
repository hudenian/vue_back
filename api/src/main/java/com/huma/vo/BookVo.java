package com.huma.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author hudenian
 * @date 2021/6/8
 */
@Data
@ApiModel("图书")
public class BookVo {

    @ApiModelProperty(value = "图书主键ID")
    private Long id;

    @ApiModelProperty(value = "图书名称")
    private String bookName;

    @ApiModelProperty(value = "图书作者")
    private String author;

    @ApiModelProperty(value = "价格")
    private Double price;

    @ApiModelProperty(value = "图书类别")
    private String bookType;

    @ApiModelProperty(value = "备注")
    private String bookDesc;
}
