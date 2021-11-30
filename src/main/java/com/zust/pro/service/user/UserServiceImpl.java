package com.zust.pro.service.user;

import com.zust.pro.dao.course.CourseDao;
import com.zust.pro.dao.course.CourseDaoImpl;
import com.zust.pro.dao.user.UserDao;
import com.zust.pro.dao.user.UserDaoImpl;
import com.zust.pro.vo.Course;
import com.zust.pro.vo.Manager;
import com.zust.pro.vo.Student;
import com.zust.pro.vo.Teacher;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 86178
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao;
    public UserServiceImpl(){
        userDao = new UserDaoImpl();
    }


    //类型转化，username改为id，是int类型的
    @Override
    public Object login(int username, String password) {
        int userByName = userDao.findUserType(username);
        if (userByName ==1){
            Student student = userDao.findStudentById(username);
            if (student.getPassword().equals(password)) {
                return student;
            }else{
                return null;
            }
        }
        else if (userByName == 2){
            Teacher teacher = userDao.findTeacherById(username);
            if (teacher.getPassword().equals(password)) {
                return teacher;
            }
            else {
                return null;
            }
        }
        else if (username==000000){
            if (password.equals("admin")){
                Manager manager = new Manager();
                manager.setId(000000);
                return manager;
            }
            else {
                return null;
            }
        }
        else {
            return null;
        }
    }

    @Override
    public boolean addTeacher(Teacher teacher) {
        boolean b = userDao.addTeacher(teacher);
        return b;
    }

    @Override
    public boolean addStudent(Student student) {
        boolean b = userDao.addStudent(student);
        return b;
    }

    @Override
    public Map getInfo() {
        List<Student> studentList = userDao.getStudentList();
        List<Teacher> teacherList = userDao.getTeacherList();
        CourseDao courseDao = new CourseDaoImpl();
        List<Course> courseList = courseDao.findCourses();
        Map<Object,List> map = new HashMap();
        map.put("studentList",studentList);
        map.put("teacherList",teacherList);
        map.put("courseList",courseList);
        return map;
    }

    @Override
    public Student getStudentById(int id) {
        Student studentById = userDao.findStudentById(id);
        return studentById;
    }

    @Override
    public boolean delStudentById(int id) {
        Student studentById = getStudentById(id);
        boolean b = false;
        if (studentById.getId()==id){
             b = userDao.delStudentById(studentById.getId());
        }
        return b;
    }

    @Override
    public boolean delTeacherById(int id) {
        Teacher teacher = userDao.findTeacherById(id);
        boolean b = false;
        if (teacher.getId()==id){
            b = userDao.delTeacherById(id);
        }
        return b;
    }

    @Override
    public Teacher getTeacherById(int id) {
        Teacher teacherById = userDao.findTeacherById(id);
        return teacherById;
    }

    @Override
    public boolean changePasswordFromStudent(int stuId, String newPassWord) {
        boolean b = userDao.changePasswordFromStudent(stuId, newPassWord);
        return b;
    }

    @Override
    public List<Student> FindStudentByTeacherId(int teacher_Id) {
        CourseDao courseDao  = new CourseDaoImpl();
        List<Student> studentList = courseDao.findStudentByTeacherId(teacher_Id);
        return studentList;
    }

    @Override
    public boolean changePasswordFromTeacher(int teacher_Id, String newPassWord) {
        boolean b = userDao.changePasswordFromTeacher(teacher_Id,newPassWord);
        return b;
    }
}
