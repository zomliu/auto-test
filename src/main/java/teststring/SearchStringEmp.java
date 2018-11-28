package teststring;

public class SearchStringEmp {
    public static void main(String []args){
        String a = "hello python";
        int index = a.indexOf("th");
        if (index == -1){
            System.out.print("not found");
        }
        else{
            System.out.print("found th at " + index);
        }


    }
}
