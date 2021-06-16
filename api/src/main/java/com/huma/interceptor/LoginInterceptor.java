package com.huma.interceptor;

import com.alibaba.fastjson.JSON;
import com.huma.annotation.RequiresRoles;
import com.huma.common.constants.SysConstant;
import com.huma.common.enums.RespCodeEnum;
import com.huma.common.utils.IpUtil;
import com.huma.common.utils.LanguageContext;
import com.huma.dto.UserDto;
import com.huma.service.ITokenService;
import com.huma.service.utils.UserContext;
import com.huma.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author hudenian
 * @date 2021/6/9
 */
@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {

    private static final String ENCODING = "UTF-8";
    private static final String CONTENT_TYPE = "application/json;charset=utf-8";

    @Resource
    private ITokenService tokenService;

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                             @NonNull Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        LanguageContext.set(request.getHeader("Accept-Language"));

        log.info("Request Info: [Method = {}], [URI = {}], [Client-IP = {}], [userAgent = {}]", request.getMethod(),
                request.getRequestURI(), IpUtil.getIpAddr(request), request.getHeader("user-agent"));

        String token = request.getHeader(SysConstant.HEADER_TOKEN_KEY);
        UserDto userDto = null;
        if (!StringUtils.isEmpty(token)) {
            userDto = tokenService.getUserByToken(token);
            if (null != userDto) {
                UserContext.set(userDto);
                tokenService.refreshToken(token);
            } else {
                log.warn("Invalid token: {}", token);
            }
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        RequiresRoles roles = handlerMethod.getMethodAnnotation(RequiresRoles.class);
        if (null != roles && roles.value().length > 0) {
            if (userDto == null) {
                log.warn("The user is not logged in!");
                printResponse(response, RespCodeEnum.UN_LOGIN);
                return false;
            }
            boolean hasRole = false;
            if (ArrayUtils.contains(userDto.getRoles(), roles)) {
                hasRole = true;
            }
            if (!hasRole) {
                log.warn("The current user does not have permission!");
                printResponse(response, RespCodeEnum.UN_ROLE);
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(@NonNull HttpServletRequest request, HttpServletResponse response, @NonNull Object handler,
                           ModelAndView modelAndView) {
    }

    @Override
    public void afterCompletion(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                                @NonNull Object handler, Exception ex) {
    }

    void printResponse(HttpServletResponse response, RespCodeEnum respCodeEnum) throws IOException {
        response.setCharacterEncoding(ENCODING);
        response.setContentType(CONTENT_TYPE);
        response.getWriter().write(JSON.toJSONString(ResponseVo.create(respCodeEnum)));
    }
}
