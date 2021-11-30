package com.zust.pro.servlet.Course;

import com.zust.pro.dao.Leave.LeaveDao;
import com.zust.pro.dao.Leave.LeaveDaoImpl;
import com.zust.pro.dao.course.CourseDao;
import com.zust.pro.dao.course.CourseDaoImpl;
import com.zust.pro.dao.elective.ElectiveDao;
import com.zust.pro.dao.elective.ElectiveDaoImpl;
import com.zust.pro.service.Course.CourseService;
import com.zust.pro.service.Course.CourseServiceImpl;
import com.zust.pro.service.Elective.ElectiveService;
import com.zust.pro.service.Elective.ElectiveServiceImpl;
import com.zust.pro.service.user.UserService;
import com.zust.pro.service.user.UserServiceImpl;
import com.zust.pro.tools.ServletTool;
import com.zust.pro.tools.SignTool;
import com.zust.pro.vo.*;
import com.zust.pro.vo.temper.Sign;

import javax.crypto.Cipher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 86178
 */
@WebServlet("/Sign/*")
public class SignServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        String methodName = ServletTool.getMethodName(uri);
        try{
            Method method = this.getClass().getDeclaredMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
            method.invoke(this,req,resp);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void setSignFromTeacher(HttpServletRequest req, HttpServletResponse resp){
        String information = "";

        //得到这些信息先要
        HttpSession session = req.getSession(true);
        Teacher teacher = (Teacher) session.getAttribute("Login_user");
        int teh_id = teacher.getId();
        String week = req.getParameter("week");
        String time = req.getParameter("Ctime");
        String course_id = req.getParameter("course_id");
        int CId = Integer.parseInt(course_id);


        if (!week.equals("")){
        //拿到list,修改list
        ServletContext application  = this.getServletContext();
        List<Sign> signList = (List<Sign>)application.getAttribute("SignList");

        ElectiveService electiveService = new ElectiveServiceImpl();
        Sign sign = electiveService.sendSign(CId, teh_id, time, week);
        if (signList==null){
            signList = new ArrayList<Sign>();
        }
        if(sign.getCourse_StartTime()<System.currentTimeMillis()&&System.currentTimeMillis()<sign.getCourse_EndTime()) {
            signList.add(sign);
            application.setAttribute("SignList", signList);
            information = "添加成功";
            //对所有选了这门课的添加目录
            ElectiveDao electiveDao = new ElectiveDaoImpl();
            CourseDao courseDao = new CourseDaoImpl();
            List<Student> studentList = courseDao.findAllStudentByCourse(CId);
            System.out.println(studentList);
            for (Student stu:studentList) {
                String sign_case ="";
                boolean bool = electiveDao.findElective(stu.getId(), CId);
                System.out.println(bool);
                if (bool==false){
                    boolean b = electiveDao.addElective(stu.getId(), CId);
                }else {
                    Elective thisElective = electiveDao.getThisElective(stu.getId(), CId);
                    sign_case = thisElective.getSign_case();
                }
                String s = SignTool.insertStr(sign_case, week, "0");
                electiveDao.changeSign_case(stu.getId(),CId,s);
                LeaveDao leaveDao = new LeaveDaoImpl();
                Leave leaveDirectly = leaveDao.findLeaveDirectly(CId,week, stu.getId());
                if(leaveDirectly.getStu_Id()!=0) {
                    s = SignTool.updateStr(s,week,"3");
                    electiveDao.changeSign_case(leaveDirectly.getStu_Id(),CId,s);
                }
                System.out.println(leaveDirectly);
            }
        }else{
            information = "时间不对，添加失败";
        }
        session.setAttribute("information",information);

        }
        else{
            information = "没有添加周次";
            session.setAttribute("information",information);
        }

        try {
            req.getRequestDispatcher("/teacher/GetReg").forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getSignCases(HttpServletRequest req, HttpServletResponse resp){
        String course_id = req.getParameter("courseId");
        int i = Integer.parseInt(course_id);
        ElectiveDao electiveDao = new ElectiveDaoImpl();
        List<Elective> electiveList = electiveDao.getEleByCourse_id(i);
        req.setAttribute("electiveList",electiveList);
        System.out.println("=============================================");
        System.out.println(electiveList);
        try {
            req.getRequestDispatcher("/mag_reg_cdn.jsp").forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
