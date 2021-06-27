package com.huma.vo;

import com.huma.dto.PermissionDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author hudenian
 * @date 2021/6/11
 */
@Data
public class PermissionVo {

    @ApiModelProperty("权限ID")
    private Long id;

    @ApiModelProperty("父级ID")
    private Long parentId;

    @ApiModelProperty("父级菜单名称")
    private String parentName;

    @ApiModelProperty("权限名称")
    private String name;

    @ApiModelProperty("URL")
    private String url;

    @ApiModelProperty("权限标识")
    private String perms;

    @ApiModelProperty("菜单权限类型: 1-目录, 2-菜单, 3-按钮")
    private Byte type;

    @ApiModelProperty("菜单图标")
    private String icon;

    @ApiModelProperty("序号")
    private Long seqNo;

    @ApiModelProperty("状态 0锁定 1有效")
    private Byte status;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("修改时间")
    private Date updateTime;

    @ApiModelProperty("子菜单")
    private List<PermissionVo> children;
}
