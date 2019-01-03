package SortTest;

import FastJson.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ComparatorTest {
    User user1=new User("张三",20);
    User user2=new User("李四",18);
    User user3=new User("李四",16);

    @Test
    public void comparingTest(){
        List<User> userlist = new ArrayList();
        userlist.add(user1);
        userlist.add(user2);
        userlist.add(user3);
        userlist.sort(Comparator.comparing(User::getAge));
        for (User user : userlist){
            System.out.println(user.getAge());
        }
    }

    @Test
    public void reverseTest(){
        List<User> testlist = new ArrayList();
        testlist.add(user1);
        testlist.add(user2);
        testlist.add(user3);
        testlist.sort(Comparator.comparing(User::getAge).reversed());
        for (User user : testlist){
            System.out.println(user.getAge());
        }
    }
}
