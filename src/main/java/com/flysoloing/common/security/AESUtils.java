package com.flysoloing.common.security;

import com.flysoloing.common.util.GsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 高级加密标准算法工具类
 *
 * @author laitao
 * @since 2015-03-06 22:31:00
 */
public class AESUtils {

    private static Logger logger = LoggerFactory.getLogger(AESUtils.class);

    private static final String CHARSET_UTF_8 = "UTF-8";

    private static String parseByte2HexStr(byte[] buf) {
        StringBuffer sb = new StringBuffer();
        for (byte aBuf : buf) {
            String hex = Integer.toHexString(aBuf & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }

        return sb.toString();
    }

    private static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) {
            return null;
        }

        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }

        return result;
    }

    public static String encrypt(String plaintext, String key) {
        try {
            KeyGenerator generator = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(key.getBytes(CHARSET_UTF_8));
            generator.init(128, secureRandom);
            SecretKey secretKey = generator.generateKey();

            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat, "AES");
            // 创建密码器
            Cipher cipher = Cipher.getInstance("AES");
            byte[] byteContent = plaintext.getBytes(CHARSET_UTF_8);

            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            // 加密
            byte[] result = cipher.doFinal(byteContent);

            return (new BASE64Encoder()).encode(result);
        } catch (Exception e) {
            logger.error("AES encrypt exception ", e);
        }

        return null;
    }

    public static String decrypt(String ciphertext, String key) {
        try {
            byte[] content = new BASE64Decoder().decodeBuffer(ciphertext);
            KeyGenerator generator = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(key.getBytes(CHARSET_UTF_8));
            generator.init(128, secureRandom);
            SecretKey secretKey = generator.generateKey();

            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            byte[] result = cipher.doFinal(content);

            return new String(result, CHARSET_UTF_8);
        } catch (Exception e) {
            logger.error("AES decrypt exception ", e);
        }

        return null;
    }

    public static void main(String[] args) {
        String key = "0123456789ABCDEF";
        System.out.println("key = " + key);
        String plainText = "我爱北京天安门123#$&%*";
        System.out.println("plainText1 = " + plainText);
        String cipherText = AESUtils.encrypt(plainText, key);
        System.out.println("cipherText = " + cipherText);
        plainText = "";
        System.out.println("plainText2 = " + plainText);
        plainText = AESUtils.decrypt(cipherText, key);
        System.out.println("plainText3 = " + plainText);

        String plainStr ="0123456789";
        System.out.println("plainStr1 = " + plainStr);
        String cipherStr = AESUtils.encrypt(plainStr, key);
        System.out.println("cipherStr1 = " + cipherStr);
        cipherStr = "";
        System.out.println("cipherStr2 = " + cipherStr);
        plainStr = "0123456780";
        System.out.println("plainStr3 = " + plainStr);
        cipherStr = AESUtils.encrypt(plainStr, key);
        System.out.println("cipherStr3 = " + cipherStr);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("string", "str");
        map.put("date", new Date());
        String plainmap = GsonUtils.toJson(map);
        System.out.println("plainmap1 = " + plainmap);
        String ciphermap = AESUtils.encrypt(plainmap, key);
        System.out.println("ciphermap1 = " + ciphermap);
        try {
            Thread.sleep(30 * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ciphermap = "";
        System.out.println("ciphermap2 = " + ciphermap);
        map.put("date", new Date());
        plainmap = GsonUtils.toJson(map);
        System.out.println("plainmap3 = " + plainmap);
        ciphermap = AESUtils.encrypt(plainmap, key);
        System.out.println("ciphermap3 = " + ciphermap);

    }
}
