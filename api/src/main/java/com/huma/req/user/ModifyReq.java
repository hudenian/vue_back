package com.huma.req.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * @author hudenian
 * @date 2021/6/11
 */
@Data
@ApiModel
public class ModifyReq {

    @ApiModelProperty(value = "用户Id", required = true, name = "用户Id", example = "1")
    @NotNull(message = "用户ID不能为空")
    @Positive(message = "用户ID错误")
    private Long id;

    @ApiModelProperty(value = "手机号", required = true, name = "phone", example = "18621970593")
    @NotBlank(message = "{user.phone.notBlank}")
    private String phone;

}
