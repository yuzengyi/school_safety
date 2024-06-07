package com.example.springboot.utils;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class SHA256Util {

    // 将输入的密码进行SHA-256加密
    public static String encryptPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(encodedHash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error occurred while encrypting password", e);
        }
    }

    // 将字节数组转换为16进制字符串
    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
