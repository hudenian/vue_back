package com.huma.dto;

import lombok.Data;

/**
 * @author hudenian
 * @date 2021/4/14
 */
@Data
public class BasePageDto {
    private Long pageNum = 1L;
    private Long pageSize = 10L;
}
