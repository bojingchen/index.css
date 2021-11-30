package com.zust.pro.vo;

/**
 * @author 86178
 */
public class Student extends User {
    private String stu_Name;
    private String stu_Sex;
    //学生入学年份
    private String stu_year;
    //班级
    private String stu_Class;
    //所在学院
    private String stu_Academy;

    public String getStu_Name() {
        return stu_Name;
    }

    public void setStu_Name(String stu_Name) {
        this.stu_Name = stu_Name;
    }

    public String getStu_Sex() {
        return stu_Sex;
    }

    public void setStu_Sex(String stu_Sex) {
        this.stu_Sex = stu_Sex;
    }

    public String getStu_Class() {
        return stu_Class;
    }

    public void setStu_Class(String stu_Class) {
        this.stu_Class = stu_Class;
    }

    public String getStu_Academy() {
        return stu_Academy;
    }

    public void setStu_Academy(String stu_Academy) {
        this.stu_Academy = stu_Academy;
    }

    public String getStu_year() {
        return stu_year;
    }

    public void setStu_year(String stu_year) {
        this.stu_year = stu_year;
    }

    @Override
    public String toString() {
        return "Student{" +
                "stu_Name='" + stu_Name + '\'' +
                ", stu_Sex='" + stu_Sex + '\'' +
                ", stu_year='" + stu_year + '\'' +
                ", stu_Class='" + stu_Class + '\'' +
                ", stu_Academy='" + stu_Academy + '\'' +
                ", id=" + id +
                ", password='" + password + '\'' +
                '}';
    }
}