package com.zust.pro.dao.Leave;

import com.zust.pro.vo.Leave;
import com.zust.pro.vo.temper.LeaveCdn;

import java.util.List;

/**
 * @author 86178
 */
public interface LeaveDao {

    /**
     * 学生根据leave表，得到课程号，课程名，课程时间，请假周次，是否通过这几个信息
     * @param id
     * @return List
     * **/
    public List<LeaveCdn> getLeaveCdnListFromStudent(int id);

    /**
     * 学生提交请假信息，添加至leave数据库
     * 备注：ｌｅａｖｅ＿Ｃａｓｅ　写为０（表示未处理状态）
     * @param leave
     * @return boolean
     * **/
    public boolean addLeave(Leave leave);

    /**
     * 根据老师的id找到他所教的课程的请假情况(所有)
     * @param id
     * @return list
     * **/
     public List<Leave> findLeaveByTeacher(int id);

    /**
     * 查看老师所教的课程里面没有审批的请假的情况(leave_case=0的情况)
     * @param id
     * @return list
     */
    public List<Leave> findLeaveByTeacherNotVerify(int id);
    /**
     * 查看老师所教的课程里面没有审批的请假的情况(leave_case=0的情况)
     * @param id
     * @return list
     * **/
    public List<Leave> findLeaveByTeacherVerify(int id);

    /**
     * 更改这个leave请求的case为String leave_case
     * @param leave_case
     * @param leave
     * @return boolean
     * **/
    public boolean changeLeaveCase(Leave leave,String leave_case);

    /**
     * 根据course_Id,leave_week,student_id找到leave那一个
     * @param course_id
     * @param leave_week
     * @param student_id
     * @return Leave
     * **/
    public Leave findLeaveDirectly(int course_id,String leave_week,int student_id);

}
