package com.zust.pro.servlet.user;

import com.zust.pro.service.Course.CourseService;
import com.zust.pro.service.Course.CourseServiceImpl;
import com.zust.pro.service.user.UserService;
import com.zust.pro.service.user.UserServiceImpl;
import com.zust.pro.tools.ServletTool;
import com.zust.pro.vo.Course;
import com.zust.pro.vo.Teacher;
import com.zust.pro.vo.temper.teh_output;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/teacher/*")
public class TeacherServlet extends HttpServlet {
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

    public void GetReg(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        CourseService courseService = new CourseServiceImpl();
        HttpSession session = req.getSession(true);
        Teacher teacher = (Teacher) session.getAttribute("Login_user");
        int teacher_Id = teacher.getId();

        List<Course> courseList = courseService.FindCourseBytehId(teacher_Id);
        //得到拿到的是第几页
        String strNum = req.getParameter("strNum");
        //分页处理
        int pageNum = 1;
        int courseList_size = courseList.size();
        int maxPage = 0;
        //如果第一次执行就不会接收数据
        if(strNum == null){
            strNum = "1";
        }else {
            pageNum = Integer.parseInt(strNum);
        }
        if(courseList_size%14==0){
            maxPage = courseList_size/14;
        }else {
            maxPage = courseList_size/14+1;
        }
        req.setAttribute("courseList_size",courseList_size);
        req.setAttribute("maxPage",maxPage);
        req.setAttribute("courseList",courseList);
        req.setAttribute("pageNum",pageNum);
        req.setAttribute("strNum",strNum);

        try {
            req.getRequestDispatcher("/teh_reg.jsp").forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void autPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        CourseService courseService = new CourseServiceImpl();
        HttpSession session = req.getSession(true);
        Teacher teacher = (Teacher) session.getAttribute("Login_user");
        int teacher_Id = teacher.getId();

        List<teh_output> teh_outputList = courseService.findStu_CouByTeacherId(teacher_Id);
        System.out.println("===========================================");
        System.out.println(teh_outputList.toString());
        System.out.println("===========================================");
        //得到拿到的是第几页
        String strNum = req.getParameter("strNum");
        //分页处理
        int pageNum = 1;
        int teh_outputList_size = teh_outputList.size();
        int maxPage = 0;
        //如果第一次执行就不会接收数据
        if(strNum == null){
            strNum = "1";
        }else {
            pageNum = Integer.parseInt(strNum);
        }
        if(teh_outputList_size%14==0){
            maxPage = teh_outputList_size/14;
        }else {
            maxPage = teh_outputList_size/14+1;
        }
        req.setAttribute("teh_outputList_size",teh_outputList_size);
        req.setAttribute("maxPage",maxPage);
        req.setAttribute("teh_outputList",teh_outputList);
        req.setAttribute("pageNum",pageNum);
        req.setAttribute("strNum",strNum);

        try {
            req.getRequestDispatcher("/teh_output.jsp").forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Check(HttpServletRequest req,  HttpServletResponse resp) throws ServletException, IOException{
        CourseService courseService = new CourseServiceImpl();
        HttpSession session = req.getSession(true);
        Teacher teacher = (Teacher) session.getAttribute("Login_user");
        int teacher_Id = teacher.getId();
        String teacher_Birth = teacher.getTec_Birth();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd ");
//一般网上的转换是没有中间new Long（timeStamp）,因为他们都是精确到毫秒的时间戳，不用再乘以1000进行转换
        long longTimeStamp = Long.parseLong(teacher_Birth);
        Date date = new Date(longTimeStamp);
        String dareString = simpleDateFormat.format(date).trim();

        req.setAttribute("teacher",teacher);
        req.setAttribute("teacher_Birth",dareString);
        List<Course> courseList = courseService.FindCourseBytehId(teacher_Id);
        req.setAttribute("courseList",courseList);

        Object oldPassword = req.getParameter("oldPassword");
        String newPassword1 = req.getParameter("newPassword1");
        String newPassword2 = req.getParameter("newPassword2");
        String info = "";
        if(oldPassword==null||newPassword1==null||newPassword2==null){
            info="";
        }else{
            if (oldPassword.equals(teacher.getPassword())){
                if (newPassword1.equals(newPassword2)){
                    UserService userService = new UserServiceImpl();
                    boolean b = userService.changePasswordFromTeacher(teacher.getId(), newPassword1);
                    if (b == true){
                        info = "修改密码成功";
                    }else {
                        info = "修改密码失败";
                    }
                }else {
                    info = "两次输入密码不一致";
                }
            }else {
                info="旧密码错误";
            }
        }
        req.setAttribute("info",info);
        try {
            req.getRequestDispatcher("/teh_check.jsp").forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
