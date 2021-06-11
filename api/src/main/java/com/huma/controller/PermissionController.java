package com.huma.controller;

import com.huma.dto.PermissionDto;
import com.huma.service.IPermissionService;
import com.huma.vo.PermissionVo;
import com.huma.vo.ResponseVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hudenian
 * @date 2021/6/11
 */
@Slf4j
@RestController
@Api(tags = "菜单权限管理关接口")
@RequestMapping(value = "permission", produces = MediaType.APPLICATION_JSON_VALUE)
public class PermissionController {

    @Resource
    private IPermissionService permissionService;

    @GetMapping("/getAllMenus")
    @ApiOperation(value = "获取所有目录菜单树接口", notes = "获取所有目录菜单树接口")
    public ResponseVo<List<PermissionVo>> getAllMenus() {
        List<PermissionDto> permissionDtoList = permissionService.getAllMenus();
        List<PermissionVo> permissionVoList = new ArrayList<>();
        permissionDtoList.forEach(permissionDto -> {
            PermissionVo p = new PermissionVo();
            p.setId(permissionDto.getId());
            p.setParentId(permissionDto.getParentId());
            p.setName(permissionDto.getName());
            p.setUrl(permissionDto.getUrl());
            p.setType(permissionDto.getType());
            p.setIcon(permissionDto.getIcon());
            p.setSeqNo(permissionDto.getSeqNo());
            permissionVoList.add(p);
        });
        return ResponseVo.createSuccess(permissionVoList);
    }
}
