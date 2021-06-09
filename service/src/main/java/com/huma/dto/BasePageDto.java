package com.huma.dto;

import lombok.Data;

/**
 * @author hudenian
 * @date 2021/4/14
 */
@Data
public class BasePageDto {
    private Integer pageNum = 1;
    private Integer pageSize = 10;
}
