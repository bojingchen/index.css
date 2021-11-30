package com.zust.pro.vo.temper;

import com.zust.pro.dao.course.CourseDao;
import com.zust.pro.dao.course.CourseDaoImpl;
import com.zust.pro.dao.user.UserDao;
import com.zust.pro.dao.user.UserDaoImpl;
import com.zust.pro.tools.TransTool;
import com.zust.pro.vo.Course;
import com.zust.pro.vo.Student;

/**
 * @author 86178
 */
public class LeaveCdn {
    private int course_id;
    private String courseName;
    private String course_Time;
    private String leave_week;
    private String is_pass;
    private String course_name;
    private int student_id;
    private String student_name;
    private String realTime;
    private String pass;
    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourse_Time() {
        return course_Time;
    }

    public void setCourse_Time(String course_Time) {
        this.course_Time = course_Time;
    }

    public String getLeave_week() {
        return leave_week;
    }

    public void setLeave_week(String leave_week) {
        this.leave_week = leave_week;
    }

    public String getIs_pass() {
        return is_pass;
    }

    public void setIs_pass(String is_pass) {
        this.is_pass = is_pass;
    }

    public String getCourse_name() {
        CourseDao courseDao = new CourseDaoImpl();
        Course courseByCourseId = courseDao.findCourseByCourseId(course_id);
        return courseByCourseId.getCou_Name();
    }


    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public String getStudent_name() {
       UserDao userDao  = new UserDaoImpl();
        Student studentById = userDao.findStudentById(student_id);
        System.out.println(studentById);
        return studentById.getStu_Name();
    }

    public String getRealTime() {
        return TransTool.getCourseTimeTrans(course_Time);
    }

    public String getPass(){
        return TransTool.getCase(is_pass);
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "LeaveCdn{" +
                "course_id=" + course_id +
                ", courseName='" + courseName + '\'' +
                ", course_Time='" + course_Time + '\'' +
                ", leave_week='" + leave_week + '\'' +
                ", is_pass='" + is_pass + '\'' +
                ", course_name='" + course_name + '\'' +
                ", student_id=" + student_id +
                ", student_name='" + student_name + '\'' +
                ", realTime='" + realTime + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }
}
