package com.huma.vo.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.huma.common.constants.SysConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author hudenian
 * @date 2021/6/11
 */
@Data
@ApiModel
public class UserVo {

    @ApiModelProperty("用户主键ID")
    private Long id;

    @ApiModelProperty("用户名")
    private String name;

    @ApiModelProperty("部门ID")
    private Long deptId;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("性别 0男 1女")
    private Byte sex;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("状态 0锁定 1有效")
    private Byte status;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = SysConstant.DEFAULT_TIME_PATTERN, timezone = SysConstant.DEFAULT_TIMEZONE)
    private Date createTime;

    @ApiModelProperty("注册时间")
    @JsonFormat(pattern = SysConstant.DEFAULT_TIME_PATTERN, timezone = SysConstant.DEFAULT_TIMEZONE)
    private Date updateTime;

    @ApiModelProperty("用户登录token")
    private String token;

}
