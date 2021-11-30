package com.zust.pro.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 86178
 */
public class TransTool {

    public static String getCase(String leave_case){
        if (leave_case.equals("0")){
            return "未处理";
        }else if (leave_case.equals("1")){
            return "允许请假";
        }else if (leave_case.equals("2")){
            return "拒绝请假";
        }else {
            return "数据有误";
        }
    }

    public static String getCourseTimeTrans(String course_time){
        String result = "";
        String[] str = course_time.split(",");
        for (int i=0;i<str.length;i++){
            if (i==0){
                switch (str[i]){
                case "1" :  result = "星期一:" ;break;
                case "2" :  result = "星期二:";break;
                    case "3" : result = "星期三:";break;
                    case "4" : result = "星期四:";break;
                    case "5" : result = "星期五：";break;
                    default:result = "格式错误";
                 }
            }
            if (i==1){
                result  = result + str[i] ;
            }
            if (i==2){
                result = result +"," +str[i] + "节课";
            }
        }
        return result;
    }

    public static String getWeek(String course_time) {
        String result = "";
        String[] str = course_time.split(",");
        for (int i = 0; i < str.length; i++) {
            if (i == 0) {
                switch (str[i]) {
                    case "1":
                        result = "星期一";
                        break;
                    case "2":
                        result = "星期二";
                        break;
                    case "3":
                        result = "星期三";
                        break;
                    case "4":
                        result = "星期四";
                        break;
                    case "5":
                        result = "星期五";
                        break;
                    default:
                        result = "格式错误";
                }
            }
        }
        return result;
    }

    public static String getBirth(String birth){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd ");
//一般网上的转换是没有中间new Long（timeStamp）,因为他们都是精确到毫秒的时间戳，不用再乘以1000进行转换
        long longTimeStamp = Long.parseLong(birth);
        Date date = new Date(longTimeStamp);
        String dareString = simpleDateFormat.format(date);
        return dareString;
    }
}
