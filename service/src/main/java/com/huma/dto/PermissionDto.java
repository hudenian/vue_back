package com.huma.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author hudenian
 * @date 2021/6/11
 */
@Data
public class PermissionDto {

    /**
     * 权限ID
     */
    private Long id;

    /**
     * 父级ID
     */
    private Long parentId;

    /**
     * 权限名称
     */
    private String name;

    /**
     * URL
     */
    private String url;

    /**
     * 权限标识
     */
    private String perms;

    /**
     * 菜单权限类型: 1-目录, 2-菜单, 3-按钮
     */
    private String type;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 序号
     */
    private Long seqNo;

    /**
     * 状态 0锁定 1有效
     */
    private Boolean status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 子菜单
     */
    private List<PermissionDto> children;
}
