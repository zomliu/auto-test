package alisa;

import org.apache.commons.codec.binary.Base64;

import java.io.*;

public class TrunTest {
    public static void main(String[] args) throws IOException {

        String fileName = TrunTest.class.getClassLoader().getResource("trunTest.txt").getPath();//获取文件路径
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));

        String context = "";
        String str;
        while((str = bufferedReader.readLine()) != null){
            System.out.println(str);//此时str就保存了一行字符串
            context = context + str;
        }

        byte[] bFile = Base64.decodeBase64(context);
        try {
            ByteArrayInputStream byteInputStream = new ByteArrayInputStream(bFile);
            BufferedInputStream bis = new BufferedInputStream(byteInputStream);
            File file = new File("C:\\temp\\file.pdf");
            // 获取文件的父路径字符串
            File path = file.getParentFile();
            if (!path.exists()) {
                System.out.println("文件夹不存在，创建, path:" + path);
                boolean isCreated = path.mkdirs();
                if (!isCreated) {
                    System.out.println("创建文件夹失败，path=" + path);
                }
            }
            FileOutputStream fos = new FileOutputStream(file);
            // 实例化OutputString 对象
            BufferedOutputStream output = new BufferedOutputStream(fos);
            byte[] buffer = new byte[1024];
            int length = bis.read(buffer);
            while (length != -1) {
                output.write(buffer, 0, length);
                length = bis.read(buffer);
            }
            output.flush();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("失败"+ e);
        }

    }

}
