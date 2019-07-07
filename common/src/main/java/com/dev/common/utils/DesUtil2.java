package com.dev.common.utils;

/**
 * Created by ok on 2019/6/5.
 */

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.nio.charset.StandardCharsets;
import java.security.spec.AlgorithmParameterSpec;

import static android.util.Base64.DEFAULT;

/**
 * Des加解密工具类
 *
 * @author Lee
 */
public class DesUtil2 {

//aaaaaa：密钥

//private  final byte[] DESkey = lmkProperties.getDesKey().getBytes();// 设置密钥，略去
//private  final byte[] DESkey = "abcdefgh".getBytes();// 设置密钥，略去  至少8位

//bbbb：偏移量
//private  final byte[] DESIV = lmkProperties.getDesIv().getBytes();// 设置向量，略去
//private  final byte[] DESIV = "12345678".getBytes();// 设置向量，略去  至少8位

    static AlgorithmParameterSpec iv = null;// 加密算法的参数接口，IvParameterSpec是它的一个实现
    private static SecretKey key = null;


    public DesUtil2(String desKey, String desIv) throws Exception {
        byte[] DESkey = desKey.getBytes("UTF-8");
        byte[] DESIV = desIv.getBytes("UTF-8");
        DESKeySpec keySpec = new DESKeySpec(DESkey);// 设置密钥参数
        iv = new IvParameterSpec(DESIV);// 设置向量
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");// 获得密钥工厂
        key = keyFactory.generateSecret(keySpec);// 得到密钥对象
    }

    /**
     * 加密
     *
     * @param data 待加密的数据
     * @return 加密后的数据
     * @throws Exception
     */
    public String encode(String data) throws Exception {
        Cipher enCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");// 得到加密对象Cipher
        enCipher.init(Cipher.ENCRYPT_MODE, key, iv);// 设置工作模式为加密模式，给出密钥和向量
        byte[] pasByte = enCipher.doFinal(data.getBytes("UTF-8"));
        //BASE64Encoder base64Encoder = new BASE64Encoder();
        return android.util.Base64.encodeToString(pasByte, DEFAULT); //Base64.getEncoder().encodeToString(pasByte);
        //return base64Encoder.encode(pasByte);
    }

    /**
     * 解密
     *
     * @param data 解密前的数据
     * @return 解密后的数据
     * @throws Exception
     */
    public String decode(String data) throws Exception {
        Cipher deCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        deCipher.init(Cipher.DECRYPT_MODE, key, iv);
        //BASE64Decoder base64Decoder = new BASE64Decoder();
        //byte[] pasByte = deCipher.doFinal(base64Decoder.decodeBuffer(data));
        byte[] decoded = android.util.Base64.decode(data.getBytes(StandardCharsets.UTF_8), DEFAULT);
        byte[] pasByte = deCipher.doFinal(decoded);
        return new String(pasByte, StandardCharsets.UTF_8);
    }

   /* public static void main(String[] args) throws Exception {
        DesUtil tools = new DesUtil("abcdefgh", "12345678");
        String data = "123789";
        System.out.println("加密:" + tools.encode(data));

        String data1 = tools.encode(data);

        System.out.println("解密:" + tools.decode(data1));
    }*/


}
