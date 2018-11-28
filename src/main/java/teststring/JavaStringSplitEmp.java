package teststring;

public class JavaStringSplitEmp {
    public static void main(String []args){
        String str = "www-youji-com";
        String [] temp;
        String delimeter = "-";
        temp = str.split(delimeter);
        for (int i = 0;i < temp.length ; i++){
            System.out.print(temp[i]);
            System.out.println("");
        }
        String str1 = "www.w3cschool.cn";
        String [] temp1;
        String delimeter1 = "\\.";
        temp1 = str1.split(delimeter1);
        for(String s : temp1)
         {
             System.out.println(s);
             System.out.println("");
        }
    }
}
