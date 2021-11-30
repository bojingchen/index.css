<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>主页面</title>
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/location.css">
    <link rel="stylesheet" href="/css/mag_insert.css">
    <script src="/js/jquery.min.js"></script>
    <script src="/js/common.js"></script>
    <script src="/js/mag_insert.js"></script>
</head>
<body>
    <div class="header">
        <span class="icon"></span>
        <h1>网络课程签到系统</h1>
        <a href="${pageContext.request.contextPath}/logout">退出</a>
        <p>
            <span class="hello"></span>
            <span class="username">manager</span>,欢迎您!
        </p>
    </div>
    <div class="time">
        <span class="icon"></span>
        <span class="now"></span>
        <span><p style="color: red">${info}</p></span>
        <span class="tips">温馨提示:为了能正常浏览,请使用高版本浏览器!</span>
    </div>
    <div class="main">
        <div class="left">
            <div class="leftH2">
                <span class="H2left"></span>
                功能列表
                <span class="H2right"></span>
            </div>
            <ul class="func">
                <li class="current"><a  href="${pageContext.request.contextPath}/mag_insert.jsp">录入信息</a></li>
                <li><a href="${pageContext.request.contextPath}/managers/getMessage">修改信息</a></li>
                <li><a href="${pageContext.request.contextPath}/Course/showMan_Reg">签到情况</a></li>
                <li><a href="${pageContext.request.contextPath}/logout">退出系统</a></li>
            </ul>
        </div>
        <div class="right">
           <div class="location">
               <strong>您现在所在的位置是:</strong>
               <span></span>
           </div>
                <div class="button">添加学生</div>
                <div class="mess">
                    <form action="${pageContext.request.contextPath}/managers/AddStudent" method="POST">
                        学生学号: <input type="text" name="Sno" id="Son" class="Sno"><br>
                        入学年份: <select class="year" name="year" id="year">
                            <option value="0">请选择年份</option>
                        </select><br>
                        学生姓名: <input type="text" name="Sname" id="Sname" class="Sname"><br>
                        学生性别: 男 <input type="radio" name="sex" id="man" value="男" checked>&nbsp;&nbsp;&nbsp;&nbsp;
                        女 <input type="radio" name="sex" id="woman" value="女"><br>
                        所在学院: <select class="dept" name="dept" id="dept">
                        <option value="0">请选择学院</option>
                        <option value="信息与电子工程学院">信息与电子工程学院</option>
                        <option value="自动化与电气工程学院">自动化与电气工程学院</option>
                        <option value="生物与化学工程学院">生物与化学工程学院</option>
                        </select><br>
                        所在班级: <select class="class" name="class" id="class">
                            <option value="0">请选择班级</option>
                        </select><br>
                        <input type="submit" value="添加" class="add">
                        <input type="reset" value="重置" class="reset">
                    </form>
                </div>
                <div class="button">添加教师</div>    
                <div class="mess">
                    <form action="${pageContext.request.contextPath}/managers/AddTeacher" method="POST">
                        教师工号: <input type="text" name="Tno" id="Ton" class="Tno"><br>
                        教师生日: <input type="date" name="birth"  class="birth"><br>
                        教师姓名: <input type="text" name="Tname" id="Tname" class="Tname"><br>
                        教师性别: 男 <input type="radio" name="sex" id="男" value="男" checked>&nbsp;&nbsp;&nbsp;&nbsp;
                        女 <input type="radio" name="sex" id="女" value="女"><br>
                        <input type="submit" value="添加" class="add">
                        <input type="reset" value="重置" class="reset">
                    </form>
                </div>
                <div class="button">添加课程</div>
                <div class="mess">
                    <form action="${pageContext.request.contextPath}/Course/AddCourse" method="POST">
                        课程号: <input type="text" name="Cno" id="Con" class="Cno"><br>
                        课程名: <input type="text" name="Cname" id="Cname" class="Cname"><br>
                        老师id：<input type="text" name="Teacher" class="Cname"><br>
                        &nbsp;&nbsp;&nbsp;教室: <input type="text" name="ClassRoom"  class="Croom"><br>
                        课程班级: <input type="text" name="Class"  class="Croom"><br>
                        &nbsp;&nbsp;&nbsp;学时: <input type="text" name="Times"  class="Ctime"><br>
                        &nbsp;&nbsp;&nbsp;时间: <select name="time"  class="day">
                            <option value="1">星期一</option>
                            <option value="2">星期二</option>
                            <option value="3">星期三</option>
                            <option value="4">星期四</option>
                            <option value="5">星期五</option>
                        </select>
                        从<input type="number" name="start"  class="start">~
                        至<input type="number" name="end"  class="end">节课(1~12)<br>
                        <input type="submit" value="添加" class="add">
                        <input type="reset" value="重置" class="reset">
                    </form>
                    <p>${info}</p>
                </div>
        </div>
    </div>
    <div class="footer">网络课程签到系统</div>
</body>
</html>