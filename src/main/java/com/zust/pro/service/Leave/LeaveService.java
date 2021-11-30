package com.zust.pro.service.Leave;

import com.zust.pro.vo.Leave;
import com.zust.pro.vo.temper.LeaveCdn;

import java.util.List;
import java.util.Map;

/**
 * @author 86178
 */
public interface LeaveService {

    /**
     * 学生显示请假情况
     * @param student_Id
     * @return list
     * **/
    public List<LeaveCdn> getLeaveCdnListByStudent(int student_Id);

    /**
     * 增加请假记录
     * @param leave
     * @return boolean
     * **/
    public boolean addLeave(Leave leave);

    /**
     * 得到这个老师有关系的请假情况，map里面有两个list
     * @param teacher_id
     * @return Map
     * **/
    public Map getLeaveCdnByTeacher(int teacher_id);


    /**
     * 老师的页面得到未处理的请假情况
     * @param teacher_id
     * @return  int
     * **/
    public int getNotNotVerify(int teacher_id);

    /**
     * 老师更改学生的请假请求
     * @param leave_case
     * @param student_id
     * @param leave_week
     * @param course_id
     * @return boolean
     * **/
    public boolean changeLeaveCase(int course_id,String leave_week,int student_id,String leave_case);

}
