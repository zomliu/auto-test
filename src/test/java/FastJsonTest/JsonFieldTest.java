package FastJsonTest;


import FastJson.Bird;
import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.Date;

public class JsonFieldTest {
    Bird bird = new Bird(new Date(), "红色皮肤", "巨大无比", 18, "典韦", "不序列化的字段，是不会被转json输出的");

    @Test
    public void test1(){
        String userJson = JSON.toJSONString(bird);
        System.out.print(userJson);
    }
}
