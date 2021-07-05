package com.huma.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huma.mapper.domain.Role;
import com.huma.mapper.query.RoleWithPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author hudenian
 * @date 2021/6/11
 */
public interface RoleMapper extends BaseMapper<Role> {

    List<RoleWithPermission> getRolesWithPermission(@Param("status") Byte status);
}