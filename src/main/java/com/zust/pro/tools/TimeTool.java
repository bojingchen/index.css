package com.zust.pro.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author 86178
 */
public class TimeTool {

    public static  Long start(String str){

        String strr[]=str.split(",");
        Long startTime = null;
        Date now = new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy/MM/dd");
        String day=simpleDateFormat.format(now);
        day=day.replace("/", "-");
        switch (strr[1]){
            case "1":
                day=day+" 08:00:00";
            case "2":
                day=day+" 08:50:00";
            case "3":
                day=day+" 09:50:00";
            case "4":
                day=day+" 10:40:00";
            case "5":
                day=day+" 11:30:00";
            case "6":
                day=day+" 13:30:00";
            case "7":
                day=day+" 14:20:00";
            case "8":
                day=day+" 15:20:00";
            case "9":
                day=day+" 16:10:00";
            case "10":
                day=day+" 18:00:00";
            case "11":
                day=day+" 13:20:00";
            case "12":
                day=day+" 23:10:00";
        }
        Calendar calendar=Calendar.getInstance();
        try {
            calendar.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(day));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendar.getTimeInMillis();
    }

    public static Long end(String str){
        String strr[]=str.split(",");
        Long endTime = null;
        Date now = new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy/MM/dd");
        String day=simpleDateFormat.format(now);
        day=day.replace("/", "-");
        switch (strr[2]){
            case "1":
                day=day+" 08:45:00";
            case "2":
                day=day+" 09:35:00";
            case "3":
                day=day+" 10:35:00";
            case "4":
                day=day+" 11:25:00";
            case "5":
                day=day+" 12:15:00";
            case "6":
                day=day+" 14:15:00";
            case "7":
                day=day+" 15:05:00";
            case "8":
                day=day+" 16:05:00";
            case "9":
                day=day+" 16:55:00";
            case "10":
                day=day+" 19:15:00";
            case "11":
                day=day+" 23:05:00";
            case "12":
                day=day+" 23:55:00";
        }
        Calendar calendar= Calendar.getInstance();
        try {
            calendar.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(day));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendar.getTimeInMillis();
    }

}
