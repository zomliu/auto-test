package teststring;

public class StringReplaceEmp {
    public static void main(String args[]){
        String str = "my name is heleon";
        System.out.print(str.replace('y','t'));
        System.out.print(str.replaceFirst("na","tt"));
       System.out.print(str.replaceAll("my","he"));
    }
}
