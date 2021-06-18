package com.huma.req.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

/**
 * @author hudenian
 * @date 2021/6/9
 */
@Data
@ApiModel
public class UserPageReq {

    @ApiModelProperty(value = "当前页码, 默认第一页")
    @Positive(message = "页码不能小于等于0")
    private Long pageNum = 1L;

    @ApiModelProperty(value = "每页大小, 默认每页十条. 最小支持每页1条, 最大支持每页1000条")
    @Min(value = 1L, message = "每页大小不能小于1")
    @Max(value = 1000L, message = "每页大小不能超过1000")
    private Long pageSize = 10L;

    @ApiModelProperty(value = "用户名", name = "name", example = "hudenian")
    private String name;

}
