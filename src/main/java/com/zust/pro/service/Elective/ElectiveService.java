package com.zust.pro.service.Elective;

import com.zust.pro.dao.elective.ElectiveDao;
import com.zust.pro.dao.elective.ElectiveDaoImpl;
import com.zust.pro.vo.Elective;
import com.zust.pro.vo.temper.Sign;

import java.util.List;

/**
 * @author 86178
 */
public interface ElectiveService {
    /**
     * 老师发起签到功能,得到sign
     * @param course_id
     * @param teacher_id
     * @param course_time
     * @param week
     * @return String
     */
    public Sign sendSign(int course_id, int teacher_id,String course_time,String week );



    /**
     * 得到目前的签到信息
     * **/
    public List<Sign> getSign();

    /**
     * 删除这个list里的sign对象
     * @param sign
     * @param signList
     * @return boolean
     * **/
    public boolean DelSign(Sign sign,List<Sign> signList);


    /**
     * 学生点击确定
     * @param elective
     * @param weekNum
     * @return void
     * **/
    void addElective(Elective elective,int weekNum,long send_time);
}
