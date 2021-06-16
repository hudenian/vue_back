package com.huma.controller;

import com.huma.common.utils.LanguageContext;
import com.huma.dto.UserDto;
import com.huma.req.user.LoginReq;
import com.huma.service.IUserService;
import com.huma.vo.ResponseVo;
import com.huma.vo.user.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

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

    @PostMapping("login")
    @ApiOperation(value = "用户登录", notes = "用户登录")
    public ResponseVo<UserVo> login(@RequestBody @Valid LoginReq loginReq) {
        String language = LanguageContext.get();
        System.out.println("当前语言版本为："+language);
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(loginReq, userDto);
        userDto = userService.login(userDto);

        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(userDto, userVo);
        return ResponseVo.createSuccess(userVo);
    }
}
