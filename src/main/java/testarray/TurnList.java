package testarray;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class TurnList {
    public static void main(String args[]) {
        String a[] = { "A", "E", "I" };
        String b[] = { "O", "U" };
        List list = new ArrayList(Arrays.asList(a));
        list.addAll(Arrays.asList(b));
        System.out.println(list);
        Object[] c = list.toArray();
        System.out.println(Arrays.toString(c));
    }
}
