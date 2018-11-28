package learn;

public class Chinese extends Human {

    private String type = "sub-class of Human";

    public int age = 10;

    public String idCard;

    public void walks(){
        System.out.println("用两条腿腿走路 + Chinese");
    }

    public void eat(){
        System.out.println("I can eat");
    }

    @Override
    public String toString() {
        return "1";
    }
}
