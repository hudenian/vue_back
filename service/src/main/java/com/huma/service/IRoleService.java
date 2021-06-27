package com.huma.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huma.dto.PermissionDto;
import com.huma.dto.RoleDto;
import com.huma.mapper.domain.Permission;
import com.huma.mapper.domain.Role;

import java.util.List;

/**
 * @author hudenian
 * @date 2021/6/11
 */
public interface IRoleService extends IService<Role> {
    /**
     * 获取所角色
     *
     * @return 角色列表
     */
    List<RoleDto> getAllRole();

    /**
     * 添加角色
     * @param roleDto 角色信息
     */
    void add(RoleDto roleDto);

    /**
     * 根据角色id删除角色
     * @param id 角色id
     */
    void deleteById(Long id);

    /**
     * 修改角色信息
     * @param roleDto 瓣的角色信息
     */
    void modify(RoleDto roleDto);
}
