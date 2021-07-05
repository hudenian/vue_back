package com.huma.mapper.query;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author hudenian
 * @date 2021/7/2
 */
@Data
public class RoleWithPermission implements Serializable {
    /**
     * 角色ID
     */
    private Long id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 状态 0锁定 1有效
     */
    private Byte status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 角色对应权限列表
     */
    private String permissions;

    private static final long serialVersionUID = 1L;

}