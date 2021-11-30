package com.zust.pro.dao.user;

import com.zust.pro.dao.BaseDao;
import com.zust.pro.vo.Student;
import com.zust.pro.vo.Teacher;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author 86178
 */
public class UserDaoImpl implements UserDao {
    @Override
    public int findUserType(int id) {
        int tbname = -1;
        String sql =  "select '2' tbname from teacher where teacher_Id = ? " +
                "union all " +
                "select '1' tbname from student where student_Id = ?;";
        Connection con = BaseDao.getCon();
        PreparedStatement st = null;
        Object[] params = {id,id};
        ResultSet rs = null;
        try {
            rs = BaseDao.executeQuery(con, st, sql, rs, params);
        while(rs.next()){
            tbname = rs.getInt("tbname");
        } } catch (Exception e) {
        e.printStackTrace();
    }
        BaseDao.closeResource(con,st,rs);
        return tbname;
    }

    @Override
    public Student findStudentById(int id) {
        String sql = "select * from student where student_Id = ?";
        Connection con = BaseDao.getCon();
        PreparedStatement st = null;
        Object[] params = {id};
        ResultSet rs = null;
        Student student = new Student();
        try{
             rs = BaseDao.executeQuery(con,st,sql,rs,params);
             while(rs.next()){

                 int student_id = rs.getInt("student_Id");
                 String student_name = rs.getString("student_Name");
                 String student_sex = rs.getString("student_Sex");
                 String password = rs.getString("student_Password");
                 String year = rs.getString("student_Year");
                 String student_class = rs.getString("student_Class");
                 String student_academy = rs.getString("student_Academy");
                 student.setId(student_id);
                 student.setStu_Name(student_name);
                 student.setStu_Sex(student_sex);
                 student.setPassword(password);

                 student.setStu_year(year);
                 student.setStu_Class(student_class);
                 student.setStu_Academy(student_academy);
             }
        } catch (Exception e) {
            e.printStackTrace();
        }
        BaseDao.closeResource(con,st,rs);
        return student;
    }

