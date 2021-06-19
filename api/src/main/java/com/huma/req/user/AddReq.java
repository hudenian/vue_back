package com.huma.req.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author hudenian
 * @date 2021/6/11
 */
@Data
@ApiModel
public class AddReq {

    @ApiModelProperty(value = "用户名", required = true, name = "name", example = "hudenian")
    @NotBlank(message = "{user.name.notBlank}")
    private String name;

    @ApiModelProperty(value = "手机号", required = true, name = "phone", example = "18621970593")
    @NotBlank(message = "{user.phone.notBlank}")
    private String phone;

    @ApiModelProperty(value = "性别", required = true, name = "sex", example = "性别 0男 1女")
    @NotBlank(message = "{user.sex.notBlank}")
    private String sex;
}
