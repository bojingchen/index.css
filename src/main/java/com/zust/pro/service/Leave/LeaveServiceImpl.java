package com.zust.pro.service.Leave;

import com.zust.pro.dao.Leave.LeaveDao;
import com.zust.pro.dao.Leave.LeaveDaoImpl;
import com.zust.pro.dao.course.CourseDao;
import com.zust.pro.service.user.UserService;
import com.zust.pro.service.user.UserServiceImpl;
import com.zust.pro.vo.Leave;
import com.zust.pro.vo.Teacher;
import com.zust.pro.vo.temper.LeaveCdn;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 86178
 */
public class LeaveServiceImpl implements LeaveService{
    LeaveDao leaveDao = new LeaveDaoImpl();

    @Override
    public List<LeaveCdn> getLeaveCdnListByStudent(int student_Id) {
        List<LeaveCdn> leaveCdnList = leaveDao.getLeaveCdnListFromStudent(student_Id);
        return leaveCdnList;
    }

    @Override
    public boolean addLeave(Leave leave) {
        boolean b = leaveDao.addLeave(leave);
        return b;
    }

    @Override
    public Map getLeaveCdnByTeacher(int teacher_id) {
        List<Leave> leaveByTeacherVerify = leaveDao.findLeaveByTeacherVerify(teacher_id);
        List<Leave> leaveByTeacherNotVerify = leaveDao.findLeaveByTeacherNotVerify(teacher_id);
        Map<String,List> map = new HashMap<String,List>();
        map.put("verify",leaveByTeacherVerify);
        map.put("NotVerify",leaveByTeacherNotVerify);
        return map;
    }

    @Override
    public int getNotNotVerify(int teacher_id) {
        List<Leave> leaveByTeacherNotVerify = leaveDao.findLeaveByTeacherNotVerify(teacher_id);
        return leaveByTeacherNotVerify.size();
    }

    @Override
    public boolean changeLeaveCase(int course_id, String leave_week, int student_id, String leave_case) {
        LeaveDao leaveDao = new LeaveDaoImpl();
        Leave leave = leaveDao.findLeaveDirectly(course_id, leave_week, student_id);
        boolean b = leaveDao.changeLeaveCase(leave, leave_case);
        return b;
    }



}
