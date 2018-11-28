package FastJsonTest;

import FastJson.User;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeserializerTest {
    User user1 = new User("张三",20);
    User user2 = new User("李四",18);

    /**
     * Json字符串 转 javaBean
     */

    @Test
    public void test1(){
        String userJson = JSON.toJSONString(user1);
        User user = JSON.parseObject(userJson, User.class);
        System.out.println(user.getUsername());
    }

    /**
     * Json字符串 转 List<JavaBean>
     */
    @Test
    public void test2(){
        List<User> userlist = new ArrayList();
        userlist.add(user1);
        userlist.add(user2);
        String userJson = JSON.toJSONString(userlist);
        List<User> userlist1=JSON.parseArray(userJson,User.class);

        for(User user: userlist1){
            System.out.print(user.getUsername());
            System.out.print(user.getAge());
        }

        // jdk8中推荐写法
        userlist1.forEach(user -> {
            System.out.print(user.getUsername());
            System.out.print(user.getAge());
        });
    }

    /**
     *  Json字符串 转 List<String>
     */
    @Test
    public void test3(){
        String json11 = "[\"北京\",\"上海\",\"深圳\",\"杭州\"]";
        List<String> stringList11 = JSON.parseArray(json11, String.class);

        for (String string11 : stringList11) {
            System.out.println("string11: " + string11);
        }

        // 简化写法1
        stringList11.forEach(System.out :: println);

        // 简化写法2
        stringList11.forEach(str -> System.out.println("string11: " + str));
    }


    /**
     * Json字符串 转 Map<String, JavaBean>
     */
    @Test
    public void test4(){
        Map<String, User> userMap = new HashMap<String, User>();
        userMap.put("user1",user1);
        userMap.put("user2",user2);
        String userJson = JSON.toJSONString(userMap);
        Map<String, User> userMap1 = JSON.parseObject(userJson, Map.class);

        for (String key : userMap1.keySet()) {
            System.out.println("key: " + key + " , value: " + userMap1.get(key));
        }

        // map循环简化写法
        userMap1.entrySet().forEach(entity -> System.out.println("key: " + entity.getKey() + " , value: " + entity.getValue()));
    }

    /**
     * Json字符串 转List<Map>
     */
    @Test
    public void test5() {
        String json17 = "[{\"浙江省会\":\"杭州\",\"湖南省会\":\"长沙\",\"江西省会\":\"南昌\"},{\"四川省会\":\"成都\",\"江苏省会\":\"南京\",\"安徽省会\":\"合肥\"}]";
        List<Map<String, Object>> list17 = JSON.parseObject(json17, new TypeReference<List<Map<String, Object>>>() {
        });

        for (Map<String, Object> map17 : list17) {
            for (String key : map17.keySet()) {
                System.out.println("key: " + key + " , value: " + map17.get(key));
            }
        }
    }

    /**
     * Json字符串 转 List<Map<String, JavaBean>>
     */
    @Test
    public void test6() {
        Map<String, User> userMap = new HashMap<String, User>();
        userMap.put("user1", user1);
        userMap.put("user2", user2);
        List<Map<String, User>> userlist = new ArrayList<Map<String, User>>();
        userlist.add(userMap);
        String userJson = JSON.toJSONString(userlist);
        List<Map<String, User>> list17 = JSON.parseObject(userJson, new TypeReference<List<Map<String, User>>>() {});

        for (Map<String, User> map22 : list17) {
            for (String key : map22.keySet()) {
                System.out.println("key: " + key + " , value { " + "username:" + map22.get(key).getUsername() + " age:" + map22.get(key).getAge() + " }");
            }
        }
    }

    /**
     * Json字符串 转 Map<String, List<JavaBean>>
     */
    @Test
    public void test7(){
        List<User> userlist=new ArrayList<User>();
        userlist.add(user1);
        userlist.add(user2);
        Map<String, List<User>> listMap = new HashMap<String, List<User>>();
        listMap.put("userlist",userlist);
        String userJson = JSON.toJSONString(listMap);
        Map<String, List<User>> listMap1=JSON.parseObject(userJson,new TypeReference<Map<String, List<User>>>(){});
        for(String key:listMap1.keySet()){
            for (User user:listMap1.get(key)){
                System.out.println("key: " + key + " , value { " + "username:" + user.getUsername()+ " age:" + user.getAge()+" }");
            }
        }
    }

    @Test
    public void test8(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("key1", "One");
        map.put("key2", "Two");
        JSONObject j = new JSONObject(map);
        j.put("key3", "Three");
        System.out.println(j.get("key1"));
        System.out.println(j.get("key2"));
        System.out.println(j.get("key3"));
    }

    @Test
    public void test9(){
        Map<String, Object> map = new HashMap();
        map.put("key1", "One");
        map.put("key2", "Two");
        Map<String, Object> map2 = new HashMap();
        map2.put("key1", "Three");
        map2.put("key2", "Four");
        List<Map<String, Object>> userlist = new ArrayList();
        userlist.add(map);
        userlist.add(map2);
        JSONArray j = JSONArray.parseArray(JSON.toJSONString(userlist));
        for(int i=0; i<j.size(); i++){
            System.out.println(j.get(i));
        }
    }
}
