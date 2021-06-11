package com.huma.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author hudenian
 * @date 2021/6/11
 */
@Data
public class UserDto {

    /**
     * 用户ID
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 盐值
     */
    private String salt;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 手机
     */
    private String phone;

    /**
     * 性别 0男 1女
     */
    private String sex;

    /**
     * 描述
     */
    private String description;

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
}