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
    <link rel="stylesheet" href="/css/stu_reg.css">
    <link rel="stylesheet" href="/css/reg.css">
    <link rel="stylesheet" href="/css/unread.css">
    <script src="/js/jquery.min.js"></script>
    <script src="/js/common.js"></script>
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
                <li class="current"><a href="${pageContext.request.contextPath}/student/getCdn">签到情况</a></li>
                <li><a href="${pageContext.request.contextPath}/student/StuRequest">课程请假</a></li>
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
            <div class="title">签到情况</div>
            <div class="con">
                <table>
                    <tr>
                        <th width="10%">课程号</th>
                        <th width="10%">课程名</th>
                        <th width="10%">迟到次数</th>
                        <th width="10%">请假次数</th>
                        <th width="10%">缺勤次数</th>
                        <th width="10%">签到次数</th>
                        <th width="10%">总签次数</th>
                    </tr>
                        <c:forEach items="${electiveList}" var="Reg">
                            <tr>
                                <td>${Reg.getCourse_id()}</td>
                                <td>${Reg.getCourse_name()}</td>
                                <td>${Reg.getLeave_times()}</td>
                                <td>${Reg.getQingjia()}</td>
                                <td>${Reg.getOut_times()}</td>
                                <td>${Reg.getSign_times()}</td>
                                <td>${Reg.getTotal_times()}</td>
                            </tr>
                        </c:forEach>
                </table>
            </div>
        </div>
    </div>
    <div class="footer">网络课程签到系统</div>
    <script>
        $(".location span").html($(".current a").html()+"页面");
        if(!$){//后台
            $(".func li span").hide();
        }

    </script>
</body>
</html>