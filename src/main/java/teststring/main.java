package teststring;

public class main {
    public static void main(String args[]){
        String str = "helloworeld";
        System.out.print(removeChartAt(str,3));
    }
    public static String removeChartAt(String s ,int pos){
        return s.substring(0 , pos) + s.substring(pos + 1);
    }
}
