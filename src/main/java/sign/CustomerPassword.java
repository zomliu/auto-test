package sign;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class CustomerPassword {
    public static String decryptFromBase64(String content, String password) {
        try {
            byte[] decryptBytes = Base64.decodeBase64(content);
            byte[] s = decrypt(decryptBytes, password);
            return new String(s,"UTF-8");
        } catch (Exception e) {
            return content;
        }
    }
    public static byte[] decrypt(byte[] content, String password) {
        try {
            byte[] enCodeFormat = password.getBytes();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");// 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(content);
            return result; // 加密
        } catch (Exception e) {
            return null;
        }
    }
    public static void main(String[] args) throws Exception {
        String content = "hSgRlrAfw6CIlpwECRRLlll09a5r/e/s0mYSj0zAy9MkBE7tOTbF1MZQZphM+WA+mqE8Y3/Vktga3gJyF+B87YWfLfUUYw8P13RTplqM3b7VGhXf7MBhAfAVWh23ZAVB7D+8KCIxOgu4J6C9ye9W7X/G0HC835Q4FL4z0GXxoslM65EatX/I9rmMb4vV7U7JF6nVlf697eImmFLiKO9IJuOaZvtmr+czqFKJXOtGsJfehfK95vG6QOAO88VDQrw/";
        String password = "21df4ca0003b11e8b5e2000c290cc30f";
        String t = decryptFromBase64(content, password);
        System.out.println("解密后的响应结果："+t);
    }
}
