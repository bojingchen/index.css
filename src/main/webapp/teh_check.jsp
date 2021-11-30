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
    <link rel="stylesheet" href="/css/check.css">
    <link rel="stylesheet" href="/css/unread.css">
    <script src="/js/jquery.min.js"></script>
    <script src="/js/common.js"></script>
</head>
<body>
    <div class="header">
        <span class="icon"></span>
        <h1>网络课程签到系统</h1>
        <a href="${pageContext.request.contextPath}/logout">退出</a>
        <p>
            <span class="hello"></span>
            <span class="username">${Login_user.getTec_Name()}</span>,欢迎您!

        </p>
    </div>
    <div class="time">
        <span class="icon"></span>
        <span class="now"></span>
        <span style="color: red">${info}</span>
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
                <li class="current"><a href="${pageContext.request.contextPath}/teacher/Check">查看信息</a></li>
                <li><a href="${pageContext.request.contextPath}/teacher/GetReg">课程签到</a></li>
                <li><a href="${pageContext.request.contextPath}/teacher/autPut">成绩导出</a></li>
                <li><a href="${pageContext.request.contextPath}/Course/leaveCdnFromTeacher">请假情况<span>${number}</span></a></li>
                <li><a href="${pageContext.request.contextPath}/logout">退出系统</a></li>
            </ul>

        </div>
        <div class="right">
            <div class="location">
                <strong>您现在所在的位置是:</strong>
                <span></span>
            </div>
            <div class="con">
                <div class="con_l">
                    <h2>指导课程</h2>
                    <ul>
                        <c:forEach items="${courseList}" var="course">
                            <li>${course.getCou_Name()}</li>
                        </c:forEach>
                    </ul>
                </div>
                <div class="con_r">
                    <h2>个人信息</h2>
                    <div class="mess">
                        教师工号: <input type="text" name=""  class="Tno" readonly value="${teacher.getId()}"><br>
                        教师生日: <input type="date" name=""  class="year" readonly value="${teacher_Birth}"><br>
                        教师姓名: <input type="text" name=""  class="Tname" readonly value="${teacher.getTec_Name()}"><br>
                        教师性别: <input type="text" name=""  class="Tsex" readonly value="${teacher.getTec_Sex()}"><br>
                        <form action="/teacher/Check" method="POST">
                            请输入旧密码: <input type="password" name="oldPassword" class="opwd"><br>
                            请输入新密码: <input type="password" name="newPassword1" class="npwd"><br>
                            请确认新密码: <input type="password" name="newPassword2" class="npwd"><br>
                            <input type="submit" value="保存" class="keep">
                            <input type="reset" value="重置" class="reset">
                        </form>
                    </div>
                </div>
            </div>

        </div>
    </div>
    <div class="footer">网络课程签到系统</div>
    <script>
        $(".location span").html($(".current a").html()+"页面");
        if($(".func li span").html()==0){
            $(".func li span").hide();
        }
    </script>
</body>
</html>