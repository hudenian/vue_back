package com.huma.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huma.common.constants.ErrorMsgEnUs;
import com.huma.common.constants.ErrorMsgZhCn;
import com.huma.common.enums.RespCodeEnum;
import com.huma.common.enums.StatEnum;
import com.huma.common.exception.BusinessException;
import com.huma.common.utils.BeanCopierUtil;
import com.huma.common.utils.EncryptUtil;
import com.huma.common.utils.StringUtil;
import com.huma.dto.UserDto;
import com.huma.mapper.UserMapper;
import com.huma.mapper.domain.User;
import com.huma.service.ITokenService;
import com.huma.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hudenian
 * @date 2021/6/11
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private ITokenService tokenService;

    @Override
    public UserDto login(UserDto userDto) {

        User user = getUser(userDto.getName());
        if (!EncryptUtil.verifyPassword(userDto.getPassword(), user.getPassword(), user.getSalt())) {
            log.error("User login user name or password error!");
            throw new BusinessException(RespCodeEnum.BIZ_FAILED, ErrorMsgZhCn.USER_OR_PASSWORD_ERROR, ErrorMsgEnUs.USER_OR_PASSWORD_ERROR);
        }

        BeanUtils.copyProperties(user, userDto);
        userDto.setToken(tokenService.setToken(userDto));
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

    @Override
    public IPage<UserDto> userPageList(Long pageNum, Long pageSize, String name) {
        IPage<User> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<User> wrapper = Wrappers.lambdaQuery();
        if (StringUtil.isNotEmpty(name)) {
            wrapper.like(User::getName, name);
        }
        IPage<User> iPage = this.baseMapper.selectPage(page, wrapper);
        return convertToPageDto(iPage);
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

    IPage<UserDto> convertToPageDto(IPage<?> page) {
        List<UserDto> items = new ArrayList<>();
        page.getRecords().forEach(user -> {
            UserDto projectDto = new UserDto();
            BeanCopierUtil.copy(user, projectDto);
            items.add(projectDto);
        });

        IPage<UserDto> pageDto = new Page<>();
        pageDto.setCurrent(page.getCurrent());
        pageDto.setRecords(items);
        pageDto.setSize(page.getSize());
        pageDto.setTotal(page.getTotal());
        return pageDto;
    }
}
