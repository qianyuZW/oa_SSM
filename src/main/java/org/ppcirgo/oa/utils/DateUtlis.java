package org.ppcirgo.oa.utils;

import java.text.SimpleDateFormat;
import java.util.Date;


public class DateUtlis {
    //根据年月获得本月天数
    public static double getDaysByYearAndMonth(int year, int month) {
        int result;
        if (2 == month) {
            if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
                result = 29;
            } else {
                result = 28;
            }
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            result = 30;
        } else {
            result = 31;
        }
        return result;
    }

  /*  public static int getCurrentMonthDay() {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int days = a.get(Calendar.DATE);
        return days;

    }*/
    private static SimpleDateFormat simpleDateFormat;
    static {
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
    public static long str2long(String str){
        try {
            return simpleDateFormat.parse(str).getTime();
        }catch (Exception e){
            return 0l;
        }
    }
    public static String long2str(long time){
        if(time>0){
            return simpleDateFormat.format(new Date(time));
        }else {
            return null;
        }
    }
    public static String currentTime(long currentTime){
        Date date=new Date(currentTime);
        return   simpleDateFormat.format(date);

    }
}
