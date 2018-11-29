package sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FundSignTest {
    public static String signWithMD5(String jsonString,String secret){

        try {

            JSONObject jsonObjectTest = JSON.parseObject(jsonString);
            String result=JSON.toJSONString(jsonObjectTest, SerializerFeature.MapSortField);
            JSONObject jsonObject = JSON.parseObject(result, Feature.OrderedField);
            String signSrc = buildSignSrc(jsonObject);
            String signt = signSrc + secret;
            System.out.println(signt);
            String sign = md5Str(signt).toLowerCase();
            return sign;
        } catch (Exception e) {
            throw e;
        }

    }

    private static String buildSignSrc(JSONObject jsonObject) {
        StringBuffer content = new StringBuffer();
        for(String key:jsonObject.keySet()){
            String values = jsonObject.getString(key);
            if(StringUtils.isBlank(values)){
                continue;
            }
            content.append("&" + key + "=" + values);
        }
        String signSrc = content.toString();
        if (signSrc.startsWith("&")) {
            signSrc = signSrc.replaceFirst("&", "");
        }
        return signSrc;
    }
    public static String md5Str(String str) {
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
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }
    public static void main(String args[]) throws Exception{
        String jsonString = "{\"loan\":{\"repaymentCycleUnit\":\"MONTH\",\"loanUsage\":\"07\",\"grantAmount\":119,\"repaymentMode\":\"DEBX\",\"repaymentCycleNum\":1,\"interest\":0.356521,\"contractSignDate\":1542359869000,\"contractAmount\":119,\"periods\":3,\"firstDueDate\":1545019200000,\"lastDueDate\":1550376000000,\"loanContractNo\":\"JK1000118220181116171601\"},\"partnerCode\":\"HAOHUAN\",\"applicationNo\":\"\",\"sign\":\"21f59d81baad1b26beee1a598fb6b3e7\",\"productKey\":\"8077C5B49A184E6DBA0233540E91E431\",\"requestSerialNo\":\"201811168113836255139280160\",\"serviceVersion\":\"1.0\",\"requestTime\":1542360270533,\"sourceCode\":\"PARTNER_VERIFY\",\"bank\":[{\"bankCode\":103,\"accountName\":\"北京微财科技有限公司\",\"bankCardNo\":\"698664400\",\"bankReservedMobile\":\"\",\"usage\":1,\"bankCardType\":1,\"bankName\":\"中国民生银行\"},{\"bankCode\":\"101\",\"accountName\":\"黄冠南\",\"bankCardNo\":\"6217003810048135380\",\"bankReservedMobile\":\"18612348989\",\"usage\":2,\"bankCardType\":1,\"bankName\":\"中国建设银行\"}],\"stage\":\"LOAN\",\"partnerApplNo\":\"10001182\",\"user\":{\"firstContactMobile\":\"18800000002\",\"zipCode\":\"999999\",\"liveAddress\":\"中关村科技园区A座18层\",\"gender\":\"0\",\"extraUserInfo\":\"{\\\"isSumOverdueGt3t\\\":false,\\\"partnerCreditScore\\\":\\\"E\\\",\\\"verificationInfo\\\":\\\"https://babel-read.test.rrdbg.com/babel/v1/record/CALL_HISTORY?jobid=887a05c3-34a7-4794-994c-eae23f0adfd1&systemid=HAO_HUAN\\\",\\\"currentBalance\\\":0,\\\"ipAddress\\\":\\\"10.10.21.194\\\",\\\"isMaxOverdueGt10d\\\":false,\\\"zaCustType\\\":2,\\\"deviceNumber\\\":\\\"PDL-7p\\\",\\\"hasNotSettled\\\":false,\\\"isOverdueGt1d\\\":false,\\\"partnerLoanCount\\\":1,\\\"creditAmount\\\":\\\"3600.00\\\",\\\"firstLoanTime\\\":1542280118000}\",\"idCardNo\":\"44138119880318213X\",\"firstContactName\":\"测试002\",\"liveProvince\":\"2\",\"userName\":\"黄冠南\",\"birthDate\":574617600000,\"liveArea\":\"384\",\"liveCity\":\"36\",\"firstContactRelation\":\"2\",\"userMobile\":\"18612348989\",\"partnerUserId\":\"a354a250ba8c70dd722533580b75415c\",\"postCode\":\"999999\",\"maritalStatus\":\"10\",\"monthlyIncome\":\"1\"}}";
        String sign = null;
        sign = signWithMD5(jsonString,"123456");
        System.out.println(sign);
    }

}
