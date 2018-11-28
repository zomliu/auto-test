package learn;

public class Eurp extends Human {

    public String idCard;

    @Override
    public void walk() {
        System.out.println("Eurp");

        super.walk();
    }

    public void walkOfFather(){
        super.walk();
    }

    @Override
    public boolean equals(Object obj) {
        Chinese c = (Chinese)obj;
        return this.idCard.equals(c.idCard);
    }

    @Override
    public String toString() {
        return "1";
    }
}
