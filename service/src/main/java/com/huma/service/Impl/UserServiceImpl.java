package com.huma.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huma.dto.UserDto;
import com.huma.mapper.UserMapper;
import com.huma.mapper.domain.User;
import com.huma.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author hudenian
 * @date 2021/6/11
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Override
    public UserDto login(UserDto userDto) {
        return null;
    }
}
