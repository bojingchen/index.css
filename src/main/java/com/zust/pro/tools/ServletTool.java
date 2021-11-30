package com.zust.pro.tools;

/**
 * @author 86178
 */
public class ServletTool {

    public static String  getFileName(String str){
        String string = "";
        int start =  str.lastIndexOf("\\");
        string = str.substring(start + 1);
        return string;
    }

    public static String getFileType(String name){
        int start = name.indexOf(".",1);
        int end = name.indexOf(".",start+1);
        if (end==-1){
            return name.substring(start+1);
        }
        else {
            return name.substring(end);
        }
    }

    public static String getMethodName(String uri) {
        int start = uri.indexOf("/", 1);
        int end = uri.indexOf("/", start + 1);
        int end2 = uri.indexOf("?", start + 1);
        if (end2 < end && end2 != -1) {
            end = end2;
        }
        if (end == -1) {
            System.out.println(uri.substring(start + 1));
            return uri.substring(start + 1);
        } else {
            System.out.println(uri.substring(start, end));
            return uri.substring(start, end);
        }
    }
}
