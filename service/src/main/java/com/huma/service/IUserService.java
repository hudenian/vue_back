package com.huma.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huma.dto.UserDto;
import com.huma.mapper.domain.User;

/**
 * @author hudenian
 * @date 2021/6/11
 */
public interface IUserService extends IService<User> {

    /**
     * 用户登录
     *
     * @param userDto 用户登录请求对象
     * @return UserDto
     */
    UserDto login(UserDto userDto);

    /**
     * 用户注册
     *
     * @param userDto 用户注册请求对象
     */
    void register(UserDto userDto);

    /**
     * 查询用户列表
     *
     * @param pageNum  当前页
     * @param pageSize 每页大小
     * @param name     用户姓名
     * @return 用户列表
     */
    IPage<UserDto> userPageList(Long pageNum, Long pageSize, String name);
}
