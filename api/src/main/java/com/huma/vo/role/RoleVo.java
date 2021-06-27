package com.huma.vo.role;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class RoleVo {

    @ApiModelProperty("角色ID")
    private Long id;

    @ApiModelProperty("角色名称")
    private String name;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("状态 0锁定 1有效")
    private Byte status;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("修改时间")
    private Date updateTime;
}
