package testarray;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeTest {
    public static void main(String args[]){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar nowdate = Calendar.getInstance();
        nowdate.add(Calendar.DAY_OF_YEAR, -2);
        String now_date = sdf.format(nowdate.getTime());
        System.out.println(now_date);
        // 目前时间减一个月
        nowdate.add(Calendar.MONTH, -1);
        String before_date = sdf.format(nowdate.getTime());
        System.out.println(before_date);
        // 目前时间加一个月
        nowdate.add(Calendar.MONTH, 2);
        nowdate.add(Calendar.MONTH, 2);

        String after_date = sdf.format(nowdate.getTime());
        System.out.println(after_date);
    }
}
