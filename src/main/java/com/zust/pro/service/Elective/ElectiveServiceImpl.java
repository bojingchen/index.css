package com.zust.pro.service.Elective;

import com.zust.pro.dao.elective.ElectiveDao;
import com.zust.pro.dao.elective.ElectiveDaoImpl;
import com.zust.pro.tools.SignTool;
import com.zust.pro.tools.TimeTool;
import com.zust.pro.vo.Elective;
import com.zust.pro.vo.temper.Sign;
import javafx.application.Application;

import java.util.List;

/**
 * @author 86178
 */
public class ElectiveServiceImpl implements ElectiveService {
    ElectiveDao elective = new ElectiveDaoImpl();


    @Override
    public Sign sendSign(int course_id, int teacher_id, String course_time, String week) {
        Sign sign = new Sign();
        long currentTimeMillis = System.currentTimeMillis();
        sign.setSend_time(currentTimeMillis);
        sign.setTeh_id(teacher_id);
        sign.setCour_id(course_id);
        sign.setWeek(week);
        sign.setCourse_EndTime(TimeTool.end(course_time));
        sign.setCourse_StartTime(TimeTool.start(course_time));
        return sign;
    }


    @Override
    public List<Sign> getSign() {
        return null;
    }

    @Override
    public boolean DelSign(Sign sign, List<Sign> signList) {
        boolean remove = signList.remove(sign);
        return remove;
    }

    @Override
    public void addElective(Elective elective,int weekNum,long send_time) {
        ElectiveDao electiveDao = new ElectiveDaoImpl();
        String str = "";
        String status = "";
        long currentTimeMillis = System.currentTimeMillis();
        long gap = currentTimeMillis-send_time;
        if (gap > 1000 * 60 * 3){
            status = "2";
        }else {
            status = "1";
        }
        System.out.println(elective.toString());
        String s = SignTool.updateStr(elective.getSign_case(), weekNum + "", status);
        System.out.println(s);
        electiveDao.changeSign_case(elective.getStu_id(),elective.getCourse_id(),s);
    }
}
