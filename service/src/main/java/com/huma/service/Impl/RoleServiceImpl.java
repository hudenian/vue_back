package com.huma.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huma.common.enums.StatEnum;
import com.huma.common.utils.BeanCopyUtil;
import com.huma.dto.PermissionDto;
import com.huma.dto.RoleDto;
import com.huma.mapper.RoleMapper;
import com.huma.mapper.domain.Role;
import com.huma.mapper.query.RoleWithPermission;
import com.huma.service.IPermissionService;
import com.huma.service.IRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author hudenian
 * @date 2021/6/11
 */
@Slf4j
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Resource
    private IPermissionService permissionService;
    @Override
    public List<RoleDto> getAllRole() {
//        LambdaQueryWrapper<Role> l = Wrappers.lambdaQuery();
//        l.eq(Role::getStatus, StatEnum.VALID.getStatus());
//        List<Role> roleList = this.baseMapper.selectList(l);

        /*获取所有的权限*/
        List<PermissionDto> permissionDtoList = permissionService.getAllPermissions();

        List<RoleWithPermission> roleList =this.baseMapper.getRolesWithPermission(StatEnum.VALID.getStatus());




        return BeanCopyUtil.copyListProperties(roleList, RoleDto::new);
    }

    @Override
    public void add(RoleDto roleDto) {
        Role role = new Role();
        role.setName(roleDto.getName());
        role.setDescription(roleDto.getDescription());
        role.setStatus(StatEnum.VALID.getStatus());
        this.baseMapper.insert(role);
    }

    @Override
    public void deleteById(Long id) {
        this.baseMapper.deleteById(id);
    }

    @Override
    public void modify(RoleDto roleDto) {
        LambdaUpdateWrapper<Role> lambdaUpdateWrapper = Wrappers.lambdaUpdate();
        lambdaUpdateWrapper.set(Role::getName, roleDto.getName());
        lambdaUpdateWrapper.set(Role::getDescription, roleDto.getDescription());
        lambdaUpdateWrapper.eq(Role::getId, roleDto.getId());
        this.update(lambdaUpdateWrapper);
    }
}
