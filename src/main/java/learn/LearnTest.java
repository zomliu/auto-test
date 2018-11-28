package learn;

public class LearnTest {

    static Integer age2 = new Integer(10);


    public static void main(String[] args){

       Eurp e = new Eurp();
       e.idCard = "12";

       Chinese c = new Chinese();
       c.idCard = "13";

       System.out.println(e.equals(c));

    }

    private static void test(Human obj){
        obj.walk();
    }

    private static void grow(Integer age){
        //age = age + 2;

        age = new Integer(20);

    }

    private static void grow(Chinese c){
        c.age = 20;
    }

}
