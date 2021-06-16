package com.huma.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huma.common.constants.ErrorMsgEnUs;
import com.huma.common.constants.ErrorMsgZhCn;
import com.huma.common.enums.RespCodeEnum;
import com.huma.common.enums.StatEnum;
import com.huma.common.exception.BusinessException;
import com.huma.common.utils.EncryptUtil;
import com.huma.dto.UserDto;
import com.huma.mapper.UserMapper;
import com.huma.mapper.domain.User;
import com.huma.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
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

        User user = getUser(userDto.getName());
        BeanUtils.copyProperties(user, userDto);
        return userDto;
    }

    @Override
    public void register(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());

        String saltHexString = EncryptUtil.getSalt();
        user.setPassword(DigestUtils.md5Hex(userDto.getPassword() + saltHexString));
        user.setSalt(saltHexString);
        user.setSex(userDto.getSex());
        user.setStatus(StatEnum.VALID.getStatus());

        try {
            this.baseMapper.insert(user);
        } catch (DuplicateKeyException e) {
            log.error("Duplicate user registration.", e);
            throw new BusinessException(RespCodeEnum.BIZ_FAILED, ErrorMsgZhCn.USER_EXITS, ErrorMsgEnUs.USER_EXITS);
        } catch (Exception e) {
            log.error("User registration failed.", e);
            throw new BusinessException(RespCodeEnum.BIZ_FAILED, ErrorMsgZhCn.USER_REGISTER_FAILED, ErrorMsgEnUs.USER_REGISTER_FAILED);
        }
    }

    private User getUser(String name) {
        LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(User::getName, name);
        User user = this.baseMapper.selectOne(queryWrapper);
        if (null == user) {
            log.error("Can not find user by userName: {}.", name);
            throw new BusinessException(RespCodeEnum.BIZ_FAILED, ErrorMsgZhCn.USER_UN_EXITS, ErrorMsgEnUs.USER_UN_EXITS);
        }
        return user;
    }
}
