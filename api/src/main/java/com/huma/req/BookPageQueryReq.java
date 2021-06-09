package com.huma.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author hudenian
 * @date 2021/6/8
 */
@Data
@ApiModel("图书分页查询")
public class BookPageQueryReq {

    @ApiModelProperty(value = "图书名称", name = "bookName", example = "java实战")
    private String bookName;

    @ApiModelProperty(value = "页号",  name = "pageNum", example = "1")
    private Integer pageNum = 1;

    @ApiModelProperty(value = "每页大小",  name = "pageSize", example = "10")
    private Integer pageSize = 10;
}
