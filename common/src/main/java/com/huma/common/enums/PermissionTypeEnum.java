package com.huma.common.enums;

/**
 * @author huma
 * @date 2021/6/11
 */
public enum PermissionTypeEnum {
    /**
     * 1-目录, 2-菜单, 3-按钮
     */
    DIRECTORY((byte) 1, "目录"),

    /**
     * 菜单
     */
    MENU((byte) 2, "菜单"),

    /**
     * 按钮
     */
    BUTTON((byte) 3, "按钮");

    private final byte code;
    private final String desc;

    PermissionTypeEnum(byte code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public byte getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}
