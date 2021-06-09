package com.huma.dto;

import lombok.Data;

import java.util.List;

/**
 * @author Oliver
 * @date 2021/4/20
 */
@Data
public class PageDto<T> {
    /**
     * 总记录数
     */
    private Long total;

    /**
     * 页码
     */
    private Integer current;

    /**
     * 每页大小
     */
    private Integer size;

    /**
     * 查询结果
     */
    private List<T> items;
}
