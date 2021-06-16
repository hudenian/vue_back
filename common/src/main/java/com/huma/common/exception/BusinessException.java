package com.huma.common.exception;

import com.huma.common.constants.SysConstant;
import com.huma.common.enums.RespCodeEnum;
import com.huma.common.utils.LanguageContext;

/**
 * @author hudenian
 * @date 2021/6/11
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

    public BusinessException(int code, String msg, String msgEnglish) {
        super(msg);
        this.code = code;
        this.msg = SysConstant.EN_US.equals(LanguageContext.get()) ? msgEnglish : msg;
    }

    public BusinessException(RespCodeEnum responseEnum) {
        super(responseEnum.getMsg());
        this.code = responseEnum.getCode();
        this.msg = SysConstant.EN_US.equals(LanguageContext.get()) ? responseEnum.getMsg() : responseEnum.getMsgEnglish();
    }

    public BusinessException(RespCodeEnum responseEnum, String msg, String msgEnglish) {
        super(msg);
        this.code = responseEnum.getCode();
        this.msg = SysConstant.EN_US.equals(LanguageContext.get()) ? msgEnglish : msg;
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

}
