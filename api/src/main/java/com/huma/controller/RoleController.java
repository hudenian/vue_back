package com.huma.controller;

import com.huma.common.utils.BeanCopyUtil;
import com.huma.dto.RoleDto;
import com.huma.dto.UserDto;
import com.huma.req.role.AddRoleReq;
import com.huma.req.role.ModifyRoleReq;
import com.huma.req.user.AddReq;
import com.huma.service.IRoleService;
import com.huma.vo.ResponseVo;
import com.huma.vo.role.RoleVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;

/**
 * @author hudenian
 * @date 2021/6/11
 */
@Slf4j
@RestController
@Api(tags = "角色管理相关接口")
@RequestMapping(value = "role", produces = MediaType.APPLICATION_JSON_VALUE)
public class RoleController {

    @Resource
    private IRoleService roleService;

    @GetMapping("/getAllRoles")
    @ApiOperation(value = "获取所有角色接口", notes = "获取所有角色接口")
    public ResponseVo<List<RoleVo>> getAllRoles() {
        List<RoleDto> roleDtoList = roleService.getAllRole();
        List<RoleVo> roleVoList = getRoleVoList(roleDtoList);
        return ResponseVo.createSuccess(roleVoList);
    }

    private List<RoleVo> getRoleVoList(List<RoleDto> roleDtoList) {
        return BeanCopyUtil.copyListProperties(roleDtoList, RoleVo::new);
    }

    @PostMapping("add")
    @ApiOperation(value = "添加角色", notes = "添加角色")
    public ResponseVo<?> add(@RequestBody @Valid AddRoleReq addRoleReq) {
        RoleDto roleDto = new RoleDto();
        BeanUtils.copyProperties(addRoleReq, roleDto);
        roleService.add(roleDto);
        return ResponseVo.createSuccess();
    }

    @PostMapping("modify")
    @ApiOperation(value = "修改角色", notes = "修改角色")
    public ResponseVo<?> modify(@RequestBody ModifyRoleReq modifyRoleReq) {
        RoleDto roleDto = new RoleDto();
        BeanUtils.copyProperties(modifyRoleReq, roleDto);
        roleService.modify(roleDto);
        return ResponseVo.createSuccess();
    }


    @PostMapping("deleteById/{id}")
    @ApiOperation(value = "删除角色", notes = "删除角色")
    public ResponseVo<?> deleteById(@ApiParam(value = "项目ID", required = true) @PathVariable Long id) {
        roleService.deleteById(id);
        return ResponseVo.createSuccess();
    }

}
