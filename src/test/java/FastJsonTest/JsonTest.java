package FastJsonTest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.junit.Test;

public class JsonTest {
    private static final String  jsontest = "{\"teacherName\":\"crystall\",\"teacherAge\":27,\"course\":{\"courseName\":\"english\",\"code\":1270},\"students\":[{\"studentName\":\"lily\",\"studentAge\":12},{\"studentName\":\"lucy\",\"studentAge\":15}]}";

    /**
     * 计算学生的年龄
     */
    @Test
    public void testComplexJSONStrToJSONObject(){
        int sum=0;
        JSONObject jsonObject= JSON.parseObject(jsontest);
        JSONArray jsonArray=jsonObject.getJSONArray("students");
        for(int i=0;i<jsonArray.size();i++){
            JSONObject jsonObject1=jsonArray.getJSONObject(i);
            sum=sum+jsonObject1.getInteger("studentAge");
        }
        System.out.print(sum);
    }

    /**
     * 去掉字符串的反斜杠
     */
    @Test
    public void test1(){
        JSONObject jsonObject=JSON.parseObject(jsontest);
        System.out.print(JSON.toJSONString(jsonObject,SerializerFeature.PrettyFormat));
    }

    /**
     * 去掉字符串的反斜杠，key使用单引号
     */
    @Test
    public void test2(){
        JSONObject jsonObject=JSON.parseObject(jsontest);
        System.out.print(JSON.toJSONString(jsonObject,SerializerFeature.PrettyFormat,SerializerFeature.UseSingleQuotes));
    }
}
