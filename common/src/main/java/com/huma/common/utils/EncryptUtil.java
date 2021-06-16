package com.huma.common.utils;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;

/**
 * @author hudenian
 * @date 2021/6/11
 */
public class EncryptUtil {
    public static String encryptMD5(String source) {
        if (source == null) {
            source = "";
        }
        return DigestUtils.md5Hex(source);
    }

    //把初始密码MD5后的16进制字符串，再加上loginName后再次MD5的

    /**
     * 加密分3步骤
     * 1. 得到初始密码的md5的16进制字符串
     * 2. 把1的结果，加上loginName，再次m5得到16进制字符串
     * 3. 把2的结果，加上盐的16进制字符串后，再次MD5得到16进制字符串
     * 把3得到的字符串作为密码存储
     *
     * @param initPassword  初始密码
     * @param loginName     用户名
     * @param saltHexString 盐的16进制字符串
     * @return 用以存储的密码信息
     */
    public static String encodePassword(String initPassword, String loginName, String saltHexString) {
        //进行MD5加密,一次直接密码加密，二次:一次结果加上account加密，三次:二次结果加上uuid加密
        String passMd5First = DigestUtils.md5Hex(initPassword);
        String passMd5Second = DigestUtils.md5Hex(passMd5First + loginName);
        System.out.println("passMd5Second is:" + passMd5Second);
        return getStoredPassword(passMd5Second, saltHexString);
    }

    /**
     * 加密分3步骤
     * 1. 得到初始密码的md5的16进制字符串
     * 2. 把1的结果，加上盐的16进制字符串后，再次MD5得到16进制字符串
     * 把3得到的字符串作为密码存储
     *
     * @param initPassword  初始密码
     * @param saltHexString 盐的16进制字符串
     * @return 用以存储的密码信息
     */
    public static String encodePassword(String initPassword, String saltHexString) {
        //进行MD5加密,一次直接密码加密，二次:二次结果加上uuid加密
        String passMd5First = DigestUtils.md5Hex(initPassword);
        return getStoredPassword(passMd5First, saltHexString);
    }

    public static String getStoredPassword(String passMd5Second, String saltHexString) {
        //进行MD5加密,一次直接密码加密，二次:一次结果加上account加密，三次:二次结果加上uuid加密
        return DigestUtils.md5Hex(passMd5Second + saltHexString);
    }

    /**
     * 参考encodePassword()，客户端已经把初始密码处理了2次，发送到后台检验与存储的密码信息是否相符
     *
     * @param passMd5Second  已经处理了2次	的初始密码信息(16进制字符串)
     * @param storedPassword 存储的密码信息(16进制字符串)
     * @param saltHexString  存储的对应的盐(16进制字符串)
     * @return
     */
    public static boolean verifyPassword(String passMd5Second, String storedPassword, String saltHexString) {
        String password = DigestUtils.md5Hex(passMd5Second + saltHexString);
        return StringUtils.equalsIgnoreCase(password, storedPassword);
    }

    /**
     * 获取加密盐
     *
     * @return 盐
     */
    public static String getSalt() {
        // 生成随机数,是个byte[]，转成hex 字符串后，作为salt
        SecureRandom random = new SecureRandom();
        byte[] saltByte = new byte[24];
        // 产生24个字节的随机数
        random.nextBytes(saltByte);
        return Hex.encodeHexString(saltByte);
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
//		String initPassword = "123456";
        String initPassword = "123456";
        String loginName = "hushiwu";
        String stored = EncryptUtil.encodePassword(initPassword, loginName, "9e1bb7355e7afa47768e32611fbc1f13f6c44dd797250844");
        //原始保存的密码：126d7ac74295c7c892087010d35b438d
        //"123456" -stored:87cce9c8bc7eea015d594e5d3e5c8dba
        //"admin123" -stored:126d7ac74295c7c892087010d35b438d
        System.out.println("stored:" + stored);

        String passMd5First = DigestUtils.md5Hex(initPassword);
        String passMd5Second = DigestUtils.md5Hex(passMd5First + loginName);
        System.out.println("passMd5Second:" + passMd5Second);

		/*SecureRandom random = new SecureRandom();
		byte[] saltByte = new byte[24];
		random.nextBytes(saltByte);

		String saltHexString = Hex.encodeHexString(saltByte);
		System.out.println(saltHexString);

		String saltedPassword = EncryptUtils.encodePassword(initPassword, loginName, saltHexString);

		System.out.println("saltedPassword:" + saltedPassword);

		String passMd5Frist = DigestUtils.md5Hex(initPassword);
		String passMd5Second = DigestUtils.md5Hex(passMd5Frist + loginName);
		System.out.println("passMd5Second?" +passMd5Second);
		System.out.println("right?" + EncryptUtils.verifyPassword(passMd5Second, saltedPassword, saltHexString));*/

    }

}
