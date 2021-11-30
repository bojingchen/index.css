package com.zust.pro.vo;

import com.zust.pro.dao.course.CourseDao;
import com.zust.pro.dao.course.CourseDaoImpl;
import com.zust.pro.tools.TimeTool;
import com.zust.pro.tools.TransTool;

/**
 * @author 86178
 */

public class Course {
    private int cou_id;
    private String cou_Name;
    //上课时间
    private String cou_Time;
    //学时
    private String couPeriod;
    //教室
    private String couClassroom;
    //授课的老师
    private int teacherId;
    //授课班级
    private String TClass;
    private String Course_RealTime;
    private String start;
    private String end;
    private String week;

    public String getStart() {
        String strr[]=cou_Time.split(",");
        return strr[1].toString();
    }

    public String getEnd() {
        String strr[]=cou_Time.split(",");
        return strr[2];
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public int getCou_id() {
        return cou_id;
    }

    public void setCou_id(int cou_id) {
        this.cou_id = cou_id;
    }

    public String getCou_Name() {
        return cou_Name;
    }

    public void setCou_Name(String cou_Name) {
        this.cou_Name = cou_Name;
    }

    public String getCou_Time() {
        return cou_Time;
    }

    public void setCou_Time(String cou_Time) {
        this.cou_Time = cou_Time;
    }

    public String getCouPeriod() {
        return couPeriod;
    }

    public void setCouPeriod(String couPeriod) {
        this.couPeriod = couPeriod;
    }

    public String getCouClassroom() {
        return couClassroom;
    }

    public void setCouClassroom(String couClassroom) {
        this.couClassroom = couClassroom;
    }

    public String getTClass() {
        return TClass;
    }

    public void setTClass(String TClass) {
        this.TClass = TClass;
    }

    public String getCourse_RealTime(){
        return TransTool.getCourseTimeTrans(cou_Time);
    }

    public String getWeek() {
        week = TransTool.getWeek(cou_Time.substring(0,1));
        System.out.println("week="+ week);
        return week;
    }

    public Course() {
    }

    @Override
    public String toString() {
        return "Course{" +
                "cou_id=" + cou_id +
                ", cou_Name='" + cou_Name + '\'' +
                ", cou_Time='" + cou_Time + '\'' +
                ", couPeriod='" + couPeriod + '\'' +
                ", couClassroom='" + couClassroom + '\'' +
                ", teacherId=" + teacherId +
                ", TClass='" + TClass + '\'' +
                '}';
    }
}