    @Override
    public Teacher findTeacherById(int id) {
        String sql = "select * from teacher where teacher_Id = ?";
        Connection con = BaseDao.getCon();
        PreparedStatement st = null;
        Object[] params = {id};
        ResultSet rs = null;
        Teacher teacher = new Teacher();
        try{
            rs = BaseDao.executeQuery(con,st,sql,rs,params);
            while(rs.next()){
                int teacher_id = rs.getInt("teacher_Id");
                String teacher_name = rs.getString("teacher_Name");
                String teacher_password = rs.getString("teacher_Password");
                Date teacher_birthDay = rs.getDate("teacher_BirthDay");
                String teacher_sex = rs.getString("teacher_Sex");
                teacher.setId(teacher_id);
                teacher.setTec_Name(teacher_name);
                teacher.setTec_Sex(teacher_sex);
                teacher.setPassword(teacher_password);
                long ts = teacher_birthDay.getTime();
                String birth = String.valueOf(ts);
                teacher.setTec_Birth(birth);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        BaseDao.closeResource(con,st,rs);
        return teacher;
    }

    @Override
    public boolean addTeacher(Teacher teacher) {
        String sql = "insert into teacher(teacher_Id,teacher_Name,teacher_BirthDay,teacher_Sex,teacher_Password) values(?,?,?,?,'000000')";
        Connection con = BaseDao.getCon();
        PreparedStatement st = null;
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd ");
        long lt =new Long(teacher.getTec_Birth());
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        Object[] params = {teacher.getId(),teacher.getTec_Name(),res,teacher.getTec_Sex()};
        int i = -1;
        try {
             i = BaseDao.executeUpdate(con, st, sql, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        BaseDao.closeResource(con,st,null);
        if (i!=-1){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean addStudent(Student student) {
        String sql = "insert into student(student_Id,student_Name,student_Sex,student_Year,student_Class,student_Academy,student_Password) values(?,?,?,?,?,?,'123456')";
        Connection con = BaseDao.getCon();
        PreparedStatement pstm = null;
        Object[]  params = {student.getId(),student.getStu_Name(),student.getStu_Sex(),student.getStu_year(),student.getStu_Class(),student.getStu_Academy()};
        int i = -1;
        try {
            i = BaseDao.executeUpdate(con, pstm, sql, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        BaseDao.closeResource(con,pstm,null);
        if (i!=-1){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean changePasswordFromStuByManager(Student student,String newPassword) {
        int i = -1;
        String sql = "update student set student_Password = ? where student_Id = ?";
        Object[] params = {newPassword,student.getId()};
        Connection con = BaseDao.getCon();
        PreparedStatement pstm = null;
        try {
            i = BaseDao.executeUpdate(con, pstm, sql, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        BaseDao.closeResource(con,pstm,null);
        if (i!=-1){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean changePasswordFromStuByManager(Teacher teacher,String newPassword) {
        int i = -1;
        String sql = "update student set teacher_Password = ? where student_Id = ?";
        Object[] params = {newPassword,teacher.getId()};
        Connection con = BaseDao.getCon();
        PreparedStatement pstm = null;
        try {
            i = BaseDao.executeUpdate(con, pstm, sql, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        BaseDao.closeResource(con,pstm,null);
        if (i!=-1){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<Teacher> getTeacherList() {
        List<Teacher> teacherList = new ArrayList<>();
        String sql = "select * from teacher";
        Connection con = BaseDao.getCon();
        PreparedStatement st = null;
        Object[] params = {};
        ResultSet rs = null;
        try{
            rs = BaseDao.executeQuery(con,st,sql,rs,params);
            while(rs.next()){
                Teacher teacher = new Teacher();
                int teacher_id = rs.getInt("teacher_Id");
                String teacher_name = rs.getString("teacher_Name");
                String teacher_password = rs.getString("teacher_Password");
                Date teacher_birthDay = rs.getDate("teacher_BirthDay");
                String teacher_sex = rs.getString("teacher_Sex");
                teacher.setId(teacher_id);
                teacher.setTec_Name(teacher_name);
                teacher.setTec_Sex(teacher_sex);
                teacher.setPassword(teacher_password);
                long ts = teacher_birthDay.getTime();
                String birth = String.valueOf(ts);
                teacher.setTec_Birth(birth);
                teacherList.add(teacher);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        BaseDao.closeResource(con,st,rs);
        return teacherList;
    }

    @Override
    public List<Student> getStudentList() {
        List<Student> studentList = new ArrayList<>();
        String sql = "select * from student";
        Connection con = BaseDao.getCon();
        PreparedStatement st = null;
        Object[] params = {};
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
    public boolean delStudentById(int id) {
        String sql = "delete from student where student_Id = ?";
        Connection con = BaseDao.getCon();
        PreparedStatement st = null;
        Object[] params = {id};
        int i = -1;
        try {
             i = BaseDao.executeUpdate(con, st, sql, params);
        } catch (Exception e) {
            e.printStackTrace();
        }BaseDao.closeResource(con,st,null);
        if (i==-1){
            return false;
        }else {
            return true;
        }

    }

    @Override
    public boolean delTeacherById(int id) {
        String sql = "delete from teacher where teacher_Id = ?";
        Connection con = BaseDao.getCon();
        PreparedStatement st = null;
        Object[] params = {id};
        int i = -1;
        try {
            i = BaseDao.executeUpdate(con, st, sql, params);
        } catch (Exception e) {
            e.printStackTrace();
        }BaseDao.closeResource(con,st,null);
        if (i==-1){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public boolean changePasswordFromStudent(int stuId, String newPassWord) {
        int i= -1;
        String sql = "update student set student_Password=? where student_Id=?";
        Connection con = BaseDao.getCon();
        PreparedStatement st = null;
        Object[] params = {newPassWord,stuId};
        try {
            i = BaseDao.executeUpdate(con,st,sql,params);
        } catch (Exception e) {
            e.printStackTrace();
        }BaseDao.closeResource(con,st,null);
        if (i==-1){
            return false;
        }else {
            return true;
        }
    }

    public boolean changePasswordFromTeacher(int Teacher_Id, String newPassWord) {
        int i= -1;
        String sql = "update teacher set teacher_Password=? where teacher_Id=?";
        Connection con = BaseDao.getCon();
        PreparedStatement st = null;
        Object[] params = {newPassWord,Teacher_Id};
        try {
            i = BaseDao.executeUpdate(con,st,sql,params);
        } catch (Exception e) {
            e.printStackTrace();
        }BaseDao.closeResource(con,st,null);
        if (i==-1){
            return false;
        }else {
            return true;
        }
    }
}
