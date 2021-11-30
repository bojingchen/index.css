package com.zust.pro.dao.elective;

import com.zust.pro.vo.Elective;
import com.zust.pro.vo.Student;

import java.util.List;

/**
 * @author 86178
 */
public interface ElectiveDao {
    /**
     * 查看elective里面是否有这个包含student_id,course_id这个字段,有的话返回true,没有的话返回false;
     * @param course_id
     * @param stu_id
     * @return boolean
     * **/
    public boolean findElective(int stu_id,int course_id);

    /**
     * 添加elective对象
     * @param course_id
     * @param stu_id
     * @return boolean
     * **/
    public boolean addElective(int stu_id,int course_id);

    /**
     *得到这门学生这门课程的签到信息
     * @param course_id
     * @param stu_id
     * @return Elective
     * **/
    public Elective getThisElective(int stu_id,int course_id);

    /**
     * 更改这门课程，这个学生的sign_case信息为case
     * @param stu_id
     * @param sign_case
     * @param course_id
     * @return boolean
     * **/
    public boolean changeSign_case(int stu_id,int course_id,String sign_case);

    /**
     * 照这个课程的所有list
     * @param course_id
     * @return list
     * **/
    public List<Elective> getEleByCourse_id(int course_id);

    List<Elective> getEleByStudentId(int i);
}
