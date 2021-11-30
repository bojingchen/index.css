package com.zust.pro.tools;

/**
 * @author 86178
 */
public class SignTool {

    //得到周次
    public static int getWeeks( String str) {
        String strs[] = str.split(",");
        return strs.length;
    }

    public static int getTimes( String str,int num) {
        String strr = str.replace("[", "");
        strr = strr.replace("]", "");
        String strs[] = strr.split(",");
        int times = 0;
        if (num == 1) {
            for (int i = 0; i < strs.length; i++) {
                String target[] = strs[i].split(":");
                if (target[1].equals("1")) {
                    times++;
                }
            }
        } else if (num == 2) {
            for (int i = 0; i < strs.length; i++) {
                String target[] = strs[i].split(":");
                if (target[1].equals("2")) {
                    times++;
                }
            }
        } else if (num == 3) {
            for (int i = 0; i < strs.length; i++) {
                String target[] = strs[i].split(":");
                if (target[1].equals("3")) {
                    times++;
                }
            }
        }
        else if (num == 0) {
            for (int i = 0; i < strs.length; i++) {
                String target[] = strs[i].split(":");
                if (target[1].equals("0")) {
                    times++;
                }
            }
        }
        return times;
    }

    //更改第几组的状态
    public static String updateStr( String str,String week, String status) {
        String strr = str.replace("[", "");
        strr = strr.replace("]", "");
        String strs[] = strr.split(",");
        for (int i = 0; i < strs.length; i++) {
            String target[] = strs[i].split(":");
            if (target[0].equals(week)) {
                target[1] = status;
            }
            target[0] = "[" + target[0];
            target[1] = target[1] + "]";
            strs[i] = String.join(":", target);
        }
        strr = String.join(",", strs);
        return strr;
    }

    //得到第星期数的状态
    public static String GetStatusWeek( String str,String week) {
        String status = "";
        String strr = str.replace("[", "");
        strr = strr.replace("]", "");
        String strs[] = strr.split(",");
        for (int i = 0; i < strs.length; i++) {
            String target[] = strs[i].split(":");
            if (target[0].equals(week)) {
                status = target[1];
            }
            target[0] = "[" + target[0];
            target[1] = target[1] + "]";
            strs[i] = String.join(":", target);
        }
        return status;
    }


    //所有的设置0为3
    public static String setUn(String str) {
        String strr = str.replace("[", "");
        strr = strr.replace("]", "");
        String strs[] = strr.split(",");
        for (int i = 0; i < strs.length; i++) {
            String target[] = strs[i].split(":");
            if (target[1].equals("0")) {
                target[1] = "3";
            }
            target[0] = "[" + target[0];
            target[1] = target[1] + "]";
            strs[i] = String.join(":", target);
        }
        strr = String.join(",", strs);
        return strr;
    }
    //添加信息
    public static  String insertStr(String str,String week, String status) {
        String strr = str;
        String strs = "[" + week + ":" + status + "]";
        if (strr.length() == 0) {
            strr = strs;
        } else {
            strr = strr + "," + strs;
        }
        return strr;
    }

    public static void main(String[] args) {
        String str = "[1,1]";
        String s = SignTool.insertStr(str, "2", "3");
        System.out.println(s);
    }
}

