package com.flysoloing.common.security;

import org.apache.commons.lang.RandomStringUtils;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

/**
 * 密码工具类
 *
 * @author laitao
 * @since 2015-03-04 23:33:00
 */
public class PasswordUtils {

    private static final int SALT_BYTE_SIZE = 24;

    private static final String CHARSET_UTF_8 = "UTF-8";

    /**
     * 创建带有Salt值的Hash值
     *
     * @param password 用户输入的密码明文
     * @return 安全哈希后的密码摘要
     * @throws Exception
     */
    public static String createHash(String password) throws Exception {
        return createHash(password.getBytes(Charset.forName(CHARSET_UTF_8)));
    }

    /**
     * 创建带有Salt值的Hash值
     *
     * @param password 用户输入的密码明文
     * @return 安全哈希后的密码摘要
     * @throws Exception
     */
    public static String createHash(byte[] password) throws Exception {
//        String saltStr = RandomStringUtils.randomAscii(SALT_BYTE_SIZE);
//        String saltStr = RandomStringUtils.randomAlphabetic(SALT_BYTE_SIZE);
        String saltStr = RandomStringUtils.randomAlphanumeric(SALT_BYTE_SIZE);
//        String saltStr = RandomStringUtils.randomNumeric(SALT_BYTE_SIZE);
//        String saltStr = RandomStringUtils.random(SALT_BYTE_SIZE);
        byte[] salt = saltStr.getBytes(Charset.forName(CHARSET_UTF_8));

        //把salt加到password的前面，或者是把password加到salt的后面
        return new String(salt, Charset.forName(CHARSET_UTF_8)) + ":" + SHAUtils.encodeSHA256Hex(mergeByteArray(salt, password));
    }

    /**
     * 验证密码是否相等
     *
     * @param password 用户输入的密码明文
     * @param correctHash 数据库存储的密码摘要
     * @return true：相等；false：不等
     * @throws Exception
     */
    public static boolean validatePassword(String password, String correctHash) throws Exception {
        return validatePassword(password.getBytes(CHARSET_UTF_8), correctHash);
    }

    /**
     * 验证密码是否相等
     *
     * @param password 用户输入的密码明文
     * @param correctHash 数据库存储的密码摘要
     * @return true：相等；false：不等
     * @throws Exception
     */
    public static boolean validatePassword(byte[] password, String correctHash) throws Exception {
        String[] hashes = correctHash.split(":");
        byte[] salt = hashes[0].getBytes(Charset.forName(CHARSET_UTF_8));
        String originalHash = hashes[1];
        //把salt加到password的前面，或者是把password加到salt的后面
        String currentHash = SHAUtils.encodeSHA256Hex(mergeByteArray(salt, password));

        return originalHash.equals(currentHash);
    }

    /**
     * 合并字节数组
     *
     * @param bytes1 待合并字节数组1
     * @param bytes2 待合并字节数组2
     * @return 合并后的字节数组
     */
    public static byte[] mergeByteArray(byte[] bytes1, byte[] bytes2) {
        byte[] bytes3 = new byte[bytes1.length + bytes2.length];
        System.arraycopy(bytes1, 0, bytes3, 0, bytes1.length);
        System.arraycopy(bytes2, 0, bytes3, bytes1.length, bytes2.length);
        return bytes3;
    }

    /**
     * 将字节数组转换为字符数组
     *
     * @param bytes 字节数组
     * @return 字符数组
     *
     * @deprecated
     */
    @Deprecated
    private static char[] getChars(byte[] bytes) {
        Charset cs = Charset.forName (CHARSET_UTF_8);
        ByteBuffer bb = ByteBuffer.allocate (bytes.length);
        bb.put (bytes);
        bb.flip ();
        CharBuffer cb = cs.decode (bb);

        return cb.array();
    }
}
