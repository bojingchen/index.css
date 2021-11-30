package com.zust.pro.vo;

import com.zust.pro.dao.course.CourseDao;
import com.zust.pro.dao.course.CourseDaoImpl;
import com.zust.pro.dao.user.UserDao;
import com.zust.pro.dao.user.UserDaoImpl;
import com.zust.pro.tools.TransTool;

/**
 * @author 86178
 */
public class Leave {
    private int stu_Id;
    private int cou_Id;
    //请假原因
    private String lea_Reason;
    //请假批准情况
    private String lea_Case;
    private String lea_week;
    private String student_name;
    private String isPass;
    private String class_time;
    private String real_time;
    public int getStu_Id() {
        return stu_Id;
    }

    public void setStu_Id(int stu_Id) {
        this.stu_Id = stu_Id;
    }


    public int getCou_Id() {
        return cou_Id;
    }

    public void setCou_Id(int cou_Id) {
        this.cou_Id = cou_Id;
    }

    public String getLea_Reason() {
        return lea_Reason;
    }

    public void setLea_Reason(String lea_Reason) {
        this.lea_Reason = lea_Reason;
    }

    public String getLea_Case() {
        return lea_Case;
    }

    public void setLea_Case(String lea_Case) {
        this.lea_Case = lea_Case;
    }


    public String getLea_week() {
        return lea_week;
    }

    public void setLea_week(String lea_week) {
        this.lea_week = lea_week;
    }

    public String getStudent_name() {
        UserDao userDao  = new UserDaoImpl();
        Student studentById = userDao.findStudentById(stu_Id);
        System.out.println(studentById);
        return studentById.getStu_Name();
    }

    public String getCourse_name() {
        CourseDao courseDao = new CourseDaoImpl();
        Course courseByCourseId = courseDao.findCourseByCourseId(cou_Id);
        return courseByCourseId.getCou_Name();
    }

    public String getIsPass() {
        return TransTool.getCase(lea_Case);
    }

    public String getClass_time(){
        CourseDao courseDao = new CourseDaoImpl();
        Course courseByCourseId = courseDao.findCourseByCourseId(cou_Id);
        return courseByCourseId.getCou_Time();
    }

    public String getReal_time() {
        CourseDao courseDao = new CourseDaoImpl();
        Course courseByCourseId = courseDao.findCourseByCourseId(cou_Id);
        return courseByCourseId.getCourse_RealTime();
    }

    @Override
    public String toString() {
        return "Leave{" +
                "stu_Id=" + stu_Id +
                ", cou_Id=" + cou_Id +
                ", lea_Reason='" + lea_Reason + '\'' +
                ", lea_Case='" + lea_Case + '\'' +
                ", lea_week='" + lea_week + '\'' +
                '}';
    }
}