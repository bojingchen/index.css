package com.zust.pro.servlet.Course;

import com.zust.pro.service.Course.CourseService;
import com.zust.pro.service.Course.CourseServiceImpl;
import com.zust.pro.service.Leave.LeaveService;
import com.zust.pro.service.Leave.LeaveServiceImpl;
import com.zust.pro.service.user.UserService;
import com.zust.pro.service.user.UserServiceImpl;
import com.zust.pro.tools.ServletTool;
import com.zust.pro.vo.Course;
import com.zust.pro.vo.Leave;
import com.zust.pro.vo.Teacher;
import org.omg.PortableInterceptor.INACTIVE;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

@WebServlet("/Course/*")
public class CourseServlet extends HttpServlet {
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

    public void AddCourse(HttpServletRequest req, HttpServletResponse resp){
        String cno = req.getParameter("Cno");
        String cname = req.getParameter("Cname");
        String teacher = req.getParameter("Teacher");
        String classRoom = req.getParameter("ClassRoom");
        String times = req.getParameter("Times");
        String time = req.getParameter("time");
        String aClass = req.getParameter("Class");
        String start = req.getParameter("start");
        String end = req.getParameter("end");
        if (!cno.equals("")&&!cname.equals("")&&!teacher.equals("")&&!classRoom.equals("")&&!times.equals("")&&!time.equals("")&&!aClass.equals("")&&!start.equals("")&&!end.equals("")) {
            int teacherId = Integer.parseInt(teacher);
            int no = Integer.parseInt(cno);
            Course course = new Course();
            course.setCou_id(no);
            course.setCou_Name(cname);
            course.setTeacherId(teacherId);
            course.setCouClassroom(classRoom);
            course.setTClass(aClass);
            course.setCouPeriod(times);
            String setCou_Time = null;
            setCou_Time = time + "," + start + "," + end;
            course.setCou_Time(setCou_Time);

            CourseService courseService = new CourseServiceImpl();
            boolean b = courseService.AddCourse(course);
            if (b == true) {
                req.setAttribute("info", "添加成功！");
            } else {
                req.setAttribute("info", "添加失败！");
            }
        }else {
            req.setAttribute("info", "添加失败！");
        }
        try {
            req.getRequestDispatcher("/mag_insert.jsp").forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void DelCourse(HttpServletRequest req, HttpServletResponse resp){
        String strNum1 = req.getParameter("strNum1");
        String strNum = req.getParameter("strNum");
        String strNum2 = req.getParameter("strNum2");
        System.out.println(strNum+" ======="+strNum1);
        if (strNum==null){
            strNum = "0";
        }
        if (strNum1==null){
            strNum1="0";
        }
        if (strNum2==null){
            strNum2 ="0";
        }
        String i = req.getParameter("id");
        String info = "";
        int id = Integer.parseInt(i);
        CourseService courseService = new CourseServiceImpl();
        boolean b = courseService.delCourse(id);
        if (b==true){
            info = "删除成功";
        }
        req.setAttribute("info",info);
        try {
            req.getRequestDispatcher("/managers/getMessage"+"?page=2&"+"strNum1="+strNum1+"&strNum"+strNum+"&strNum2="+strNum2).forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ChangeCourse(HttpServletRequest req, HttpServletResponse resp){
        String ids = req.getParameter("id");
        String info = "";
        int id = Integer.parseInt(ids);
        String page = req.getParameter("page");
        String strNum = req.getParameter("strNum");
        String strNum1 = req.getParameter("strNum1");
        String strNum2 = req.getParameter("strNum2");
        req.setAttribute("page",page);
        req.setAttribute("strNum",strNum);
        req.setAttribute("strNum2",strNum2);
        req.setAttribute("strNum1",strNum1);
        CourseService courseService = new CourseServiceImpl();
        Course course = courseService.FindCourseById(id);
        req.setAttribute("course",course);

        try {
            req.getRequestDispatcher("/mag_update_course.jsp").forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void changeCourseInfo(HttpServletRequest req, HttpServletResponse resp){
        String ids = req.getParameter("id");
        //拿到表中的数据
        String cno = req.getParameter("Cno");
        String cname = req.getParameter("Cname");
        String croom = req.getParameter("Croom");
        String Times = req.getParameter("Ctimes");
        String time1 = req.getParameter("time1");
        String time2 = req.getParameter("time2");
        String time3 = req.getParameter("time3");
        String teacher = req.getParameter("Teacher");
        String aClass = req.getParameter("Class");
        HttpSession session = req.getSession(true);
        if (cno.equals("")&&cname.equals("")&&croom.equals("")&&Times.equals("")&&time2.equals("")&&time3.equals("")){
            session.setAttribute("information","修改失败");
        }
        else {
        int i = Integer.parseInt(cno);
        int i1 = Integer.parseInt(teacher);
        int id = Integer.parseInt(ids);
        CourseService courseService = new CourseServiceImpl();
        Course course = courseService.FindCourseById(id);
        course.setCou_id(i);
        course.setCou_Name(cname);
        course.setTeacherId(i1);
        course.setTClass(aClass);
        course.setCouClassroom(croom);
        course.setCouPeriod(Times);
        String setCou_Time = null;
        setCou_Time = time1+","+time2+","+time3;
        course.setCou_Time(setCou_Time);
        courseService.delCourse(id);
        courseService.AddCourse(course);
            session.setAttribute("information","修改成功");
        }
        try {
            resp.sendRedirect("/managers/getMessage?page=2");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void showMan_Reg(HttpServletRequest req, HttpServletResponse resp){
        CourseService courseService = new CourseServiceImpl();
        List<Course> courseList = courseService.FindCourses();
        String strNum = req.getParameter("strNum");
        int pageNum = 0;
        int maxPage = 0;
        int size = courseList.size();
        //如果第一次执行不接收数据
        if (strNum==null){
            strNum = "0";
        }else {
            pageNum = Integer.parseInt(strNum);
        }
        if (size%14==0){
            maxPage = size/14;
        }else {
            maxPage = size/14+1;
        }


        req.setAttribute("course_size",size);
        req.setAttribute("maxPage",maxPage);
        req.setAttribute("pageNum",pageNum);
        req.setAttribute("strNum",strNum);
        req.setAttribute("courseList",courseList);

        try{
            req.getRequestDispatcher("/mag_reg.jsp").forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void leaveCdnFromTeacher(HttpServletRequest req, HttpServletResponse resp){
        HttpSession session = req.getSession(true);
        Teacher teacher = (Teacher) session.getAttribute("Login_user");

        LeaveService leaveService = new LeaveServiceImpl();
        Map map = leaveService.getLeaveCdnByTeacher(teacher.getId());
        List<Leave> verify = (List<Leave>) map.get("verify");
        List<Leave> notVerify = (List<Leave>) map.get("NotVerify");
        req.setAttribute("verify",verify);
        req.setAttribute("notVerify",notVerify);

        try {
            req.getRequestDispatcher("/teh_leave_cdn.jsp").forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeLeaveCase(HttpServletRequest req, HttpServletResponse resp){
        HttpSession session = req.getSession(true);
        int number = (int)session.getAttribute("number");
        number = number -1;
        session.setAttribute("number",number);
        String agree = req.getParameter("agree");
        String sno = req.getParameter("sno");
        int Sno = Integer.parseInt(sno);
        String cweek = req.getParameter("cweek");
        String cid = req.getParameter("Cid");
        int i = Integer.parseInt(cid);
        LeaveService leaveService = new LeaveServiceImpl();
        boolean b = leaveService.changeLeaveCase(i, cweek, Sno, agree);
        String info = "";
        if (b==true){
            info = "同意成功";
        }else {
            info = "不同意！";
        }
        try {
            req.getRequestDispatcher("/Course/leaveCdnFromTeacher").forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
