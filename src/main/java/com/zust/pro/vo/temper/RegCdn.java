package com.zust.pro.vo.temper;

/**
 *
 *请假的情况
 * @author 86178
 */
public class RegCdn {
    int course_id;
    String course_name;
    int late_times;
    int elective_times;
    int dontGo_times;
    int sign_times;
    int totalTimes;

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public int getLate_times() {
        return late_times;
    }

    public void setLate_times(int late_times) {
        this.late_times = late_times;
    }

    public int getElective_times() {
        return elective_times;
    }

    public void setElective_times(int elective_times) {
        this.elective_times = elective_times;
    }

    public int getDontGo_times() {
        return dontGo_times;
    }

    public void setDontGo_times(int dontGo_times) {
        this.dontGo_times = dontGo_times;
    }

    public int getTotalTimes() {
        return totalTimes;
    }

    public void setTotalTimes(int totalTimes) {
        this.totalTimes = totalTimes;
    }

    public int getSign_times() {
        return sign_times;
    }

    public void setSign_times(int sign_times) {
        this.sign_times = sign_times;
    }

    @Override
    public String toString() {
        return "RegCdn{" +
                "course_id=" + course_id +
                ", course_name='" + course_name + '\'' +
                ", late_times=" + late_times +
                ", elective_times=" + elective_times +
                ", dontGo_times=" + dontGo_times +
                ", sign_times=" + sign_times +
                ", totalTimes=" + totalTimes +
                '}';
    }
}
