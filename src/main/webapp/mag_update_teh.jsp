<%@ page import="com.zust.pro.vo.Student" %>
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/location.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mag_update.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mag_insert.css">
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/common.js"></script>
    <script src="${pageContext.request.contextPath}/js/mag_update.js"></script>
    <style>
        .con {
            margin-top: 20px;
        }
        .con .mess {
            border: 0;
        }
        .location {
            position: relative;
        }
        i {
            color: #2179a9;
            font-weight: 700;
            font-style: normal;
        }
        .goback {
            position: absolute;
            right: 20px;
            width: 50px;
            top: 32px;
            height: 25px;
            font-weight: 700;
            font-size: 14px;
            color: white;
            border: 1px solid #ccc;
            text-align: center;
            line-height: 25px;
            background-color: yellowgreen;
            border-radius: 3px;
            box-shadow: 1px 1px 3px black;
            cursor: pointer;
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
            <span class="username">管理员</span>,欢迎您!
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
                <li><a href="${pageContext.request.contextPath}/mag_insert.jsp">录入信息</a></li>
                <li class="current"><a href="${pageContext.request.contextPath}/managers/getMessage">修改信息</a></li>
                <li><a href="${pageContext.request.contextPath}/Course/showMan_Reg">签到情况</a></li>
                <li><a href="${pageContext.request.contextPath}/logout">退出系统</a></li>
            </ul>
        </div>
        <div class="right">
           <div class="location">
               <strong>您现在所在的位置是:</strong>
               <span></span>&gt;<i>教师信息</i>
               <div class="goback" style="display: block;">返回</div>
           </div>
           <div class="con">
            <div class="mess" style="display: block;">
                <form action="${pageContext.request.contextPath}/managers/ChangeTeacherInfo?id=${teacher.getId()}" method="POST">
                    教师工号: <input type="text" name="Tno" id="Ton" class="Tno" value="${teacher.getId()}"><br>
                    教师生日: <input type="date" name="birth" id="" class="birth" value="${teacher.getTec_Birth().trim()}"><br>
                    教师姓名: <input type="text" name="Tname" id="Tname" class="Tname" value="${teacher.getTec_Name()}"><br>
                    <c:if test="${teacher.getTec_Sex().equals('男')}">
                        学生性别: 男 <input type="radio" name="sex" id="sex1" value="男" checked>&nbsp;&nbsp;
                        女 <input type="radio" name="sex" id="sex2" value="女"><br>
                    </c:if>
                    <c:if test="${teacher.getTec_Sex().equals('女')}">
                        学生性别: 男 <input type="radio" name="sex" id="sex1" value="男" >&nbsp;&nbsp;&nbsp;&nbsp;
                        女 <input type="radio" name="sex" id="sex2" value="女" checked><br>
                    </c:if>
                    <input type="submit" value="修改" class="add">
                    <input type="reset" value="重置" class="reset">
                </form>
            </div>
           </div>
        </div>
    </div>
    <div class="footer">网络课程签到系统</div>
    <script>
        $(function(){
            $(".goback").click(function(){
                // window.sessionStorage.setItem("pos",("i").html());
                window.location.href="${pageContext.request.contextPath}/managers/getMessage?page=${page}&strNum=${strNum}&strNum1=${strNum1}&strNum2=${strNum2}";
            })
        })
    </script>
</body>
</html>