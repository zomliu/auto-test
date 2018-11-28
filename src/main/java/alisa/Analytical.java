package alisa;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import sign.CustomerPassword;

import java.util.ArrayList;
import java.util.List;

public class Analytical {
    public static void main(String[] args) throws Exception {
        String content = "hSgRlrAfw6CIlpwECRRLlll09a5r/e/s0mYSj0zAy9MkBE7tOTbF1MZQZphM+WA+mqE8Y3/Vktga3gJyF+B87YWfLfUUYw8P13RTplqM3b7VGhXf7MBhAfAVWh23ZAVB7D+8KCIxOgu4J6C9ye9W7X/G0HC835Q4FL4z0GXxoslM65EatX/I9rmMb4vV7U7JF6nVlf697eImmFLiKO9IJuOaZvtmr+czqFKJXOtGsJfehfK95vG6QOAO88VDQrw/";
        String password = "21df4ca0003b11e8b5e2000c290cc30f";
        String t = CustomerPassword.decryptFromBase64(content, password);
        JSONObject jso= JSON.parseObject(t);
        System.out.println("初始jsonObject:\n"+jso+"\n");
        JSONArray jsarr=jso.getJSONArray("taskStatus");
        System.out.println("jsonObject里面的jsonarray:\n"+jsarr+"\n");
        List<JSONObject> list = new ArrayList<JSONObject>();
        for(int index=0;index<jsarr.size();index++)
        {
            list.add(jsarr.getJSONObject(index));
        }
        JSONObject ao=list.get(0);//jsonarray对象通过getjsonobjext(index)方法取得数组里面的jsonobject对象
        System.out.println("jsonObject里面的jsonarray里面的第一个jsonobject：\n"+ao+"\n");
        String vString=ao.getString("mobile");//jsonobject对象通过key直接取得String的值
        System.out.println("jsonObject里面的jsonarray里面的第一个jsonobject里的键值对对k1取值：\n"+vString+"\n");
    }
}
