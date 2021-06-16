package com.huma.interceptor;

import com.huma.common.enums.RespCodeEnum;
import com.huma.common.exception.BusinessException;
import com.huma.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author hudenian
 * @date 2021/4/9
 */
@Slf4j
@RestControllerAdvice
public class ControllerAdvice {
    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public <T> ResponseVo<T> exceptionHandler(HttpServletRequest request, Exception e) {
        log.error("An exception occurred on the request.", e);

        return ResponseVo.create(RespCodeEnum.EXCEPTION);
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(BusinessException.class)
    public <T> ResponseVo<T> bizExceptionHandler(HttpServletRequest request, BusinessException e) {
        log.warn("A biz exception occurred on the request. Error message: " + e.getMsg());

        return ResponseVo.create(e.getCode(), e.getMsg(),e.getMsg());
    }

    /**
     * 请求参数类型错误
     */
    @ExceptionHandler(TypeMismatchException.class)
    public <T> ResponseVo<T> typeMismatchExceptionHandler(HttpServletRequest request, TypeMismatchException e) {
        log.warn("Incorrect request parameter type.", e);

        return ResponseVo.create(RespCodeEnum.PARAM_TYPE_ERROR);
    }

    /**
     * 请求参数类型错误
     */
    @ExceptionHandler(BindException.class)
    public <T> ResponseVo<T> bindExceptionExceptionHandler(HttpServletRequest request, BindException e) {
        log.warn("Incorrect request parameter type.", e);

        String errMsg = null;
        if (null != e.getBindingResult().getFieldError()) {
            errMsg = e.getBindingResult().getFieldError().getDefaultMessage();
        }
        return ResponseVo.create(RespCodeEnum.PARAM_TYPE_ERROR.getCode(),
            errMsg == null ? RespCodeEnum.PARAM_TYPE_ERROR.getMsg() : errMsg,errMsg == null ? RespCodeEnum.PARAM_TYPE_ERROR.getMsgEnglish():errMsg);
    }

    /**
     * 请求方法不支持
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public <T> ResponseVo<T> httpRequestMethodNotSupportedExceptionHandler(HttpServletRequest request,
                                                                           HttpRequestMethodNotSupportedException e) {
        log.warn("Request method not supported.", e);

        return ResponseVo.create(RespCodeEnum.REQUEST_METHOD_ERROR);
    }

    /**
     * 请求参数格式错误
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public <T> ResponseVo<T> httpMessageNotReadableExceptionHandler(HttpServletRequest request,
                                                                    HttpMessageNotReadableException e) {
        log.warn("Incorrect format of request parameters.", e);

        return ResponseVo.create(RespCodeEnum.PARAM_FORMAT_ERROR);
    }

    /**
     * 请求参数校验失败
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public <T> ResponseVo<T> validateExceptionHandler(MethodArgumentNotValidException e) {
        log.warn("Request parameters validation failed.", e);

        String errMsg = null;
        if (null != e.getBindingResult().getFieldError()) {
            errMsg = e.getBindingResult().getFieldError().getDefaultMessage();
        }
        return ResponseVo.create(RespCodeEnum.PARAM_ERROR.getCode(),
            errMsg == null ? RespCodeEnum.PARAM_ERROR.getMsg() : errMsg,errMsg == null ? RespCodeEnum.PARAM_ERROR.getMsgEnglish() : errMsg);
    }

    static final String FILE_SIZE_OUT_LIMIT = "上传文件大小超出限制!";

    /**
     * 上传文件大小超出限制
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public <T> ResponseVo<T> httpMessageNotReadableExceptionHandler(HttpServletRequest request,
                                                                    MaxUploadSizeExceededException e) {
        log.warn("File size out of limit.", e);
        return ResponseVo.create(RespCodeEnum.PARAM_ERROR.getCode(),
            RespCodeEnum.PARAM_ERROR.getMsg() + FILE_SIZE_OUT_LIMIT,RespCodeEnum.PARAM_ERROR.getMsgEnglish() + FILE_SIZE_OUT_LIMIT);
    }
}
