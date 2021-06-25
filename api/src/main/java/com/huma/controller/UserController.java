package com.huma.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.huma.common.utils.BeanCopyUtil;
import com.huma.dto.UserDto;
import com.huma.req.user.*;
import com.huma.service.IUserService;
import com.huma.vo.PageVo;
import com.huma.vo.ResponseVo;
import com.huma.vo.user.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
 * @date 2021/6/8
 */
@Slf4j
@RestController
@Api(tags = "用户管理关接口")
@RequestMapping(value = "user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Resource
    private IUserService userService;

    @PostMapping("register")
    @ApiOperation(value = "用户注册", notes = "用户注册")
    public ResponseVo<?> register(@RequestBody @Valid RegisterReq registerReq) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(registerReq, userDto);
        userService.register(userDto);
        return ResponseVo.createSuccess();
    }

    @PostMapping("login")
    @ApiOperation(value = "用户登录", notes = "用户登录")
    public ResponseVo<UserVo> login(@RequestBody @Valid LoginReq loginReq) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(loginReq, userDto);
        userDto = userService.login(userDto);

        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(userDto, userVo);
        return ResponseVo.createSuccess(userVo);
    }

    @GetMapping("userList")
    @ApiOperation(value = "用户列表", notes = "用户列表")
    public ResponseVo<PageVo<UserVo>> userAuthList(@Valid UserPageReq userPageReq) {
        IPage<UserDto> servicePage = userService.userPageList(userPageReq.getPageNum(), userPageReq.getPageSize(), userPageReq.getName());

        PageVo<UserVo> controlPage = new PageVo<>();
        BeanCopyUtil.copyProperties(servicePage, controlPage);
        List<UserVo> userAuthVoList =
                BeanCopyUtil.copyListProperties(servicePage.getRecords(), UserVo::new);
        controlPage.setItems(userAuthVoList);
        controlPage.setCurrent(servicePage.getCurrent());
        controlPage.setSize(servicePage.getSize());
        controlPage.setTotal(servicePage.getTotal());
        return ResponseVo.createSuccess(controlPage);
    }

    @PostMapping("add")
    @ApiOperation(value = "添加用户", notes = "添加用户")
    public ResponseVo<?> add(@RequestBody @Valid AddReq addReq) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(addReq, userDto);
        userService.add(userDto);
        return ResponseVo.createSuccess();
    }

    @GetMapping("getUserById/{id}")
    @ApiOperation(value = "根据用户id获取用户信息", notes = "根据用户id获取用户信息")
    public ResponseVo<UserVo> getUserById(@PathVariable Long id) {
        UserDto userDto = userService.getUserById(id);
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(userDto, userVo);
        return ResponseVo.createSuccess(userVo);
    }

    @PostMapping("modify")
    @ApiOperation(value = "修改用户", notes = "修改用户")
    public ResponseVo<?> modify(@RequestBody @Valid ModifyReq modifyReq) {
        userService.modify(modifyReq.getId(),modifyReq.getPhone());
        return ResponseVo.createSuccess();
    }

    @PostMapping("delete")
    @ApiOperation(value = "删除用户", notes = "删除用户")
    public ResponseVo<?> delete(@RequestBody @Valid DeleteReq deleteReq) {
        userService.delete(deleteReq.getId());
        return ResponseVo.createSuccess();
    }

}
