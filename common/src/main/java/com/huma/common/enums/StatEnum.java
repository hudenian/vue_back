package com.huma.common.enums;

/**
 * @author hudenian
 * @date 2021/6/9
 */
public enum StatEnum {
    /**
     * 无效
     */
    INVALID((byte) 0),

    /**
     * 有效
     */
    VALID((byte) 1);

    private final byte status;

    StatEnum(byte status) {
        this.status = status;
    }

    public byte getStatus() {
        return status;
    }
}
