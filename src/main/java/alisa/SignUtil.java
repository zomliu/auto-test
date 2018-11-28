package alisa;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;




public class SignUtil{

    public static String signature(TreeMap<String, String> params, String secret) {
        List<String> list = new ArrayList<String>();
        Iterator<Entry<String, String>> iter = params.entrySet().iterator();
        while (iter.hasNext()) {
            Entry<String, String> entry = (Entry<String, String>) iter.next();
            String key = entry.getKey();
            String val = entry.getValue();
            if (val != null && val.length() != 0 ) {
                list.add(key + "=" + val);
            }
        }
        StringBuilder buf = new StringBuilder();
        for (String s : list) {
            buf.append(s).append("&");
        }
        if (buf.length() > 0) {
            buf.deleteCharAt(buf.length() - 1);
        }
        buf.append(secret);//(3)append secret key
        String calMd5 = md5(buf.toString());
        return calMd5;
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
    public static void main(String[] args) {
        String secret = "c60de7822a0cee7d6bd152bc9c985000"; //secret key (32bit)
        TreeMap<String, String> map = new TreeMap<String, String>();
        map.put("partner", "HAOHUAN");
        map.put("partnerLoanNo", "7e074f49-12ad-4053-9969-0a74d4712bb8");
        String str = SignUtil.signature(map, secret).toLowerCase();
        System.out.println(str);
    }

}
