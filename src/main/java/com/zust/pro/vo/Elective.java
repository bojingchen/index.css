package com.zust.pro.vo;

import com.zust.pro.dao.course.CourseDao;
import com.zust.pro.dao.course.CourseDaoImpl;
import com.zust.pro.dao.user.UserDao;
import com.zust.pro.dao.user.UserDaoImpl;
import com.zust.pro.tools.SignTool;

/**
 * @author 86178
 */
public class Elective {
    private int stu_id;
    private int course_id;
    private String sign_case;
    private int course_name;
    private int stu_name;
    //迟到的次数
    private int Leave_times;
    //签到次数
    private int sign_times;
    //总签到次数
    private int total_times;
    //没去的次数
    private int out_times;
    private int grade;
    private int qingjia;

    public int getStu_id() {
        return stu_id;
    }

    public void setStu_id(int stu_id) {
        this.stu_id = stu_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public void setSign_case(String sign_case) {
        this.sign_case = sign_case;
    }

    public String getCourse_name(){
        CourseDao courseDao = new CourseDaoImpl();
        Course courseByCourseId = courseDao.findCourseByCourseId(course_id);
        return courseByCourseId.getCou_Name();
    }

    public String getStu_name(){
        UserDao userDao  = new UserDaoImpl();
        Student studentById = userDao.findStudentById(stu_id);
        System.out.println(studentById);
        return studentById.getStu_Name();
    }

    public String getSign_case() {
        return sign_case;
    }

    public int getLeave_times() {
         return SignTool.getTimes(sign_case,2);
    }

    public int getSign_times() {
        return SignTool.getTimes(sign_case,1);
    }

    public int getTotal_times() {
        return SignTool.getWeeks(sign_case);
    }
    public int getOut_times() {
        return SignTool.getTimes(sign_case,0);
    }

    public void setLeave_times() {
        Leave_times = SignTool.getTimes(sign_case,2);
    }

    public void setOut_times() {
        this.out_times = SignTool.getTimes(sign_case,0);
    }

    public int getGrade(){
        int grade=10;
        grade=grade-Leave_times-2*out_times;
        return grade;
    }

    public int getQingjia() {
        return SignTool.getTimes(sign_case,3);
    }

    @Override
    public String toString() {
        return "Elective{" +
                "stu_id=" + stu_id +
                ", course_id=" + course_id +
                ", sign_case='" + sign_case + '\'' +
                ", course_name=" + course_name +
                ", stu_name=" + stu_name +
                ", Leave_times=" + Leave_times +
                ", sign_times=" + sign_times +
                ", total_times=" + total_times +
                ", out_times=" + out_times +
                ", grade=" + grade +
                '}';
    }



}
