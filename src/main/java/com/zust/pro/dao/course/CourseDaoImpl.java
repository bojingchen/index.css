package com.zust.pro.dao.course;

import com.zust.pro.dao.BaseDao;
import com.zust.pro.vo.Course;
import com.zust.pro.vo.Student;
import com.zust.pro.vo.temper.teh_output;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 86178
 */
public class CourseDaoImpl implements CourseDao{

    @Override
    public boolean addCourse(Course course) {
        int i = -1;
        String sql = "insert into course(course_Id,course_Name,course_Time,course_Period,course_Classroom,course_TeacherId,course_Tclass) values(?,?,?,?,?,?,?)";
        Connection con = BaseDao.getCon();
        PreparedStatement pstm = null;
        Object[] params = {course.getCou_id(),course.getCou_Name(),course.getCou_Time(),course.getCouPeriod(),course.getCouClassroom(),course.getTeacherId(),course.getTClass()};
        Course AddCourse = new Course();
        try {
            i = BaseDao.executeUpdate(con, pstm, sql, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        BaseDao.closeResource(con,pstm,null);
        if (i==-1){
            return false;
        }else{
            return true;
        }

    }
    @Override
    public boolean delCourse(Course course) {
        int i = -1;
        String sql = "delete from course where course_Id=?";
        Connection con = BaseDao.getCon();
        PreparedStatement pstm = null;
        Object[] params = {course.getCou_id()};
        try{
            i = BaseDao.executeUpdate(con,pstm,sql,params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        BaseDao.closeResource(con,pstm,null);
        if (i==-1){
            return false;
        }else{
            return true;
        }
    }


    @Override
    public List<Student> findAllStudentByCourse(int courseId) {
        String sql = "select student.student_Id,student_Name,student_Sex,student_Password,student_Year,student_Class,student_Academy from student,course  where course.course_Tclass = student_Class and course.course_Id = ?";

        System.out.println(sql);
        Connection con = BaseDao.getCon();
        PreparedStatement pstm = null;
        Object[] params = {courseId};
        List<Student> students = new ArrayList<Student>();
        try{
            ResultSet rs = null;
            rs = BaseDao.executeQuery(con, pstm, sql, rs, params);
            while (rs.next()){
                Student student = new Student();
                student.setId(rs.getInt("student_Id"));
                student.setStu_Name(rs.getString("student_Name"));
                student.setStu_Sex(rs.getString("student_Sex"));
                student.setPassword(rs.getString("student_Password"));
                student.setStu_year(rs.getString("student_Year"));
                student.setStu_Class(rs.getString("student_Class"));
                student.setStu_Academy(rs.getString("student_Academy"));
                students.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        BaseDao.closeResource(con,pstm,null);
        return students;
    }

    @Override
    public List<Course> findAllCourseFromTeacher(int teacherId) {
        String sql = "select * from course where course_TeacherId=?;";
        Connection con = BaseDao.getCon();
        PreparedStatement pstm = null;
        Object[] params = {teacherId};
        List<Course> courses = new ArrayList<Course>();
        try{
            ResultSet rs = null;
            rs = BaseDao.executeQuery(con, pstm, sql, rs, params);
            while (rs.next()){
                Course course = new Course();
                course.setCou_id(rs.getInt("course_Id"));
                course.setCou_Name(rs.getString("course_Name"));
                course.setCou_Time(rs.getString("course_Time"));
                course.setCouPeriod(rs.getString("course_Period"));
                course.setCouClassroom(rs.getString("course_Classroom"));
                course.setTClass(rs.getString("course_Tclass"));
                course.setTeacherId(rs.getInt("course_TeacherId"));
                courses.add(course);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        BaseDao.closeResource(con,pstm,null);
        return courses;
    }

    @Override
    public List<Course> findAllCourseFromClass(String className) {
        String sql = "select * from course where course_Tclass = ?";
        Connection con = BaseDao.getCon();
        PreparedStatement pstm = null;
        Object[] params = {className};
        List<Course> courses = new ArrayList<Course>();
        try{
            ResultSet rs = null;
            rs = BaseDao.executeQuery(con, pstm, sql, rs, params);
            while (rs.next()){
                Course course = new Course();
                course.setCou_id(rs.getInt("course_Id"));
                course.setCou_Name(rs.getString("course_Name"));
                course.setCou_Time(rs.getString("course_Time"));
                course.setCouPeriod(rs.getString("course_Period"));
                course.setCouClassroom(rs.getString("course_Classroom"));
                course.setTClass(rs.getString("course_Tclass"));
                course.setTeacherId(rs.getInt("course_TeacherId"));
                courses.add(course);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        BaseDao.closeResource(con,pstm,null);
        return courses;
    }

    @Override
    public List<Course> findCourses() {
        String sql = "select * from course ";
        Connection con = BaseDao.getCon();
        PreparedStatement pstm = null;
        Object[] params = {};
        List<Course> courses = new ArrayList<Course>();
        try{
            ResultSet rs = null;
            rs = BaseDao.executeQuery(con, pstm, sql, rs, params);
            while (rs.next()){
                Course course = new Course();
                course.setCou_id(rs.getInt("course_Id"));
                course.setCou_Name(rs.getString("course_Name"));
                course.setCou_Time(rs.getString("course_Time"));
                course.setCouPeriod(rs.getString("course_Period"));
                course.setCouClassroom(rs.getString("course_Classroom"));
                course.setTClass(rs.getString("course_Tclass"));
                course.setTeacherId(rs.getInt("course_TeacherId"));
                courses.add(course);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        BaseDao.closeResource(con,pstm,null);
        return courses;
    }

    @Override
    public Course findCourseByCourseId(int course_id) {
        String sql = "select * from course where course_Id=?;";
        Connection con = BaseDao.getCon();
        PreparedStatement pstm = null;
        Object[] params = {course_id};
        Course course = new Course();
        try{
            ResultSet rs = null;
            rs = BaseDao.executeQuery(con, pstm, sql, rs, params);
            while (rs.next()){
                course.setCou_id(rs.getInt("course_Id"));
                course.setCou_Name(rs.getString("course_Name"));
                course.setCou_Time(rs.getString("course_Time"));
                course.setCouPeriod(rs.getString("course_Period"));
                course.setCouClassroom(rs.getString("course_Classroom"));
                course.setTClass(rs.getString("course_Tclass"));
                course.setTeacherId(rs.getInt("course_TeacherId"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        BaseDao.closeResource(con,pstm,null);
        return course;

    }

    @Override
    public List<Student> findStudentByTeacherId(int teacher_Id) {
        List<Student> studentList = new ArrayList<>();
        String sql = "select student.* from student,course,elective where course.course_TeacherId=? " +
                "and course.course_Id=elective.course_Id " +
                "and elective.student_Id=student.student_Id;";
        Connection con = BaseDao.getCon();
        PreparedStatement st = null;
        Object[] params = {teacher_Id};
        ResultSet rs = null;
        try{
            rs = BaseDao.executeQuery(con,st,sql,rs,params);
            while(rs.next()){
                Student student = new Student();
                int student_id = rs.getInt("student_Id");
                String student_name = rs.getString("student_Name");
                String student_sex = rs.getString("student_Sex");
                String password = rs.getString("student_Password");
                String student_Year = rs.getString("student_Year");
                String student_class = rs.getString("student_Class");
                String student_academy = rs.getString("student_Academy");
                student.setId(student_id);
                student.setStu_Name(student_name);
                student.setStu_Sex(student_sex);
                student.setPassword(password);
                student.setStu_year(student_Year);
                student.setStu_Class(student_class);
                student.setStu_Academy(student_academy);
                studentList.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        BaseDao.closeResource(con,st,rs);
        return studentList;
    }

    @Override
    public List<teh_output> findStu_CouByTeacherId(int teacher_Id) {
        List<teh_output> teh_outputList = new ArrayList<teh_output>();
        String sql = "select course.course_Id,course.course_Name,student.student_Id,student.student_Name from student,course,elective " +
                "where course.course_TeacherId=? " +
                "and course.course_Id=elective.course_Id " +
                "and elective.student_Id=student.student_Id;";
        Connection con = BaseDao.getCon();
        PreparedStatement st = null;
        Object[] params = {teacher_Id};
        ResultSet rs = null;
        try{
            rs = BaseDao.executeQuery(con,st,sql,rs,params);
            while(rs.next()){
                teh_output teh_output = new teh_output();
                teh_output.setCourse_Id(rs.getInt("course_Id"));
                teh_output.setCourse_Name(rs.getString("course_Name"));
                teh_output.setStudent_Id(rs.getInt("student_Id"));
                teh_output.setStudent_Name(rs.getString("student_Name"));
                teh_outputList.add(teh_output);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        BaseDao.closeResource(con,st,rs);
        return teh_outputList;
    }

    @Override
    public List<Student> findStudentsNotLeave(int course_id, String week) {
        List<Student> studentList = new ArrayList<>();
        String sql = "select student.* from student,`leave` where `leave`.course_Id=? and " +
                "`leave`.leave_Case!='1' and `leave`.student_Id=student.student_Id and `leave`.leave_week=?;";
        Connection con = BaseDao.getCon();
        PreparedStatement st = null;
        Object[] params = {course_id,week};
        ResultSet rs = null;
        try{
            rs = BaseDao.executeQuery(con,st,sql,rs,params);
            while(rs.next()){
                Student student = new Student();
                int student_id = rs.getInt("student_Id");
                String student_name = rs.getString("student_Name");
                String student_sex = rs.getString("student_Sex");
                String password = rs.getString("student_Password");
                String student_Year = rs.getString("student_Year");
                String student_class = rs.getString("student_Class");
                String student_academy = rs.getString("student_Academy");
                student.setId(student_id);
                student.setStu_Name(student_name);
                student.setStu_Sex(student_sex);
                student.setPassword(password);
                student.setStu_year(student_Year);
                student.setStu_Class(student_class);
                student.setStu_Academy(student_academy);
                studentList.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        BaseDao.closeResource(con,st,rs);
        return studentList;
    }

}
