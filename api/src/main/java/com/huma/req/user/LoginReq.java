package com.huma.req.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author hudenian
 * @date 2021/6/11
 */
@Data
@ApiModel
public class LoginReq {

    @ApiModelProperty(value = "用户名", required = true, name = "name", example = "hudenian")
    @NotBlank(message = "{user.name.notBlank}")
    private String name;

    @ApiModelProperty(value = "密码", required = true, name = "password", example = "423424324242")
    @NotBlank(message = "{user.password.notBlank}")
    private String password;

}
