package com.zust.pro.servlet.user;

import com.alibaba.fastjson.JSON;
import com.zust.pro.service.Leave.LeaveService;
import com.zust.pro.service.Leave.LeaveServiceImpl;
import com.zust.pro.service.user.UserService;
import com.zust.pro.service.user.UserServiceImpl;
import com.zust.pro.vo.Manager;
import com.zust.pro.vo.Student;
import com.zust.pro.vo.Teacher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.util.HashMap;

/**
 * @author 86178
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("login======");
        //获取用户名和密码
        String userCode = req.getParameter("username");
        int i = Integer.parseInt(userCode);
        String password = req.getParameter("password");
        UserService userService = new UserServiceImpl();
        Object user = userService.login(i, password);
        System.out.println(user);
        if (user instanceof Teacher){
            HttpSession session = req.getSession(true);
            session.setAttribute("Login_user",user);
            HashMap<String, Object> map = new HashMap<>();
            LeaveService leaveService = new LeaveServiceImpl();
            int notNotVerify = leaveService.getNotNotVerify(((Teacher) user).getId());
            session.setAttribute("number",notNotVerify);
            map.put("urlHref","teh_index.jsp");
            map.put("flag",1);
            String jsonStr = JSON.toJSONString(map);
            PrintWriter writer = resp.getWriter();
            writer.write(jsonStr);
        }
       else if (user instanceof Student){
            HttpSession session = req.getSession(true);
            session.setAttribute("Login_user",user);
            HashMap<String, Object> map = new HashMap<>();
            map.put("urlHref","/student/login");
            map.put("flag",1);
            String jsonStr = JSON.toJSONString(map);
            PrintWriter writer = resp.getWriter();
            writer.write(jsonStr);
        }
       else if(user instanceof Manager){
            HttpSession session = req.getSession(true);
            session.setAttribute("Login_user",user);
            HashMap<String, Object> map = new HashMap<>();
            map.put("urlHref","mag_index.jsp");
            map.put("flag",1);
            String jsonStr = JSON.toJSONString(map);
            PrintWriter writer = resp.getWriter();
            writer.write(jsonStr);
        }
       else{
            HashMap<String, Object> map = new HashMap<>();
            map.put("errorMessage","密码或用户id错误，请重新输入");
            map.put("flag",0);

            String jsonStr = JSON.toJSONString(map);
            PrintWriter writer = resp.getWriter();
            writer.write(jsonStr);
        }
    }
}
