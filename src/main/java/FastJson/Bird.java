package FastJson;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class Bird {
    @JSONField(ordinal = 4,name="生产日期",format = "yyyy年MM月dd日 HH时mm分ss秒")
    private Date birthday;

    @JSONField(ordinal = 3, name = "颜色")
    private String color;

    @JSONField(ordinal = 2, name = "体型大小")

    private String size;

    @JSONField(ordinal = 1, name = "年龄")
    private int age;

    @JSONField(name = "名称", deserialize = false)
    private String name;

    @JSONField(ordinal = 5, name = "不序列化的属性字段", serialize = false)
    private String deserialize;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeserialize() {
        return deserialize;
    }

    public void setDeserialize(String deserialize) {
        this.deserialize = deserialize;
    }



    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Bird(Date birthday, String color, String size, int age, String name, String deserialize){
        this.birthday=birthday;
        this.color=color;
        this.size=size;

        this.age=age;
        this.name=name;
        this.deserialize=deserialize;
    }
}
