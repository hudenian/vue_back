package com.huma.req;

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
public class BookAddReq {

    @NotNull
    @ApiModelProperty(value = "图书名称", required = true, name = "bookName", example = "java实战")
    private String bookName;

    @NotNull
    @ApiModelProperty(value = "图书作者", required = true, name = "author", example = "小马哥")
    private String author;

    @NotNull
    @ApiModelProperty(value = "价格", required = true, name = "price", example = "10.12")
    private Double price;

    @NotNull
    @ApiModelProperty(value = "图书类别", required = true, name = "bookType", example = "技术")
    private String bookType;

    @NotNull
    @ApiModelProperty(value = "备注", required = true, name = "bookDesc", example = "值得一看的一本书")
    private String bookDesc;
}
