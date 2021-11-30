package com.zust.pro.vo.temper;

import com.zust.pro.dao.course.CourseDao;
import com.zust.pro.dao.course.CourseDaoImpl;
import com.zust.pro.vo.Course;

/**
 * @author 86178
 */
public class Sign {
    private int teh_id;
    private int cour_id;
    private String week;
    private Long course_StartTime;
    private Long course_EndTime;
    private Long send_time;

    public int getTeh_id() {
        return teh_id;
    }

    public void setTeh_id(int teh_id) {
        this.teh_id = teh_id;
    }

    public int getCour_id() {
        return cour_id;
    }

    public void setCour_id(int cour_id) {
        this.cour_id = cour_id;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public Long getCourse_StartTime() {
        return course_StartTime;
    }

    public void setCourse_StartTime(Long course_StartTime) {
        this.course_StartTime = course_StartTime;
    }

    public Long getCourse_EndTime() {
        return course_EndTime;
    }

    public void setCourse_EndTime(Long course_EndTime) {
        this.course_EndTime = course_EndTime;
    }

    public Long getSend_time() {
        return send_time;
    }

    public void setSend_time(Long send_time) {
        this.send_time = send_time;
    }

    public String getCourse_name(){
        CourseDao courseDao = new CourseDaoImpl();
        Course courseByCourseId = courseDao.findCourseByCourseId(cour_id);
        return courseByCourseId.getCou_Name();
    }

    @Override
    public String toString() {
        return "Sign{" +
                "teh_id=" + teh_id +
                ", cour_id=" + cour_id +
                ", week='" + week + '\'' +
                ", course_StartTime=" + course_StartTime +
                ", course_EndTime=" + course_EndTime +
                ", send_time=" + send_time +
                '}';
    }
}
