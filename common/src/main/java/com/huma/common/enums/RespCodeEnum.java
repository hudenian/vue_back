package com.huma.common.enums;

/**
 * 返回码定义
 *
 * @author huma
 * @date 2021/5/9
 */
public enum RespCodeEnum {
    /**
     * 成功
     */
    SUCCESS(10000, "请求成功!", "SUCCESS"),

    /**
     * 失败
     */
    FAIL(20000, "请求失败!", "FAIL"),

    /**
     * 参数类型错误
     */
    PARAM_TYPE_ERROR(20001, "参数类型错误!", "Param type error"),

    /**
     * 请求方式错误
     */
    REQUEST_METHOD_ERROR(20002, "请求方式错误!", "Request method error"),

    /**
     * 参数格式错误
     */
    PARAM_FORMAT_ERROR(20003, "参数格式错误!", "Param format error"),

    /**
     * 请求参数错误
     */
    PARAM_ERROR(20004, "请求参数错误!", "Request param error"),

    /**
     * 请求头不包含token
     */
    UN_TOKEN(20005, "参数缺失:缺少TOKEN!", "Missing parameters TOKEN"),

    /**
     * 用户未登录
     */
    UN_LOGIN(20006, "用户未登录!", "User not login"),

    /**
     * 用户没有权限
     */
    UN_ROLE(20007, "当前用户没有权限!", "Current user no permission"),

    /**
     * 业务失败
     */
    BIZ_FAILED(20008, "业务失败!", "Business failed"),
    /**
     * 新手机号已注册
     */
    USER_UN_EXIST(20009, "用户不存在!", "User not exists"),

    /**
     * 系统异常
     */
    EXCEPTION(30000, "系统异常,请联系管理员!", "System exception,please contact the administrator"),

    /**
     * 业务异常
     */
    BIZ_EXCEPTION(30001, "业务异常!", "Business exception");

    private final int code;
    private final String msg;
    private final String msgEnglish;

    RespCodeEnum(int code, String msg, String msgEnglish) {
        this.code = code;
        this.msg = msg;
        this.msgEnglish = msgEnglish;
    }

    public static RespCodeEnum getByCode(int code) {
        for (RespCodeEnum e : values()) {
            if (e.getCode() == code) {
                return e;
            }
        }
        return null;
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
