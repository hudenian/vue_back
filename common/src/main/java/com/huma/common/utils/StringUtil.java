package com.huma.common.utils;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author hudenian
 * @date 2021/4/27
 */
public class StringUtil {

    public final static String RAIL = "-";
    public final static String NULL = "";
    public final static String NULLSTR = "null";

    private final static int[] SIZE_TABLE =
        {9, 99, 999, 9999, 99999, 999999, 9999999, 99999999, 999999999, Integer.MAX_VALUE};

    private StringUtil() {}

    /**
     * 在字符串两边添加小括号
     *
     * @param param
     *            需要在两边加上引号的字符串
     * @return java.lang.String
     */
    public static String addBrackets(String param) {
        return "(" + param + ")";
    }

    public static boolean isEmpty(List<?> list) {
        return null == list || list.size() == 0;
    }

    public static boolean isEmpty(String str) {
        return null == str || "".equals(str);
    }

    public static boolean isNotEmpty(String str) {
        return null != str && str.length() > 0;
    }

    public static boolean isEmpty(Set<?> set) {
        return null == set || set.size() == 0;
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return null == map || map.size() == 0;
    }

    public static boolean isEmpty(Object obj) {
        return null == obj;
    }

    public static boolean isEmpty(Object[] obj) {
        return null == obj || obj.length == 0;
    }

    public static boolean isNotEmpty(Object[] obj) {
        return null != obj && obj.length > 0;
    }

    public static String format(String str, Object... args) {
        if (isNotEmpty(str)) {
            MessageFormat mf = new MessageFormat(str);
            return mf.format(args);
        }
        return null;
    }

    /**
     * 计算一个整数的大小
     *
     * @param x
     *            整数值
     * @return 整数大小
     */
    public static int sizeOfInt(int x) {
        for (int i = 0;; i++) {
            if (x <= SIZE_TABLE[i]) {
                return i + 1;
            }
        }
    }

    /**
     * 判断字符串的每个字符是否相等
     *
     * @param str
     *            需要判断的字符串
     * @return boolean
     */
    public static boolean isCharEqual(String str) {
        return str.replace(str.charAt(0), ' ').trim().length() == 0;
    }

    /**
     * 确定字符串是否为数字
     *
     * @param str
     *            需要判断是否为数字的字符串
     * @return boolean
     */
    public static boolean isNumeric(String str) {
        for (int i = str.length(); --i >= 0;) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断字符串是否为空格、空(“)”或null。
     *
     * @param str
     *            需要判断的字符串
     * @return boolean
     */
    public static boolean equalsNull(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0 || NULLSTR.equalsIgnoreCase(str)) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((!Character.isWhitespace(str.charAt(i)))) {
                return false;
            }
        }
        return true;
    }
}
