package learn;

public class OverloadingTest {

    public int test(){
        System.out.println("test1");
        return 1;
    }

    public  void test(int a){
        System.out.println("test2");
    }

    public  static void main(String args[]){
        OverloadingTest o = new OverloadingTest();
        o.test();
        o.test(5);
    }
}
