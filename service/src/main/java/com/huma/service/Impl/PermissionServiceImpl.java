package com.huma.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huma.dto.PermissionDto;
import com.huma.enums.PermissionTypeEnum;
import com.huma.mapper.PermissionMapper;
import com.huma.mapper.domain.Permission;
import com.huma.service.IPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hudenian
 * @date 2021/6/11
 */
@Slf4j
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

    @Override
    public List<PermissionDto> getAllMenus() {
        LambdaQueryWrapper<Permission> l = Wrappers.lambdaQuery();
        l.in(Permission::getType, PermissionTypeEnum.DIRECTORY.getCode(), PermissionTypeEnum.MENU.getCode());
        List<Permission> permissionList = this.baseMapper.selectList(l);
        //TODO 拼装菜单树
        if(!permissionList.isEmpty()){

        }
        return null;
    }
}
