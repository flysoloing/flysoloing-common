package com.flysoloing.common.security;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 安全哈希算法工具类
 *
 * @author laitao
 * @since 2014-05-03 23:21:00
 */
public class SHAUtils {

    /**
     * SHA-1消息摘要算法，返回字节数组
     *
     * @param data 字节数组
     * @return 字节数组
     * @throws Exception
     *
     * @deprecated
     */
    @Deprecated
    public static byte[] encodeSHA(byte[] data) throws Exception {
        return DigestUtils.sha(data);
    }

    /**
     * SHA-1消息摘要算法，返回十六进制字符串
     *
     * @param data 字节数组
     * @return 十六进制字符串
     * @throws Exception
     *
     * @deprecated
     */
    @Deprecated
    public static String encodeSHAHex(byte[] data) throws Exception {
        return DigestUtils.shaHex(data);
    }

    /**
     * SHA-256消息摘要算法，返回字节数组
     *
     * @param data 字节数组
     * @return 字节数组
     * @throws Exception
     */
    public static byte[] encodeSHA256(byte[] data) throws Exception {
        return DigestUtils.sha256(data);
    }

    /**
     * SHA-256消息摘要算法，返回十六进制字符串
     *
     * @param data 字节数组
     * @return 十六进制字符串
     * @throws Exception
     */
    public static String encodeSHA256Hex(byte[] data) throws Exception {
        return DigestUtils.sha256Hex(data);
    }

    /**
     * SHA-384消息摘要算法，返回字节数组
     *
     * @param data 字节数组
     * @return 字节数组
     * @throws Exception
     */
    public static byte[] encodeSHA384(byte[] data) throws Exception {
        return DigestUtils.sha384(data);
    }

    /**
     * SHA-384消息摘要算法，返回十六进制字符串
     *
     * @param data 字节数组
     * @return 十六进制字符串
     * @throws Exception
     */
    public static String encodeSHA384Hex(byte[] data) throws Exception {
        return DigestUtils.sha384Hex(data);
    }

    /**
     * SHA-512消息摘要算法，返回字节数组
     *
     * @param data 字节数组
     * @return 字节数组
     * @throws Exception
     */
    public static byte[] encodeSHA512(byte[] data) throws Exception {
        return DigestUtils.sha512(data);
    }

    /**
     * SHA-512消息摘要算法，返回十六进制字符串
     *
     * @param data 字节数组
     * @return 十六进制字符串
     * @throws Exception
     */
    public static String encodeSHA512Hex(byte[] data) throws Exception {
        return DigestUtils.sha512Hex(data);
    }

}
