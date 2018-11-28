package structure;

import java.util.*;

public class Structure {

    public static void main(String[] args){
        //printGender(GenderEnum.MALE);

        testArrayList();
    }

    static void printGender(GenderEnum gender){
        if (gender == GenderEnum.FMALE){
            print(gender.getDesc());
        }
        if (gender == GenderEnum.MALE){
            print(gender.getDesc());
        }

        gender.getVal();
    }


    static void print(Object v){
        System.out.println(v);
    }

    static void testArrayList(){
        List<Integer> a = new ArrayList<>();
        a.add(34);
        a.add(56);
        print(a.get(0));
        List<String> list = Arrays.asList("apple", "orange", "pear", "coconut", "blue barry");
        for (String f : list){
            print(f);
        }
        print("-----------------------------");
        list.sort((s1, s2)-> s1.compareTo(s2));

        list.forEach(s -> print(s));
    }

    static void testHashMap(){
        Map<String, String> map = new HashMap<>();
        map.put("one", "number 1");
        map.put("two", "number 2");
        map.put("three", "number 3");
        map.put("fore", "number 4");

        map.get("two");

        map.forEach((k,v) -> {
            print(k);
            print(v);
        });
        for (Map.Entry entry : map.entrySet()){
            print(entry.getKey());
            print(entry.getValue());
        }
    }


}
