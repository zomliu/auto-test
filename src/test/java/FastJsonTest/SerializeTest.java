package FastJsonTest;
import com.alibaba.fastjson.JSON;
import FastJson.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SerializeTest {
    User user1=new User("张三",20);
    User user2=new User("李四",18);

    //JavaBean转Json字符串
    @Test
    public void test1(){
        String userJson = JSON.toJSONString(user1);
        System.out.print(userJson);
    }

    //List<JavaBean> 转 Json字符串
    @Test
    public void test2(){
        List<User> userlist=new ArrayList<User>();
        userlist.add(user1);
        userlist.add(user2);
        String userJson = JSON.toJSONString(userlist);
        System.out.print(userJson);
    }

    //Map<String, JavaBean> 转 Json字符串
    @Test
    public void test3(){
        Map<String, User> userMap = new HashMap<String, User>();
        userMap.put("user1",user1);
        userMap.put("user2",user2);
        String userJson = JSON.toJSONString(userMap,true);
        System.out.print(userJson);
    }

    //List<Map>转成JSON
    @Test
    public void test4(){
        Map<String, User> userMap = new HashMap<String, User>();
        userMap.put("user1",user1);
        userMap.put("user2",user2);
        List<Map<String,User>> userlist=new ArrayList<Map<String,User>>();
        userlist.add(userMap);
        String userJson = JSON.toJSONString(userlist,true);
        System.out.print(userJson);
    }

    //Map<List>转成JSON
    @Test
    public void test5(){
        List<User> userlist=new ArrayList<User>();
        userlist.add(user1);
        userlist.add(user2);
        Map<String, List<User>> listMap = new HashMap<String, List<User>>();
        listMap.put("userlist",userlist);
        String userJson = JSON.toJSONString(listMap);
        System.out.print(userJson);
    }

}
