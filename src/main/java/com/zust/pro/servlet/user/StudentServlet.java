package com.zust.pro.servlet.user;

import com.zust.pro.dao.Leave.LeaveDao;
import com.zust.pro.dao.Leave.LeaveDaoImpl;
import com.zust.pro.dao.elective.ElectiveDao;
import com.zust.pro.dao.elective.ElectiveDaoImpl;
import com.zust.pro.service.Course.CourseService;
import com.zust.pro.service.Course.CourseServiceImpl;
import com.zust.pro.service.Elective.ElectiveService;
import com.zust.pro.service.Elective.ElectiveServiceImpl;
import com.zust.pro.service.Leave.LeaveService;
import com.zust.pro.service.Leave.LeaveServiceImpl;
import com.zust.pro.service.user.UserService;
import com.zust.pro.service.user.UserServiceImpl;
import com.zust.pro.tools.ServletTool;
import com.zust.pro.tools.SignTool;
import com.zust.pro.vo.Course;
import com.zust.pro.vo.Elective;
import com.zust.pro.vo.Leave;
import com.zust.pro.vo.Student;
import com.zust.pro.vo.temper.LeaveCdn;
import com.zust.pro.vo.temper.Sign;

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
@WebServlet("/student/*")
public class StudentServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //判断是否有签到任务，如果有的话，写到session里面
        Sign needSign = null;
        ServletContext servletContext = this.getServletContext();
        List<Sign> signList = (List<Sign>)servletContext.getAttribute("SignList");
        if (signList!=null){
        HttpSession session1 = req.getSession(true);
        Student user =(Student) session1.getAttribute("Login_user");
        session1.setAttribute("flag", "false");
        boolean findThisCourse = false;
        if (signList!=null) {
            for (Sign sign : signList) {
                System.out.println(signList);
                if (sign.getCourse_StartTime()<=System.currentTimeMillis()&&System.currentTimeMillis()<=sign.getCourse_EndTime()){
                CourseService courseService = new CourseServiceImpl();
                findThisCourse = courseService.isFindThisCourse(user.getId(), sign.getCour_id());
                System.out.println(findThisCourse);
                if (findThisCourse == true) {
                    LeaveDao leaveDao = new LeaveDaoImpl();
                    Leave leaveDirectly = leaveDao.findLeaveDirectly(sign.getCour_id(), sign.getWeek(), user.getId());
                    System.out.println(leaveDirectly);
                    if (leaveDirectly.getStu_Id() == 0) {
                        needSign = sign;
                        ElectiveDao electiveDao = new ElectiveDaoImpl();
                        Elective thisElective1 = electiveDao.getThisElective(user.getId(), sign.getCour_id());
                        String sign_case = thisElective1.getSign_case();
                        System.out.println("sign_Case="+sign_case);
                        System.out.println("week="+sign.getWeek());
                        String s = SignTool.GetStatusWeek(sign_case, sign.getWeek());
                        if (s.equals("0")) {
                            session1.setAttribute("needSign", needSign);
                        System.out.println("needSignStart"+needSign);
                            session1.setAttribute("flag", "true");
                        }
                    }
                }
            }
            }
        }
        }
