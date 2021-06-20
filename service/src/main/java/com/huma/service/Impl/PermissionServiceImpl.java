package com.huma.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huma.common.utils.BeanCopyUtil;
import com.huma.dto.PermissionDto;
import com.huma.common.enums.PermissionTypeEnum;
import com.huma.mapper.PermissionMapper;
import com.huma.mapper.domain.Permission;
import com.huma.service.IPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

        List<PermissionDto> permissionDtoList = new ArrayList<>();

        if (!permissionList.isEmpty()) {
            //TODO 拼装菜单树:1.获取所有的目录，2根据目录获取对应所有子菜单
            PermissionDto permissionDto = null;
            for (int i = 0; i < permissionList.size(); i++) {
                if (permissionList.get(i).getType() == PermissionTypeEnum.DIRECTORY.getCode()) {
                    permissionDto = new PermissionDto();
                    BeanUtils.copyProperties(permissionList.get(i), permissionDto);
                    permissionDto.setChildren(getChildExcBtn(permissionList.get(i).getId(), permissionList));
                    permissionDtoList.add(permissionDto);
                }
            }
        }
        return permissionDtoList;
    }

    @Override
    public List<PermissionDto> getAllPermissions() {
        List<Permission> permissionList = this.list();
        return BeanCopyUtil.copyListProperties(permissionList, PermissionDto::new);
    }

    /**
     * 递归获取菜单树
     */
    private List<PermissionDto> getTree(List<Permission> all, boolean type) {

        List<PermissionDto> list = new ArrayList<>();
        if (all == null || all.isEmpty()) {
            return list;
        }
        for (Permission permission : all) {
            log.info(permission.getParentId().toString());
            if (permission.getParentId().intValue() == 0) {
                PermissionDto permissionDto = new PermissionDto();
                BeanUtils.copyProperties(permission, permissionDto);

                if (type) {
                    permissionDto.setChildren(getChildExcBtn(permission.getId(), all));
                } else {
                    permissionDto.setChildren(getChildAll(permission.getId(), all));
                }
                list.add(permissionDto);
            }
        }
        return list;
    }

    private List<PermissionDto> getChildExcBtn(Long id, List<Permission> all) {

        List<PermissionDto> list = new ArrayList<>();
        for (Permission permission : all) {
            if (permission.getParentId().equals(id) && permission.getType() != 3) {
                PermissionDto permissionDto = new PermissionDto();
                BeanUtils.copyProperties(permission, permissionDto);
                permissionDto.setChildren(getChildExcBtn(permission.getId(), all));
                list.add(permissionDto);
            }
        }
        return list;
    }

    private List<PermissionDto> getChildAll(Long id, List<Permission> all) {

        List<PermissionDto> list = new ArrayList<>();
        for (Permission permission : all) {
            if (permission.getParentId().equals(id)) {
                PermissionDto permissionDto = new PermissionDto();
                BeanUtils.copyProperties(permission, permissionDto);
                permissionDto.setChildren(getChildAll(permission.getId(), all));
                list.add(permissionDto);
            }
        }
        return list;
    }
}
