package com.zust.pro.dao.elective;

import com.zust.pro.dao.BaseDao;
import com.zust.pro.vo.Elective;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 86178
 */

public class ElectiveDaoImpl implements ElectiveDao {
    @Override
    public boolean findElective(int stu_id, int course_id) {
        int i=0 ;
        String sql ="select course_Id,student_Id from elective where course_Id=? and student_Id=?;";
        Object[] params = {course_id,stu_id};
        Connection con = BaseDao.getCon();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            rs = BaseDao.executeQuery(con,pstm,sql,rs,params);
            while(rs.next()){
                i = 1;
            }
            System.out.println(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        BaseDao.closeResource(con,pstm,rs);
        if (i==1){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean addElective(int stu_id, int course_id) {
        int i = 0;
        String sql = "insert into elective (student_Id,course_Id) VALUES(?,?);";
        Connection con = BaseDao.getCon();
        PreparedStatement pstm = null;
        Object[] params = {stu_id,course_id};
        try{
            i = BaseDao.executeUpdate(con,pstm,sql,params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        BaseDao.closeResource(con,pstm,null);
        if (i!=0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Elective getThisElective(int stu_id, int course_id) {
        String sql = "select elective.* from elective where student_Id=? and course_Id=?;";
        Connection con = BaseDao.getCon();
        PreparedStatement pstm = null;
        Object[] params = {stu_id,course_id};
        Elective elective = new Elective();
        try{
            ResultSet rs = null;
            rs = BaseDao.executeQuery(con, pstm, sql, rs, params);
            while (rs.next()){
                elective.setStu_id(rs.getInt("student_Id"));
                elective.setCourse_id(rs.getInt("course_Id"));
                elective.setSign_case(rs.getString("sign_Case"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        BaseDao.closeResource(con,pstm,null);
        return elective;
    }

    @Override
    public boolean changeSign_case(int stu_id, int course_id, String sign_case) {
        int i = 0;
        String sql = "update elective set sign_Case=? where student_Id=? " +
                "and course_Id=?;";
        Object[] params = {sign_case,stu_id,course_id};
        Connection con = BaseDao.getCon();
        PreparedStatement pstm = null;
        try {
            i = BaseDao.executeUpdate(con, pstm, sql, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        BaseDao.closeResource(con,pstm,null);
        if (i!=0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<Elective> getEleByCourse_id(int course_id) {
        String sql = "select elective.* from elective where  course_Id=?;";
        Connection con = BaseDao.getCon();
        PreparedStatement pstm = null;
        Object[] params = {course_id};
        List<Elective> electiveList = new ArrayList<>();
        try{
            ResultSet rs = null;
            rs = BaseDao.executeQuery(con, pstm, sql, rs, params);
            while (rs.next()){
                Elective elective = new Elective();
                elective.setStu_id(rs.getInt("student_Id"));
                elective.setCourse_id(rs.getInt("course_Id"));
                elective.setSign_case(rs.getString("sign_Case"));
                electiveList.add(elective);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        BaseDao.closeResource(con,pstm,null);
        return electiveList;
    }

    @Override
    public List<Elective> getEleByStudentId(int i) {
        String sql = "select elective.* from elective where  student_Id=?;";
        Connection con = BaseDao.getCon();
        PreparedStatement pstm = null;
        Object[] params = {i};
        List<Elective> electiveList = new ArrayList<>();
        try{
            ResultSet rs = null;
            rs = BaseDao.executeQuery(con, pstm, sql, rs, params);
            while (rs.next()){
                Elective elective = new Elective();
                elective.setStu_id(rs.getInt("student_Id"));
                elective.setCourse_id(rs.getInt("course_Id"));
                elective.setSign_case(rs.getString("sign_Case"));
                electiveList.add(elective);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        BaseDao.closeResource(con,pstm,null);
        return electiveList;
    }


}
