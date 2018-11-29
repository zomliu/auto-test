package sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.TreeMap;

public class SignTest {
    public static String signWithMD5(String jsonStr, String secret){
        Map<String, String> treeMap = JSON.parseObject(jsonStr, new TypeReference<TreeMap<String, String>>(){});

        final StringBuffer context = new StringBuffer();

        treeMap.entrySet().stream()
                .forEach((Map.Entry entity) -> {
                    if (entity.getValue() != null && entity.getValue().toString().trim().length() > 0 ) {
                        context.append("&").append(entity.getKey()).append("=").append(entity.getValue());
                    }
                });
        String signSrc = context.toString();
        if (signSrc.startsWith("&")) {
            signSrc = signSrc.replaceFirst("&", "");
        }
        String sign = md5(signSrc + secret).toUpperCase();
        return sign;
    }
    public static String md5(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes("utf-8"));
            byte[] byteDigest = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < byteDigest.length; offset++) {
                i = byteDigest[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            // 32位加密
            return buf.toString();
            // 16位的加密
            // return buf.toSortString().substring(8, 24);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }
    public static String getSignFromJsonString(String parmas ) throws Exception {
        return signWithMD5(parmas, "12554501-003b-11e8-b5e2-000c290cc30f");
    }
    public static void main(String[] args) throws Exception {
        String jsonString = "{'a':'b','c':'d'}";
        String sign = null;
        try {
            sign = getSignFromJsonString(jsonString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(sign);
    }
}
