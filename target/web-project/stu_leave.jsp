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
    <link rel="stylesheet" href="/css/stu_leave.css">
    <link rel="stylesheet" href="/css/reg.css">
    <link rel="stylesheet" href="/css/unread.css">
    <script src="/js/jquery.min.js"></script>
    <script src="/js/common.js"></script>
    <script src="/js/stu_leave.js"></script>
    <style>
        .right .icon {
            float: left;
            background: url(images/欢迎.png) no-repeat center;
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
            <span class="username">${Login_user.getStu_Name()}</span>,欢迎您!
            <span>${info}</span>
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
                <li><a href="${pageContext.request.contextPath}/student/checks">查看信息</a></li>
                <li><a href="${pageContext.request.contextPath}/student/getCdn">签到情况</a></li>
                <li class="current"><a href="${pageContext.request.contextPath}/student/StuRequest" >课程请假</a></li>
                <li><a href="${pageContext.request.contextPath}/student/checkIsSign">学生签到</a><c:if test="${flag.equals('true')}"><span></span></c:if></li>
                <li><a href="${pageContext.request.contextPath}/student/StuLeaveCdnMessage">请假情况</a></li>
                <li><a href="${pageContext.request.contextPath}/logout">退出系统</a></li>
            </ul>
        </div>
        <div class="right">
            <div class="location">
                <strong>您现在所在的位置是:</strong>
                <span></span>

            </div>
            <div class="title">课程请假</div>
            <div class="con">
                <table>
                    <tr>
                        <th width="25%">课程号</th>
                        <th width="25%">课程名</th>
                        <th width="25%">课程时间</th>
                        <th width="25%">操作</th>
                    </tr>
                    <c:forEach items="${courseList}" var="i">
                        <tr>
                            <td>${i.getCou_id()}</td>
                            <td>${i.getCou_Name()}</td>
                            <td>${i.getCourse_RealTime()}</td>
                            <td><span>请假</span></td>
                            <td hidden>${i.getCou_Time()}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
        </div>
    </div>
    <div class="footer">网络课程签到系统</div>
    <div class="leave_form">
        <form action="/student/StudentLeaveRequest" method="POST">
                <span class="close">×</span>
                <h1>请假信息表</h1>
                课程号: <input type="text" name="courseId"   class="Cno" readonly><br>
                课程名: <input type="text" name="courseName"   class="Cname" readonly><br>
                <div class="Sno_f">学生学号: <input type="text" name="Sno"   class="Sno" readonly><br></div>
                <div class="Ctime_f">课程时间: <input type="text" name="Ctime"   class="Ctime" readonly><br></div>
                <div class="week_f">请假周: <input type="number" name="week_f"  class="week" min=1 max=20><br></div>
                <div class="reason_f">请假理由: <textarea name="leaveReason"  cols="30" rows="10" class="reason"></textarea></div>
            <input type="submit" value="提交" class="submit">
            <input type="reset" value="取消" class="cancel">
        </form>
    </div>
    <script>
        $(".location span").html($(".current a").html()+"页面");
        if(!$){//后台
            $(".func li span").hide();
        }
    </script>
</body>
</html>