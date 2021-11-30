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
    <link rel="stylesheet" href="/css/index.css">
    <link rel="stylesheet" href="/css/unread.css">
    <script src="/js/jquery.min.js"></script>
    <script src="/js/common.js"></script>
    <style>
        .right .icon {
            float: left;
            background: url(/images/欢迎.png) no-repeat center;
            background-size: 75% 75%;
            width: 200px;
            height: 200px;
        }
        .right h1 {
            color: rgb(44, 151, 229);
            margin-top: 60px;
        }
        .right p {
            margin-top: 10px;
            color: #808589;
        }
    </style>
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
                <li><a href="${pageContext.request.contextPath}/teacher/Check">查看信息</a></li>
                <li><a href="${pageContext.request.contextPath}/teacher/GetReg">课程签到</a></li>
                <li><a href="${pageContext.request.contextPath}/teacher/autPut">成绩导出</a></li>
                <li><a href="${pageContext.request.contextPath}/Course/leaveCdnFromTeacher">请假情况<span>${number}</span></a></li>
                <li><a href="${pageContext.request.contextPath}/logout">退出系统</a></li>
            </ul>
        </div>
        <div class="right">
            <span class="icon"></span>
            <h1>${user.getName()}</h1>
            <p>欢迎来到网络课程签到系统!</p>
        </div>
    </div>
    <div class="footer">网络课程签到系统</div>
    <div class="updatepwd">
        <form action="" method="POST">
            <div class="input_form">
                <h1>初次登录,请修改密码!</h1>
                <div class="username"><span></span><input type="password" name=""  placeholder="请输入密码" class="fpwd"></div>
                <div class="error"></div>
                <div class="password"><span></span><input type="password" name=""  placeholder="请再次输入密码" class="spwd"></div>
            </div>
            <input type="submit" value="修改" class="submit">
            <input type="reset" value="重置" class="reset">
        </form>
    </div>
    <div class="background"></div>
    <script>
        if($(".func li span").html()==0){
            $(".func li span").hide();
        }
    </script>
</body>
</html>