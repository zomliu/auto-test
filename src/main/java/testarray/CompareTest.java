package testarray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class CompareTest {
    public static void main(String []args){
        Integer [] numbers ={8, 2, 7, 1, 4, 9, 5};
        int min = Collections.min(Arrays.asList(numbers));
        int max = Collections.max(Arrays.asList(numbers));
        System.out.println("最小值: " + min);
        System.out.println("最大值: " + max);
        ArrayList a = new ArrayList();
        a.add(1);
        a.add(5);
        a.add(4);
        System.out.println("最值: " + a);
        System.out.println("最大值: " + Collections.max(a));

    }
}
