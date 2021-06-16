package com.huma.interceptor;

import com.huma.common.enums.RespCodeEnum;
import com.huma.common.exception.BusinessException;
import com.huma.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.UUID;

/**
 * @author hudenian
 * @date 2021/5/18
 */
@Slf4j
@Aspect
@Component
public class ControllerAspect {

    @Pointcut("execution(* com.huma.controller..*.*(..))")
    public void pointCut() {

    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint pjp) {
        // 加入日志跟踪号
        addRequestId();

        long start = System.currentTimeMillis();

        // 被拦截的类
        String clazzName = pjp.getTarget().getClass().getName();
        // 被拦截的方法签名
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        // 被拦截的方法
        Method method = methodSignature.getMethod();
        // 被拦截的方法名
        String methodName = methodSignature.getName();
        // 被拦截的方法
        Class<?> returnType = method.getReturnType();
        // 请求参数
        Object[] args = pjp.getArgs();

        log.info("Request {}.{} start... ...", clazzName, methodName);
        if (null != args && args.length > 0) {
            log.info("Request parameters: {}.", args[0]);
        }

        Object returnObj;
        try {
            returnObj = pjp.proceed(args);
        } catch (BusinessException e) {
            log.warn("A biz exception occurred on the request. Error message: " + e.getMsg());

            returnObj = ResponseVo.create(e.getCode(), e.getMsg(),e.getMsg());
        } catch (Throwable e) {
            log.error("An exception occurred on the request.", e);

            returnObj = ResponseVo.create(RespCodeEnum.EXCEPTION);
        }

        log.info("End of request, Used Time: {}ms, Return result: {}.", (System.currentTimeMillis() - start),
                returnObj);
        return returnObj;
    }

    void addRequestId() {
        MDC.put("requestId", UUID.randomUUID().toString().replaceAll("-", "").toLowerCase());
    }
}
