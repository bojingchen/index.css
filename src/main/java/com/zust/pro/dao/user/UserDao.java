package com.zust.pro.dao.user;

import com.zust.pro.vo.Student;
import com.zust.pro.vo.Teacher;

import java.util.List;

/**
 * @author 86178
 */
public interface UserDao {

    /**
     * 查找登录的用户id，并且返回类型。学生是1，老师是2，查找不到为-1
     * 多表查询返回是哪一个表查找到的：可参照 https://zhidao.baidu.com/question/595004751.html
     * @param id
     * @return
     * **/
    public int findUserType(int id) ;
    /**
     * 根据id查找学生的信息，返回学生实例
     * @param id
     * @return Student
     * **/
    public Student findStudentById(int id);

    /**
     * 根据id查找教师的信息，返回教师实例
     * @param id
     * @return Student
     * **/
    public Teacher findTeacherById(int id);

    /**
     * 管理员添加老师信息
     * @param teacher
     * @return boolean
     * **/
    public boolean addTeacher(Teacher teacher);

    /**
     * 管理员添加学生信息
     * @param student
     * @return boolean类型
     * **/
    public boolean addStudent(Student student);

    /**
     * 学生修改密码
     * @param student
     * @param newPassword
     * @return boolean类型
     * **/
    public boolean changePasswordFromStuByManager(Student student,String newPassword);

    /**
     * 教师修改密码
     * @param teacher
     * @param newPassword
     * @return boolean类型
     * **/
    public boolean changePasswordFromStuByManager(Teacher teacher,String newPassword);

    /**
     * 管理员得到老师的列表
     * @return List
     * **/
    public List<Teacher> getTeacherList();

    /**
     * 管理员得到学生的列表
     * @return List
     * **/
    public List<Student> getStudentList();


    /**
     * 删除学生信息
     * @param id
     * @return booleam
     * **/
    public boolean delStudentById(int id);

    /**
     * 删除老师信息
     * @param id
     * @return booleam
     * **/
    public boolean delTeacherById(int id);

    /**
     * 更改这个学生的密码
     * @param newPassWord
     * @param stuId
     * @return boolean
     * 找到用户的id
     * **/
    public boolean changePasswordFromStudent(int stuId, String newPassWord);

    /**
     * 更改这个老师的密码
     * @param newPassWord
     * @param Teacher_Id
     * @return boolean
     * 找到用户的id
     * **/
    public boolean changePasswordFromTeacher(int Teacher_Id,String newPassWord);

}
