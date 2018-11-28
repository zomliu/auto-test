package alisa;

public class BaseTest {

    public static void main(String[] args){

        Person tom_l = new BaseTest().new Person("tom", "l");
        Person lili_p = new BaseTest().new Person("lili", "p");
        Person jobs_k = new BaseTest().new Person("jobs", "k");

        System.out.println(tom_l);
    }

    class Person{
        String firstName;
        String lastName;

        public Person(String a, String b){
            this.firstName = a;
            this.lastName = b;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    '}';
        }
    }

}