//        System.out.println(111);
        String uri = req.getRequestURI();
        String methodName = ServletTool.getMethodName(uri);
        try{
            Method method = this.getClass().getDeclaredMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
            method.invoke(this,req,resp);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void SetNewPassWord(HttpServletRequest req, HttpServletResponse resp){
        String newPassWord = req.getParameter("newPassWord");
        String newPassword2 = req.getParameter("newPassword2");
        HttpSession session = req.getSession(true);
        Student user =(Student) session.getAttribute("Login_user");
        String info = "";

        if (newPassWord.equals(newPassword2)){
            UserService userService  = new UserServiceImpl();
            userService.changePasswordFromStudent(user.getId(),newPassWord);
            info = "密码更改成功!";
        }
        else {
            info = "密码更改失败";
        }
        req.setAttribute("info",info);
        try {
            req.getRequestDispatcher("/stu_index.jsp").forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void checks(HttpServletRequest req, HttpServletResponse resp){
        HttpSession session = req.getSession(true);
        Student student = (Student)session.getAttribute("Login_user");
        int id = student.getId();
        String stu_class = student.getStu_Class();
        CourseService courseService = new CourseServiceImpl();
        List<Course> courses = courseService.FindCourseByClass(stu_class);

        session.setAttribute("courses",courses);

        try {
            req.getRequestDispatcher("/stu_check.jsp").forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void checkChangePwd(HttpServletRequest req, HttpServletResponse resp){
        HttpSession session = req.getSession(true);
        Student user =(Student) session.getAttribute("Login_user");
        Object oldPassword = req.getParameter("oldPassword");
        String newPassword1 = req.getParameter("newPassword1");
        String newPassword2 = req.getParameter("newPassword2");
        String info = "";
        if (oldPassword.equals("")||newPassword1.equals("")||newPassword2.equals("")){
            session.setAttribute("info","密码没有输入");
        }else {
            if (oldPassword.equals(user.getPassword())) {
                if (newPassword1.equals(newPassword2)) {
                    UserService userService = new UserServiceImpl();
                    boolean b = userService.changePasswordFromStudent(user.getId(), newPassword1);
                    if (b == true) {
                        info = "修改密码成功";
                    } else {
                        info = "修改密码失败";
                    }
                } else {
                    info = "两次输入密码不一致";
                }
            } else {
                info = "旧密码错误";
            }
            session.setAttribute("info", info);
        }
        try {
            req.getRequestDispatcher("/stu_check.jsp").forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void StuLeaveCdnMessage(HttpServletRequest req, HttpServletResponse resp){
        LeaveService leaveService = new LeaveServiceImpl();
        HttpSession session = req.getSession(true);
        Student student = (Student)session.getAttribute("Login_user");
        int student_Id = student.getId();
//        System.out.println("学生的学号为："+ student_Id);
        List<LeaveCdn> leaveCdnList = leaveService.getLeaveCdnListByStudent(student_Id);
        System.out.println(leaveCdnList);
        //得到拿到的是第几页
        String strNum = req.getParameter("strNum");
        //分页处理
        int pageNum = 0;
        int leaveCdn_size = leaveCdnList.size();
        int maxPage = 0;
        //如果第一次执行就不会接收数据
        if(strNum == null){
            strNum = "0";
        }else {
            pageNum = Integer.parseInt(strNum);
        }
        if(leaveCdn_size%14==0){
            maxPage = leaveCdn_size/14;
        }else {
            maxPage = leaveCdn_size/14+1;
        }
        req.setAttribute("leaveCdn_size",leaveCdn_size);
        req.setAttribute("maxPage",maxPage);
        req.setAttribute("leaveCdnList",leaveCdnList);
        req.setAttribute("pageNum",pageNum);
        req.setAttribute("strNum",strNum);

        try {
            req.getRequestDispatcher("/stu_leave_cdn.jsp").forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void StudentLeaveRequest(HttpServletRequest req, HttpServletResponse resp){
        String courseId1= req.getParameter("courseId");
        System.out.println("coueseId= "+ courseId1);
        String sno1 = req.getParameter("Sno");
        String week_f = req.getParameter("week_f");
        String info = "";
        String leaveReason = req.getParameter("leaveReason");
        if (sno1.equals("")||week_f.equals("")||leaveReason.equals("")){
            info = "不能输入空的信息";
        }
        else {
        int courseId = Integer.parseInt(courseId1);
        int sno = Integer.parseInt(sno1);
        Leave leave = new Leave();
        leave.setCou_Id(courseId);
        leave.setLea_Reason(leaveReason);
        leave.setStu_Id(sno);
        leave.setLea_week(week_f);

        LeaveService leaveService = new LeaveServiceImpl();
        boolean b = leaveService.addLeave(leave);
        if (b==true){
            info = "请假成功";
        }else{
            info = "请假失败";
        }
        }
        HttpSession session = req.getSession(true);
        session.setAttribute("info",info);
        try {
            req.getRequestDispatcher("/student/StuRequest").forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void StuRequest(HttpServletRequest req, HttpServletResponse resp){
        HttpSession session = req.getSession(true);
        Student student = (Student) session.getAttribute("Login_user");

        CourseService courseService = new CourseServiceImpl();
        List<Course> courseList = courseService.FindCourses();
        if (courseList!=null) {
            List<Course> realCourse = new ArrayList<>();
            for (Course course : courseList) {
                if (course.getTClass().equals(student.getStu_Class())) {
                    realCourse.add(course);
                }
            }
            req.setAttribute("courseList", realCourse);
        }  else {req.setAttribute("courseList", null);}
        try {
            req.getRequestDispatcher("/stu_leave.jsp").forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void checkIsSign(HttpServletRequest req, HttpServletResponse resp){
        HttpSession session = req.getSession(true);
        Sign needSign = (Sign) session.getAttribute("needSign");
        if (needSign==null){
            req.setAttribute("flag","false");
        }else {
            req.setAttribute("flag","true");
        }
        System.out.println("needSign:" + needSign);
        session.setAttribute("needSign",needSign);
        try {
            req.getRequestDispatcher("/stu_sign.jsp").forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void checkSign(HttpServletRequest req, HttpServletResponse resp){
        HttpSession session = req.getSession(true);
        Student student = (Student)session.getAttribute("Login_user");
        Sign needSign =(Sign) session.getAttribute("needSign");
        int stu_id = student.getId();
        String cno = req.getParameter("Cno");
        int course_id = Integer.parseInt(cno);
        String week = req.getParameter("week");
        int weekNum = Integer.parseInt(week);
        ElectiveDao electiveDao = new ElectiveDaoImpl();
        Elective elective = electiveDao.getThisElective(stu_id,course_id);
        Long send_time = needSign.getSend_time();
        System.out.println(elective);
        ElectiveService electiveService = new ElectiveServiceImpl();
        electiveService.addElective(elective,weekNum,send_time);
        req.setAttribute("flag","false");
        session.removeAttribute("needSign");
        try {
            req.getRequestDispatcher("/stu_sign.jsp").forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void login(HttpServletRequest req, HttpServletResponse resp){
        try {
            req.getRequestDispatcher("/stu_index.jsp").forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getCdn(HttpServletRequest req, HttpServletResponse resp){
        HttpSession session = req.getSession(true);
        Student student = (Student) session.getAttribute("Login_user");
        int i = student.getId();
        ElectiveDao electiveDao = new ElectiveDaoImpl();
        List<Elective> electiveList = electiveDao.getEleByStudentId(i);
        req.setAttribute("electiveList",electiveList);
        System.out.println("=============================================");
        System.out.println(electiveList);
        try {
            req.getRequestDispatcher("/stu_reg.jsp").forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
