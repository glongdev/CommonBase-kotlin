package com.dev.common.utils;

import android.text.TextUtils;
import android.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

import static android.util.Base64.DEFAULT;

/**
 * 加密解密工具类
 */
public class EncryptUtils2 {

    private static final String ENCRYPT_ALGORITHM_MODE = "AES/CBC/PKCS5Padding";

    public static String encrypt(String sSrc, String sKey, String sIv) throws Exception {
        if (TextUtils.isEmpty(sSrc)) {
            return null;
        }

        sKey = to16BitsString(sKey);
        sIv = to16BitsString(sIv);

        byte[] raw = sKey.getBytes();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance(ENCRYPT_ALGORITHM_MODE);//"算法/模式/补码方式"
        IvParameterSpec iv = new IvParameterSpec(sIv.getBytes());//使用CBC模式，需要一个向量iv，可增加加密算法的强度
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes());
        return Base64.encodeToString(encrypted, DEFAULT);//此处使用BASE64做转码功能，同时能起到2次加密的作用。
    }


    // 解密
    public static String decrypt(String sSrc, String sKey, String sIv) {
        try {
            if (TextUtils.isEmpty(sSrc)) {
                return null;
            }
            sKey = to16BitsString(sKey);
            sIv = to16BitsString(sIv);

            byte[] raw = sKey.getBytes(StandardCharsets.US_ASCII);
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance(ENCRYPT_ALGORITHM_MODE);
            IvParameterSpec iv = new IvParameterSpec(sIv.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] encrypted = Base64.decode(sSrc.getBytes(StandardCharsets.UTF_8), DEFAULT);//先用base64解密

            try {
                byte[] original = cipher.doFinal(encrypted);
                return new String(original, StandardCharsets.UTF_8);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.toString());
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * 获取16位的String 字符串
     */
    private static String to16BitsString(String str) {
        if (str == null) {
            return null;
        }
        if (str.length() == 16) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str);
        while (sb.length() < 16) {
            sb.append("0");
        }
        if (sb.length() > 16) {
            sb.setLength(16);
        }
        return sb.toString();
    }
}
