package com.huma.req.role;

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
public class AddRoleReq {

    @ApiModelProperty(value = "角色名称", required = true, name = "name", example = "角色名称")
    @NotBlank(message = "{role.name.notBlank}")
    private String name;

    @ApiModelProperty(value = "描述", required = true, name = "description", example = "角色描述")
    @NotBlank(message = "{role.description.notBlank}")
    private String description;

}
