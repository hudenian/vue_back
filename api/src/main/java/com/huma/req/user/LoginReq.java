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

    @ApiModelProperty(value = "用户名", required = true, name = "userName", example = "hudenian")
    @NotBlank(message = "用户名不能为空")
    private String userName;

    @ApiModelProperty(value = "密码", required = true, name = "password", example = "423424324242")
    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 64, message = "密码长度最小6位且不超过64位")
    @Pattern(regexp = "^[\\da-zA-Z~!@#$%^&*()?,./;'<>:\"_-]{6,20}$", message = "密码格式错误")
    private String password;

}
