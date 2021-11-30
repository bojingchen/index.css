package com.zust.pro.vo.temper;

import com.zust.pro.dao.elective.ElectiveDao;
import com.zust.pro.dao.elective.ElectiveDaoImpl;
import com.zust.pro.vo.Elective;

/**
 * @author 86178
 */
public class teh_output {
    private int course_Id;
    private String course_Name;
    private int student_Id;
    private String student_Name;
    private int price;

    public int getCourse_Id() {
        return course_Id;
    }

    public void setCourse_Id(int course_Id) {
        this.course_Id = course_Id;
    }

    public String getCourse_Name() {
        return course_Name;
    }

    public void setCourse_Name(String course_Name) {
        this.course_Name = course_Name;
    }

    public int getStudent_Id() {
        return student_Id;
    }

    public void setStudent_Id(int student_Id) {
        this.student_Id = student_Id;
    }

    public String getStudent_Name() {
        return student_Name;
    }

    public void setStudent_Name(String student_Name) {
        this.student_Name = student_Name;
    }


    @Override
    public String toString() {
        return "teh_output{" +
                "course_Id=" + course_Id +
                ", course_Name='" + course_Name + '\'' +
                ", student_Id=" + student_Id +
                ", student_Name='" + student_Name + '\'' +
                ", price=" + price +
                '}';
    }

    public int getPrice() {
        ElectiveDao electiveDao = new ElectiveDaoImpl();
        Elective thisElective = electiveDao.getThisElective(student_Id, course_Id);
        thisElective.setLeave_times();
        thisElective.setOut_times();
        int grade = thisElective.getGrade();
        if(grade<0){
            grade = 0;
        }
        return grade;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
