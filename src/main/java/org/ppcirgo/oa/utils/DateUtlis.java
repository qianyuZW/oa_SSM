package org.ppcirgo.oa.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtlis {
    private static SimpleDateFormat simpleDateFormat ;
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
