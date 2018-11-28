package teststring;
public class StringReverseExample{
    public static void main(String[] args){
        String string="abcdef";
        StringBuffer reverse = new StringBuffer(string).reverse();
        System.out.println("\nString before reverse: "+string);
                System.out.println("String after reverse: "+reverse);
    }
}