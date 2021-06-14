package com.huma.common.constants;

/**
 * @author hudenian
 * @date 2021/4/12
 */
public class SysConstant {

    /**
     * 请求头token key值
     */
    public static final String HEADER_TOKEN_KEY = "Access-Token";

    /**
     * redis数据库 key值 用户前缀
     */
    public static final String REDIS_USER_PREFIX_KEY = "User:";

    /**
     * redis数据库 key值 Token前缀
     */
    public static final String REDIS_TOKEN_PREFIX_KEY = "Token:";

    public static final String CLASSPATH = "classpath:";

    /**
     * 系统默认日期格式
     */
    public static final String DEFAULT_DATE_PATTERN = "yyyy/MM/dd";

    /**
     * 系统默认时间格式
     */
    public static final String DEFAULT_TIME_PATTERN = "yyyy/MM/dd HH:mm:ss";

    /**
     * 时区默认东八区北京时间
     */
    public static final String DEFAULT_TIMEZONE = "GMT+8";

    /**
     * 图片类型前缀
     */
    public static final String IMAGE_FILE_TYPE_PREFIX = "image";
}
