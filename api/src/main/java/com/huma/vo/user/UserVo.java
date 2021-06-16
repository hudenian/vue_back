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

    @ApiModelProperty("用户id")
    private Long id;

    @ApiModelProperty("手机号码")
    private String name;

    @ApiModelProperty("注册时间")
    @JsonFormat(pattern = SysConstant.DEFAULT_TIME_PATTERN, timezone = SysConstant.DEFAULT_TIMEZONE)
    private Date createTime;

    @ApiModelProperty("用户登录token")
    private String token;

}
