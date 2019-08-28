package com.uaes.esw.util;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 判断两个日期间相差天数，假如是从这个月一号到这个月六号，那么一共就是相差六天
     * @param startDate
     * @param endDate
     * @return
     */
    public static int differentDays(String startDate,String endDate){
        ParsePosition pos = new ParsePosition(0);
        Calendar cal1 = Calendar.getInstance();
        Date date1 = format.parse(startDate,pos);
        cal1.setTime(date1);

        ParsePosition pos1 = new ParsePosition(0);
        Calendar cal2 = Calendar.getInstance();
        Date date2 = format.parse(endDate,pos1);
        cal2.setTime(date2);
        int day1= cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if(year1 != year2){ //如果两个日期不是同一年
            int timeDistance = 0 ;
            for(int i = year1 ; i < year2 ; i ++)
            {
                if(i%4==0 && i%100!=0 || i%400==0)    //闰年
                {
                    timeDistance += 366;
                }
                else    //不是闰年
                {
                    timeDistance += 365;
                }
            }
            return timeDistance + (day2 - day1 + 1);
        } else{  //如果两个人日期是同一年
            return day2 - day1 + 1;
        }
    }

    /**
     * 计算两个日期相差的月数,假如是从今年一月到今年六月，那么一共就是相差六个月
     * @param startDate
     * @param endDate
     * @return
     */
    public static int differentMonths(String startDate,String endDate){
        ParsePosition pos = new ParsePosition(0);
        Calendar cal1 = Calendar.getInstance();
        Date date1 = format.parse(startDate,pos);
        cal1.setTime(date1);

        ParsePosition pos1 = new ParsePosition(0);
        Calendar cal2 = Calendar.getInstance();
        Date date2 = format.parse(endDate,pos1);
        cal2.setTime(date2);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);

        int month1 = cal1.get(Calendar.MONTH);
        int month2 = cal2.get(Calendar.MONTH);
        if(year1 != year2){ //如果两个日期不是同一年
            return 13 + month2 - month1;
        } else{  //如果两个人日期是同一年
            return month2 - month1 + 1;
        }
    }
}
