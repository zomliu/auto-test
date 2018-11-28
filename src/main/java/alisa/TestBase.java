package alisa;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;

import java.io.File;

public class TestBase {
    public static void main(String[] args) {
        try {
            byte[] bytes = FileUtils.readFileToByteArray(new File("C:\\Users\\zoudongxia\\Desktop\\资金平台\\G-贷款合同.pdf"));
            //System.out.println(Base64.encodeBase64String(bytes));
            String str= new String (Base64.encodeBase64String(bytes));
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
