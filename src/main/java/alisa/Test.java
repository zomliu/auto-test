package alisa;

import java.util.Calendar;
public class Test {
    public static void main (String args[]){
        String result="APPROE";
        String count = "5";
        if (result.equals("APPROVE")){
            System.out.print("success");
        }
        else if (result.equals("PROCESSING")){
            System.out.print("PROCESSING");
        }
        else if (Integer.valueOf(count) > 20){
            System.out.print("counttt");
        }
        else{
            System.out.print(String.valueOf(Integer.valueOf(count) + 1));
        }


    }
}
