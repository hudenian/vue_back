package com.huma.common.exception;

import com.huma.common.enums.RespCodeEnum;

/**
 * @author Oliver
 * @date 2021/4/9
 */
public class BusinessException extends RuntimeException {
    private int code;
    private String msg;

    public BusinessException() {
        super();
    }

    public BusinessException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public BusinessException(RespCodeEnum responseEnum) {
        super(responseEnum.getMsg());
        this.code = responseEnum.getCode();
        this.msg = responseEnum.getMsg();
    }

    public BusinessException(RespCodeEnum responseEnum, String msg) {
        super(msg);
        this.code = responseEnum.getCode();
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
