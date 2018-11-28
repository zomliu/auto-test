package testarray;

import java.util.Calendar;

public class TimeTurn {
    public static void main(String []args){
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -1);
        long billtime = c.getTimeInMillis();
        System.out.println(billtime);
        Calendar b = Calendar.getInstance();
        b.add(Calendar.MONTH,-2);
        long bill = b.getTimeInMillis();
        System.out.print(bill);

    }

}
