package com.huma.service.utils;

import com.huma.dto.UserDto;

/**
 * @author hudenian
 * @date 2021/6/9
 */
public class UserContext {
    private static final ThreadLocal<UserDto> USER_THREAD_LOCAL = new ThreadLocal<>();

    private UserContext() {}

    public static UserDto get() {
        return USER_THREAD_LOCAL.get();
    }

    public static void set(UserDto userDto) {
        USER_THREAD_LOCAL.set(userDto);
    }
}
