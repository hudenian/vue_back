package com.huma.common.exception;

import com.huma.common.enums.RespCodeEnum;

/**
 * @author hudenian
 * @date 2021/6/11
 */
public class BusinessException extends RuntimeException {
    private int code;
    private String msg;
    private String msgEnglish;

    public BusinessException() {
        super();
    }

    public BusinessException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public BusinessException(int code, String msg, String msgEnglish) {
        super(msg);
        this.code = code;
        this.msg = msg;
        this.msg = msgEnglish;
    }

    public BusinessException(RespCodeEnum responseEnum) {
        super(responseEnum.getMsg());
        this.code = responseEnum.getCode();
        this.msg = responseEnum.getMsg();
        this.msgEnglish = responseEnum.getMsgEnglish();
    }

    public BusinessException(RespCodeEnum responseEnum, String msg, String msgEnglish) {
        super(msg);
        this.code = responseEnum.getCode();
        this.msg = msg;
        this.msgEnglish = msgEnglish;
    }

    public BusinessException(RespCodeEnum responseEnum, String msg) {
        super(msg);
        this.code = responseEnum.getCode();
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public String getMsgEnglish() {
        return msgEnglish;
    }

}
