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
    SUCCESS(10000, "请求成功!"),

    /**
     * 失败
     */
    FAIL(20000, "请求失败!"),

    /**
     * 参数类型错误
     */
    PARAM_TYPE_ERROR(20001, "参数类型错误!"),

    /**
     * 请求方式错误
     */
    REQUEST_METHOD_ERROR(20002, "请求方式错误!"),

    /**
     * 参数格式错误
     */
    PARAM_FORMAT_ERROR(20003, "参数格式错误!"),

    /**
     * 请求参数错误
     */
    PARAM_ERROR(20004, "请求参数错误!"),

    /**
     * 请求头不包含token
     */
    UN_TOKEN(20005, "参数缺失:缺少TOKEN!"),

    /**
     * 用户未登录
     */
    UN_LOGIN(20006, "用户未登录!"),

    /**
     * 用户没有权限
     */
    UN_ROLE(20007, "当前用户没有权限!"),

    /**
     * 业务失败
     */
    BIZ_FAILED(20008, "业务失败!"),
    /**
     * 新手机号已注册
     */
    USER_UN_EXIST(20009, "新手机号已注册!"),

    /**
     * 系统异常
     */
    EXCEPTION(30000, "系统异常,请联系管理员!"),

    /**
     * 业务异常
     */
    BIZ_EXCEPTION(30001, "业务异常!");

    private final int code;
    private final String msg;

    RespCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
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
}
