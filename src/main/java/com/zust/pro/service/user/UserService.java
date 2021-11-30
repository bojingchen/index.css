package com.zust.pro.service.user;

import com.zust.pro.vo.Student;
import com.zust.pro.vo.Teacher;

import java.util.List;
import java.util.Map;

/**
 * @author 86178
 */
public interface UserService {
    /**
     * 用户登录
     * @return Object
     * **/
    public Object login(int username, String password);

    /**
     * 管理员添加老师
     * @param teacher
     * @return Object
     * **/
    public boolean addTeacher(Teacher teacher);

    /**
     * 管理员添加学生
     * @param student
     * @return Object
     * **/
    public boolean addStudent(Student student);

    /**
     * 管理员得到学生，教师，(课程)信息
     * @param
     * @return Map
     * **/
    public Map getInfo();

    /**
     * 找到这个学生
     * @param id
     * @return Student
     * 找到用户的id
     * **/
    public Student getStudentById(int id);

    /**
     * 找到这个学生
     * @param id
     * @return boolean
     * 找到用户的id,并且删除
     * **/
    public boolean delStudentById(int id);

    /**
     * 找到这个老师
     * @param id
     * @return boolean
     * 找到用户的id,并且删除
     * **/
    public boolean delTeacherById(int id);

    /** 根据老师id查找他（她）所教的学生
     * @param teacher_Id
     * @return List
     */
    List<Student> FindStudentByTeacherId(int teacher_Id);

    /**
     * 找到这个老师
     * @param id
     * @return Student
     * 找到用户的id
     * **/
    public Teacher getTeacherById(int id);

    /**
     * 更改这个学生的密码
     * @param newPassWord
     * @param stuId
     * @return boolean
     * 找到用户的id
     * **/
    public boolean changePasswordFromStudent(int stuId,String newPassWord);

    /**
     * 更改这个老师的密码
     * @param newPassWord
     * @param teacher_Id
     * @return boolean
     * 找到用户的id
     */
    public boolean changePasswordFromTeacher(int teacher_Id,String newPassWord);
}
