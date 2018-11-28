package alisa;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class FundSignTest {
    public static String signWithMD5(JSONObject jsonObject, String secret) throws Exception   {

        try {
            // 生成待签名串
            String signSrc = buildSignSrc(jsonObject);
            String signt = signSrc + secret;
            System.out.println(signt);
            String sign = md5Str(signt).toLowerCase();
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
    public static String getSignFromJsonString(String parmas ) throws Exception {
        String sign = signWithMD5(JSON.parseObject(parmas), "123456");
        JSONObject json = JSONObject.parseObject(parmas);
        json.put("sign",sign);
        return json.toJSONString();
    }
    public static void main(String[] args) throws Exception {
        String jsonString = "{'serviceVersion':'1.0','sourceCode':'PARTNER_HAOHUAN','requestTime':1539575972935,'requestSerialNo':'TEST_1011699','applicationNo':'','partnerApplNo':'test3064080','partnerCode':'HAOHUAN','productKey':'euDJWkKsreHRmiTREWQ','stage':'LOAN','bank':[ { 'accountName':'刘试','bankAddress':'测试地址12211','bankBranchName':'bank_branch_name','bankCardNo':'6210811234567001910','bankCity':'11','bankCode':'101','bankName':'中国建设银行','bankProvince':'11','bankReservedMobile':'18500410022','usage':2, 'bankCardType':1 }, { 'accountName':'刘试','bankAddress':'测试地址1221881','bankBranchName':'bank_branch_name','bankCardNo':'6210811234567001910','bankCity':'11','bankCode':'101','bankName':'中国建设银行','bankProvince':'11','bankReservedMobile':'18500410022','usage':1, 'bankCardType':1 } ], 'loan':{ 'contractAmount':2000, 'contractSignDate':1539575972935,'firstDueDate':1539575972935,'grantAmount':2000, 'interest':0.1, 'lastDueDate':1542252793000,'loanContractNo':'8748719','loanUsage':'使用用途','periods':2, 'repaymentCycleNum':10, 'repaymentCycleUnit':'MONTH','repaymentMode':'DEBX' }, 'user':{'partnerUserId':'zouzouzozu1','idCardNo':'411424199012301101','companyName':'非洲雄狮有限公司','mateMobile':'','liveCity':'1101','idCardValidityEndDate':1857871993000,'firstContactMobile':'18700010302','birthDate':662527993000,'nickName':'zou11','secondContactRelation':'1','highestEducation':'10','nationality':'','extraUserInfo':'','email':'','postAddress':'','mateName':'','companyCity':'','companyPhoneNo':'','userName':'刘试','gender':'0','companyArea':'','firstContactName':'王长柱','firstContactRelation':'1','zipCode':'100102','jobTitle':'3','highestDegree':'4','maritalStatus':'10','liveConditions':'5','jobType':'1','liveArea':'110101','mateIdCardNo':'','liveAddress':'北京市朝阳区永和园花家地小区33号楼203','jobDuty':'1','companyProvince':'','secondContactMobile':'18612345678','monthlyIncome':'5000', 'secondContactName':'王大锤','companyAddress':'','liveProvince':'11','postCode':'102001','companyIndustry':'17','mateCompany':'','userMobile':'15609260011','extraUserInfo':'{\\\"currentBalance\\\":\\\"0\\\",\\\"hasNotSettled\\\":false,\\\"firstLoanTime\\\":1539575972935,\\\"zaCustType\\\":false,\\\"isSumOverdueGt3t\\\":false,\\\"isMaxOverdueGt10d\\\":false,\\\"isOverdueGt1d\\\":false,\\\"verificationInfo\\\":\\\"https://iamlihao.cf/php/test.html\\\",\\\"partnerCreditScore\\\":\\\"750\\\",\\\"ipAddress\\\":\\\"172.16.2.39\\\",\\\"deviceNumber\\\":\\\"aaa\\\",\\\"shopNo\\\":\\\"aaa\\\",\\\"shopProvince\\\":\\\"1\\\",\\\"shopCity\\\":\\\"1\\\",\\\"shopDistrict\\\":\\\"1\\\",\\\"shopName\\\":\\\"shopName\\\",\\\"shopAddress\\\":\\\"shopAddress\\\",\\\"shopPhone\\\":\\\"010-58860499\\\",\\\"partnerLoanCount\\\":0,\\\"latitude\\\":\\\"108\\\",\\\"longitude\\\":\\\"35\\\"}'}}";
        String requestparam = null;
        try {
            requestparam = getSignFromJsonString(jsonString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(requestparam);
    }
}

