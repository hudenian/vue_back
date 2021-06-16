package com.huma.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author hudenian
 * @date 2021/6/11
 */
@Data
@ApiModel
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageVo<T> {
    /**
     * 符合条件的总记录数
     */
    @ApiModelProperty("符合条件的总记录数")
    private Long total;

    /**
     * 页码
     */
    @ApiModelProperty("当前页")
    private Integer current;

    /**
     * 每页大小
     */
    @ApiModelProperty("每页条数")
    private Integer size;

    /**
     * 查询结果
     */
    @ApiModelProperty("分页数据")
    private List<T> items;
}
