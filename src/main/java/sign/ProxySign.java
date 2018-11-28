package sign;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

public class ProxySign {

    public static String signature(String originalparametersString, String secret) {
        String[] parameters = originalparametersString.split("&");
        TreeMap SortedParametersWithoutSign = new TreeMap();
        // 将参数按字母排序
        for(int i = 0 ; i < parameters.length ; i ++){
            String[] pandvalue  =  parameters[i].split("=");
            if(pandvalue.length>1){
                SortedParametersWithoutSign.put(pandvalue[0],pandvalue[1]);
            }else{
                SortedParametersWithoutSign.put(pandvalue[0],null);
            }
        }
        List<String> list = new ArrayList<String>();
        Iterator<Entry<String, String>> iter = SortedParametersWithoutSign.entrySet().iterator();
        while (iter.hasNext()) {
            Entry<String, String> entry = (Entry<String, String>) iter.next();
            String key = entry.getKey();
            String val = entry.getValue();
            if (val != null && val.length() != 0) {
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
    public static String signGet(String testparams){
        return signature(testparams,"123456").toLowerCase();
    }
    public static void main(String[] args) {
        String tests = "partner=HAOHUAN&partnerLoanNo=7e074f49-12ad-4053-9969-0a74d4712bb8&requestTime=1525232954785";
        String str = signGet(tests);
        System.out.println(str);
    }

}
