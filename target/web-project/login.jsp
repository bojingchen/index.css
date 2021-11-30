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
    <title>登录页面</title>
    <link rel="stylesheet" href="css/login.css">
    <script src="js/jquery.min.js"></script>
    <script src="js/login.js"></script>
</head>
<body>
    <div class="main">
        <h2>网络课程签到系统</h2>
        <div class="login">
                <div class="section">
                    <div class="op1 current">用户登录</div>
                    <div class="op2">后台登录</div>
                </div>
                <div class="input_form">
                    <div class="username"><span></span><input type="text" name="userid" id="userid" placeholder="请输入学号/工号"></div>
                    <div class="error1"></div>
                    <div class="password"><span></span><input type="password" name="pwd" id="pwd" placeholder="请输入密码"></div>
                </div>
                <div class="check">
                    <input type="text" name="" id="checkbox" class="checkcode" placeholder="请输入验证码">
                    <div class="code"></div>
                    <div class="change">看不清换一张</div>
                    <div class="error2"></div>
                </div>
                <div class="select">
                    <div class="auto">
                        <input type="checkbox" name="remember" id="remember" class="remember"><label for="remember">记住账号密码</label>
                    </div>
                    <div class="forget">
                        <a href="#">忘记密码?</a>
                    </div>
                </div>
                <input type="button" value="登录" class="submit">
        </div>
    </div>
</body>
</html>