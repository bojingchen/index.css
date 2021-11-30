package com.zust.pro.servlet.user;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.zust.pro.dao.user.UserDao;
import com.zust.pro.service.user.UserService;
import com.zust.pro.service.user.UserServiceImpl;
import com.zust.pro.tools.ServletTool;
import com.zust.pro.tools.TransTool;
import com.zust.pro.vo.Student;
import com.zust.pro.vo.Teacher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.Inet4Address;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 86178
 */
@WebServlet("/managers/*")
public class ManagerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        String methodName = ServletTool.getMethodName(uri);
        try{
            Method method = this.getClass().getDeclaredMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
            method.invoke(this,req,resp);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void AddTeacher(HttpServletRequest req, HttpServletResponse resp){
        Teacher teacher = new Teacher();
        String ton = req.getParameter("Tno");
        String birth = req.getParameter("birth");
        String tname = req.getParameter("Tname");
        String sex = req.getParameter("sex");

        if (!ton.equals("")||!birth.equals("")||!tname.equals("")||!sex.equals("")){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(birth);
            long time = date.getTime();
            String birthday = String.valueOf(time);
            teacher.setTec_Birth(birthday);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int no = Integer.parseInt(ton);
        teacher.setId(no);
        teacher.setTec_Sex(sex);
        teacher.setTec_Name(tname);
        UserService userService = new UserServiceImpl();
        userService.addTeacher(teacher);
        req.setAttribute("info","添加成功！");}
        else {
            req.setAttribute("info","添加失败！");
        }
        try {
            req.getRequestDispatcher("/mag_insert.jsp").forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void AddStudent(HttpServletRequest req, HttpServletResponse resp){
        Student student = new Student();
        String sno = req.getParameter("Sno");
        String year = req.getParameter("year");
        String sname = req.getParameter("Sname");
        String dept = req.getParameter("dept");
        String aClass = req.getParameter("class");
        String sex = req.getParameter("sex");
        if (!sno.equals("")&&!year.equals("")||!"".equals(sname)||!dept.equals("")||!aClass.equals("")||!sex.equals("")) {
            int i = Integer.parseInt(sno);
            student.setId(i);
            student.setStu_Sex(sex);
            student.setStu_year(year);
            student.setStu_Name(sname);
            student.setStu_Academy(dept);
            student.setStu_Class(aClass);
            System.out.println(student.toString());
            UserService userService = new UserServiceImpl();
            userService.addStudent(student);
            req.setAttribute("info", "添加成功！");
            try {
                req.getRequestDispatcher("/mag_insert.jsp").forward(req, resp);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            req.setAttribute("info", "添加失败！");
            try {
                req.getRequestDispatcher("/mag_insert.jsp").forward(req, resp);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void getMessage(HttpServletRequest req, HttpServletResponse resp){
        UserService userService = new UserServiceImpl();
        Map info = userService.getInfo();
        //得到拿到的是第几页
        String page = req.getParameter("page");
        int pageNo =0;
        if (page==null){
            pageNo=0;
        }else{
           pageNo = Integer.parseInt(page);
        }


        List studentList = (List) info.get("studentList");
        req.setAttribute("page",pageNo);
        //分页处理
        String strNum = req.getParameter("strNum");
        System.out.println(strNum);
        int pageNum = 1;
        int student_size = studentList.size();
        int maxPage = 0;
        //如果第一次执行就不会接收数据
        if (strNum ==null){
            strNum = "1";
        }else {
            pageNum = Integer.parseInt(strNum);
        }
        if (student_size%14==0){
            maxPage = student_size/14;
        }else {
            maxPage = student_size/14 +1;
        }
        req.setAttribute("student_size",student_size);
        req.setAttribute("maxPage",maxPage);
        req.setAttribute("studentList",studentList);
        req.setAttribute("pageNum",pageNum);
        req.setAttribute("strNum",strNum);




        // 教师部分
        List teacherList = (List) info.get("teacherList");

        //老师分页处理
        String strNum1 = req.getParameter("strNum1");
        System.out.println("老师页面"+strNum1);
        int pageNum1 = 1;
        int teacher_size = teacherList.size();
        int maxPage1 = 0;
        //如果第一次执行就不会接收数据
        if(strNum1 ==null){
            strNum1 = "1";
        }else {
            pageNum1 = Integer.parseInt(strNum1);
        }
        if (teacher_size%14==0){
            maxPage1 = teacher_size/14;
        }else {
            maxPage1 = teacher_size/14+1;
        }
        req.setAttribute("teacher_size",teacher_size);
        req.setAttribute("maxPage1",maxPage1);
        req.setAttribute("teacherList",teacherList);
        req.setAttribute("pageNum1",pageNum1);
        req.setAttribute("strNum1",strNum1);


        // 课程部分
        List courseList = (List) info.get("courseList");

        //课程分页处理
        String strNum2 = req.getParameter("strNum2");
        System.out.println("课程页面"+strNum2);
        int pageNum2 = 1;
        int course_size = courseList.size();
        int maxPage2 = 0;
        //如果第一次执行就不会接收数据
        if(strNum2 ==null){
            strNum2 = "1";
        }else {
            pageNum2 = Integer.parseInt(strNum2);
        }
        if (course_size%14==0){
            maxPage2 = course_size/14;
        }else {
            maxPage2 = course_size/14+1;
        }
        req.setAttribute("course_size",course_size);
        req.setAttribute("maxPage2",maxPage2);
        req.setAttribute("courseList",courseList);
        req.setAttribute("pageNum2",pageNum2);
        req.setAttribute("strNum2",strNum2);

        try {
            req.getRequestDispatcher("/mag_update.jsp").forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ChangeStudent(HttpServletRequest req, HttpServletResponse resp){
        String i = req.getParameter("id");
        int id = Integer.parseInt(i);
        UserService userService = new UserServiceImpl();
        Student studentById = userService.getStudentById(id);
        String page = req.getParameter("page");
        String strNum = req.getParameter("strNum");
        String strNum1 = req.getParameter("strNum1");
        String strNum2 = req.getParameter("strNum2");
        req.setAttribute("page",page);
        req.setAttribute("strNum",strNum);
        req.setAttribute("strNum2",strNum2);
        req.setAttribute("strNum1",strNum1);
        req.setAttribute("Student",studentById);
        System.out.println(studentById.toString());
        try {
            req.getRequestDispatcher("/mag_update_stu.jsp").forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ChangeTeacher(HttpServletRequest req, HttpServletResponse resp){
        String i = req.getParameter("id");
        int id = Integer.parseInt(i);
        UserService userService = new UserServiceImpl();
        Teacher teacherById = userService.getTeacherById(id);
        String tec_birth = teacherById.getTec_Birth();
        teacherById.setTec_Birth(TransTool.getBirth(tec_birth));
        System.out.println("teacher_birth="+teacherById.getTec_Birth());
        String page = req.getParameter("page");
        String strNum = req.getParameter("strNum");
        String strNum1 = req.getParameter("strNum1");
        String strNum2 = req.getParameter("strNum2");
        req.setAttribute("page",page);
        req.setAttribute("strNum",strNum);
        req.setAttribute("strNum2",strNum2);
        req.setAttribute("strNum1",strNum1);
        req.setAttribute("teacher",teacherById);
        try {
            req.getRequestDispatcher("/mag_update_teh.jsp").forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ChangeTeacherInfo(HttpServletRequest req, HttpServletResponse resp){
        String information = "";
        Teacher teacher = new Teacher();
        String id = req.getParameter("id");
        String ton = req.getParameter("Tno");
        String birth = req.getParameter("birth");
        String tname = req.getParameter("Tname");
        String sex = req.getParameter("sex");
        HttpSession session = req.getSession(true);
        if (!ton.equals("")||!id.equals("")||!tname.equals("")){
        int ids = Integer.parseInt(id);
        int no = Integer.parseInt(ton);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(birth);
            long time = date.getTime();
            String birthday = String.valueOf(time);
            teacher.setTec_Birth(birthday);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        teacher.setId(no);
        teacher.setTec_Sex(sex);
        teacher.setTec_Name(tname);
        UserService userService = new UserServiceImpl();
        userService.delTeacherById(ids);
        userService.addTeacher(teacher);
        information = "修改成功";
        }
        else {
            information = "修改失败";
        }session.setAttribute("information",information);
        try {
            resp.sendRedirect("/managers/getMessage?page=1");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void DelTeacher(HttpServletRequest req, HttpServletResponse resp){
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
        UserService userService = new UserServiceImpl();
        Boolean b =  userService.delTeacherById(id);
        if (b==true){
            info= "教师删除成功";
        }
        try {
            req.getRequestDispatcher("/managers/getMessage"+"?page=1&"+"strNum1="+strNum1+"&strNum"+strNum+"&strNum2="+strNum2).forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeStudentInfo(HttpServletRequest req, HttpServletResponse resp){
        Student student = new Student();
        String sno = req.getParameter("id");
        String sno1 = req.getParameter("Sno");
        String year = req.getParameter("year");
        String sname = req.getParameter("Sname");
        String dept = req.getParameter("dept");
        String aClass = req.getParameter("class");
        String sex = req.getParameter("sex");
        String info = "";
        HttpSession session = req.getSession(true);
        if (!sno1.equals("")||!sname.equals("")){
        int i = Integer.parseInt(sno);
        int i1 = Integer.parseInt(sno1);
        student.setId(i1);
        student.setStu_Sex(sex);
        student.setStu_year(year);
        student.setStu_Name(sname);
        student.setStu_Academy(dept);
        student.setStu_Class(aClass);
        System.out.println(student.toString());
        session.setAttribute("information","修改成功");
        UserService userService = new UserServiceImpl();
        userService.delStudentById(i);
        System.out.println(student.toString());
        userService.addStudent(student);
        }else {
            session.setAttribute("information","修改失败");
        }

        try {
            resp.sendRedirect("/managers/getMessage");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void DelStudent(HttpServletRequest req, HttpServletResponse resp){
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
        UserService userService = new UserServiceImpl();
        Boolean b =  userService.delStudentById(id);
        if (b==true){
            info= "学生删除成功";
        }
        try {
            req.getRequestDispatcher("/managers/getMessage"+"?page=0&"+"strNum1="+strNum1+"&strNum"+strNum+"&strNum2="+strNum2).forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
