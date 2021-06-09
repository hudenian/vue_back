package com.huma.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author hudenian
 * @date 2021/6/8
 */
@Data
@ApiModel("图书查询")
public class BookQueryReq {

    @ApiModelProperty(value = "图书名称", name = "bookName", example = "java实战")
    private String bookName;

    @ApiModelProperty(value = "图书作者",  name = "author", example = "小马哥")
    private String author;

    @ApiModelProperty(value = "价格",  name = "price", example = "10.12")
    private Double price;

    @ApiModelProperty(value = "图书类别",  name = "bookType", example = "技术")
    private String bookType;

    @ApiModelProperty(value = "备注",  name = "bookDesc", example = "值得一看的一本书")
    private String bookDesc;
}
