package com.huma.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huma.common.constants.ErrorMsgEnUs;
import com.huma.common.constants.ErrorMsgZhCn;
import com.huma.common.enums.RespCodeEnum;
import com.huma.common.exception.BusinessException;
import com.huma.dto.UserDto;
import com.huma.mapper.UserMapper;
import com.huma.mapper.domain.User;
import com.huma.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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

        User user = getUser(userDto.getUserName());
        BeanUtils.copyProperties(user, userDto);
        return userDto;
    }

    private User getUser(String userName) {
        LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(User::getName, userName);
        User user = this.baseMapper.selectOne(queryWrapper);
        if (null == user) {
            log.error("Can not find user by userName: {}.", userName);
            throw new BusinessException(RespCodeEnum.BIZ_FAILED, ErrorMsgZhCn.USER_UN_EXITS, ErrorMsgEnUs.USER_UN_EXITS);
        }
        return user;
    }
}
