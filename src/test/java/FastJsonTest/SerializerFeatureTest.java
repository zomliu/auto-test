package FastJsonTest;

import FastJson.Word;
import FastJson.User;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.junit.Test;

import java.util.*;

public class SerializerFeatureTest {
    public static Word init(){
        User user1=new User("张三",20);
        User user2=new User(null,18);
        User user3=new User("李四",18);
        Word word=new Word();
        word.setA("a");
        word.setB(2);
        word.setC(true);
        word.setDate(new Date());
        List<User> list = new ArrayList<User>();
        List<User> list1 = new ArrayList<User>();
        list.add(user3);
        list.add(user2);
        list.add(user1);
        list1.add(null);
        word.setList(list);
        //word.setList(list1);
        Map<String, Object> userMap = new HashMap<String, Object>();
        Map<String, Object> userMap1 = new HashMap<String, Object>();
        userMap.put("test1", "test1");
        userMap.put("test2", "test2");
        userMap.put("test3", "test3");
        userMap.put("test4", null);
        word.setMap(userMap);
        //word.setMap(userMap1);
        return word;
    }

    @Test
     public void prettyFormat() {
        System.out.println(JSON.toJSONString(init(), SerializerFeature.PrettyFormat));
    }

    @Test
    public void showJsonBySelf(){
        System.out.println(JSON.toJSONString(init(),SerializerFeature.PrettyFormat,SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty,SerializerFeature.WriteNullListAsEmpty));
    }

    /**
     * 8:
     * 将对象转为array输出
     */
    @Test
    public  void beanToArray() {
        System.out.println(JSON.toJSONString(init(), SerializerFeature.BeanToArray,SerializerFeature.PrettyFormat));
    }

    /**
     *格式化日期的几种方式
     */
    @Test
    public  void writeDateUseDateFormat() {
        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd";
        System.out.println(JSON.toJSONString(init(), SerializerFeature.WriteDateUseDateFormat,SerializerFeature.PrettyFormat));
    }
    @Test
    public  void writeDateUseDateFormat1() {
        System.out.println(JSON.toJSONString(init(), SerializerFeature.WriteDateUseDateFormat,SerializerFeature.PrettyFormat));
    }
    @Test
    public  void toJSONStringWithDateFormat() {
        System.out.println(JSON.toJSONStringWithDateFormat(init(), "yyyy-MM-dd HH:mm:ss.SSS" ));
    }
    @Test
    public  void useISO8601DateFormat() {
        System.out.println(JSON.toJSONString(init(), SerializerFeature.UseISO8601DateFormat,SerializerFeature.PrettyFormat));
    }
    @Test
    public  void sortField() {
        System.out.println(JSON.toJSONString(init(), SerializerFeature.SortField,SerializerFeature.PrettyFormat));
    }

    @Test
    public  void mapSortField() {
        System.out.println(JSON.toJSONString(init(), SerializerFeature.MapSortField,SerializerFeature.PrettyFormat));
    }
    @Test
    public  void useSingleQuotes() {
        System.out.println(JSON.toJSONString(init(), SerializerFeature.UseSingleQuotes,SerializerFeature.PrettyFormat));
    }
}
