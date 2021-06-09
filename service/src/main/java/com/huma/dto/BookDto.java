package com.huma.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author hudenian
 * @date 2021/6/5
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BookDto extends com.huma.dto.BasePageDto {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 图书名称
     */
    private String bookName;

    /**
     * 图书作者
     */
    private String author;

    /**
     * 价格
     */
    private Double price;

    /**
     * 图书类别
     */
    private String bookType;

    /**
     * 备注
     */
    private String bookDesc;
}
