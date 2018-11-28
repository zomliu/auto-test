package alisa;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * <pre>
 * 接口签名的工具类
 * </pre>
 */

public class CustomerSign {
    /**
     * 生成签名
     *
     * @param jsonObject
     * @param secret
     * @return
     */
    public static String signWithMD5(JSONObject jsonObject, String secret) throws Exception   {

        try {
            // 生成待签名串
            String signSrc = buildSignSrc(jsonObject);
            String sign = md5(signSrc + secret).toUpperCase();
            return sign;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 生成待签名串
     *
     * @param jsonObject
     * @return
     */
    private static String buildSignSrc(JSONObject jsonObject) {
        // 按照key做首字母升序排列
        List<String> keys = new ArrayList<>(jsonObject.keySet());
        keys.sort(String.CASE_INSENSITIVE_ORDER);
        StringBuilder content = new StringBuilder();
        for (String key : keys) {
            String value = jsonObject.getString(key);
            // 空串值不参与签名
            if (value == null || value.length()==0) {
                continue;
            }
            content.append("&" + key + "=" + value);
        }

        String signSrc = content.toString();
        if (signSrc.startsWith("&")) {
            signSrc = signSrc.replaceFirst("&", "");
        }

        return signSrc;
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
         return signWithMD5(JSON.parseObject(parmas), "12554501-003b-11e8-b5e2-000c290cc30f").toUpperCase();
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