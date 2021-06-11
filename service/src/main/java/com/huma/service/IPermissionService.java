package com.huma.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huma.dto.PermissionDto;
import com.huma.mapper.domain.Permission;

import java.util.List;

/**
 * @author hudenian
 * @date 2021/6/11
 */
public interface IPermissionService extends IService<Permission> {
    /**
     * 获取所有菜单
     *
     * @return 菜单列表
     */
    List<PermissionDto> getAllMenus();
}
