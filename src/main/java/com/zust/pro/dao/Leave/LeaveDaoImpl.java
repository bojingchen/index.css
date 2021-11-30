package com.zust.pro.dao.Leave;


import com.zust.pro.dao.BaseDao;
import com.zust.pro.vo.Course;
import com.zust.pro.vo.Leave;
import com.zust.pro.vo.temper.LeaveCdn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LeaveDaoImpl implements LeaveDao{

    @Override
    public List<LeaveCdn> getLeaveCdnListFromStudent(int id) {
            String sql = "select course.course_Id,course.course_Name,course.course_Time,`leave`.leave_week,`leave`.leave_Case " +
                    "from `leave`,course " +
                    "where `leave`.student_Id=? and `leave`.course_Id=course.course_Id;";
            Connection con = BaseDao.getCon();
            PreparedStatement pstm = null;
            Object[] params = {id};
            List<LeaveCdn> leaveCdns = new ArrayList<LeaveCdn>();
            try{
                ResultSet rs = null;
                rs = BaseDao.executeQuery(con, pstm, sql, rs, params);
                while (rs.next()){
                    LeaveCdn leaveCdn = new LeaveCdn();
                    leaveCdn.setCourse_id(rs.getInt("course_Id"));
                    leaveCdn.setCourseName(rs.getString("course_Name"));
                    leaveCdn.setCourse_Time(rs.getString("course_Time"));
                    leaveCdn.setLeave_week(rs.getString("leave_week"));
                    leaveCdn.setIs_pass(rs.getString("leave_Case"));
                    leaveCdns.add(leaveCdn);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            BaseDao.closeResource(con,pstm,null);
            return leaveCdns;
    }

    @Override
    public boolean addLeave(Leave leave) {
        int i = -1;
        String sql = "insert into `leave` (student_Id,course_Id,leave_Reason,leave_Case,leave_week) VALUES(?,?,?,'0',?);";
        Connection con = BaseDao.getCon();
        PreparedStatement pstm = null;
        Object[] params = {leave.getStu_Id(),leave.getCou_Id(),leave.getLea_Reason(),leave.getLea_week()};
        try{
            i = BaseDao.executeUpdate(con,pstm,sql,params);
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
    public List<Leave> findLeaveByTeacher(int id) {
        //根据老师的id找到他所教的课程的请假情况(所有)
        String sql = "select `leave`.* from `leave`,course " +
                "where course_TeacherId=? and course.course_Id=`leave`.course_Id;";
        Connection con = BaseDao.getCon();
        PreparedStatement pstm = null;
        Object[] params = {id};
        List<Leave> leaves = new ArrayList<Leave>();
        try{
            ResultSet rs = null;
            rs = BaseDao.executeQuery(con, pstm, sql, rs, params);
            while (rs.next()){
                Leave leave = new Leave();
                leave.setStu_Id(rs.getInt("student_Id"));
                leave.setCou_Id(rs.getInt("course_Id"));
                leave.setLea_Reason(rs.getString("leave_Reason"));
                leave.setLea_Case(rs.getString("leave_Case"));
                leave.setLea_week(rs.getString("leave_week"));
                leaves.add(leave);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        BaseDao.closeResource(con,pstm,null);
        return leaves;
    }

    @Override
    public List<Leave> findLeaveByTeacherNotVerify(int id) {
        String sql = "select `leave`.* from `leave`,course where course_TeacherId=? " +
                "and course.course_Id=`leave`.course_Id and leave_Case='0';";
        Connection con = BaseDao.getCon();
        PreparedStatement pstm = null;
        Object[] params = {id};
        List<Leave> leaves = new ArrayList<Leave>();
        try{
            ResultSet rs = null;
            rs = BaseDao.executeQuery(con, pstm, sql, rs, params);
            while (rs.next()){
                Leave leave = new Leave();
                leave.setStu_Id(rs.getInt("student_Id"));
                leave.setCou_Id(rs.getInt("course_Id"));
                leave.setLea_Reason(rs.getString("leave_Reason"));
                leave.setLea_Case(rs.getString("leave_Case"));
                leave.setLea_week(rs.getString("leave_week"));
                leaves.add(leave);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        BaseDao.closeResource(con,pstm,null);
        return leaves;
    }

    @Override
    public List<Leave> findLeaveByTeacherVerify(int id) {
        String sql = "select `leave`.* from `leave`,course where course_TeacherId=? " +
                "and course.course_Id=`leave`.course_Id and leave_Case!='0';";
        Connection con = BaseDao.getCon();
        PreparedStatement pstm = null;
        Object[] params = {id};
        List<Leave> leaves = new ArrayList<Leave>();
        try{
            ResultSet rs = null;
            rs = BaseDao.executeQuery(con, pstm, sql, rs, params);
            while (rs.next()){
                Leave leave = new Leave();
                leave.setStu_Id(rs.getInt("student_Id"));
                leave.setCou_Id(rs.getInt("course_Id"));
                leave.setLea_Reason(rs.getString("leave_Reason"));
                leave.setLea_Case(rs.getString("leave_Case"));
                leave.setLea_week(rs.getString("leave_week"));
                leaves.add(leave);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        BaseDao.closeResource(con,pstm,null);
        return leaves;
    }

    @Override
    public boolean changeLeaveCase(Leave leave, String leave_case) {
        int i = 0;
        String sql = "update `leave` set leave_Case=? where student_Id=? " +
                "and course_Id=? and leave_week=?;";
        Object[] params = {leave_case,leave.getStu_Id(),leave.getCou_Id(),leave.getLea_week()};
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
    public Leave findLeaveDirectly(int course_id, String leave_week, int student_id) {
        String sql = "select `leave`.* from `leave` where course_Id= ? and student_Id= ? and leave_week= ? ;";
        Object[] params = {course_id,student_id,leave_week};
        Connection con = BaseDao.getCon();
        PreparedStatement pstm = null;
        ResultSet rs  = null;
        Leave leave = new Leave();
        try{
                 rs = BaseDao.executeQuery(con, pstm, sql, rs,params);
                while (rs.next()){
                    int student_id1 = rs.getInt("student_Id");
                    System.out.println("student_id1" +student_id1);
                    int course_id1 = rs.getInt("course_Id");
                    String leave_reason = rs.getString("leave_Reason");
                    String leave_case = rs.getString("leave_Case");
                    String leave_week1 = rs.getString("leave_week");
                    leave.setCou_Id(course_id1);
                    leave.setStu_Id(student_id1);
                    leave.setLea_week(leave_week1);
                    leave.setLea_Case(leave_case);
                    leave.setLea_Reason(leave_reason);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        if (rs!=null){
        return leave;}
        else {
            return null;
        }
    }
}
