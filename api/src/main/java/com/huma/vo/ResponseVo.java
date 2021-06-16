package com.huma.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.huma.common.constants.SysConstant;
import com.huma.common.enums.RespCodeEnum;
import com.huma.common.utils.LanguageContext;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author hudenian
 * @date 2021/6/8
 */
@Data
@ApiModel
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseVo<T> {
    @ApiModelProperty(value = "返回码", required = true)
    private final int code;

    @ApiModelProperty(value = "返回描述", required = true)
    private final String msg;

    @ApiModelProperty(value = "返回结果")
    private T data;

    private ResponseVo(int code, String msg, String msgEnglish) {
        this.code = code;
        this.msg = SysConstant.EN_US.equals(LanguageContext.get()) ? msgEnglish : msg;
    }

    private ResponseVo(int code, String msg, String msgEnglish, T data) {
        this(code, msg, msgEnglish);
        this.data = data;
    }

    private ResponseVo(RespCodeEnum respCodeEnum) {
        this(respCodeEnum.getCode(), respCodeEnum.getMsg(), respCodeEnum.getMsgEnglish());
    }

    private ResponseVo(RespCodeEnum respCodeEnum, T data) {
        this(respCodeEnum.getCode(), respCodeEnum.getMsg(), respCodeEnum.getMsgEnglish(), data);
    }

    public static <T> ResponseVo<T> createSuccess() {
        return new ResponseVo<>(RespCodeEnum.SUCCESS);
    }

    public static <T> ResponseVo<T> createSuccess(T data) {
        return new ResponseVo<>(RespCodeEnum.SUCCESS, data);
    }

    public static <T> ResponseVo<T> create(RespCodeEnum respCodeEnum) {
        return new ResponseVo<>(respCodeEnum);
    }

    public static <T> ResponseVo<T> create(int code, String msg, String msgEnglish) {
        return new ResponseVo<>(code, msg, msgEnglish);
    }

    public T getData() {
        return data;
    }
}
