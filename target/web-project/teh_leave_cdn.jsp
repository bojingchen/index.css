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
    <link rel="stylesheet" href="/css/unread.css">
    <script src="/js/jquery.min.js"></script>
    <script src="/js/common.js"></script>
    <script src="/js/teh_leave.js"></script>
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
                <li class="current"><a href="${pageContext.request.contextPath}/Course/leaveCdnFromTeacher">请假情况<span>${number}</span></a></li>
                <li><a href="${pageContext.request.contextPath}/logout">退出系统</a></li>
            </ul>

        </div>
        <div class="right">
            <div class="location">
                <strong>您现在所在的位置是:</strong>
                <span>请假情况页面</span>
            </div>
            <div class="title">请假情况</div>
            <div class="con">
                <table>
                    <tr>
                        <th width="12%">学生学号</th>
                        <th width="12%">学生姓名</th>
                        <th width="12%">课程号</th>
                        <th width="12%">课程名</th>
                        <th width="12%">课程时间</th>
                        <th width="12%">请假周次</th>
                        <th width="28%">是否通过</th>
                    </tr>
                    <c:forEach items="${notVerify}" var="leave">
                        <tr>
                            <td>${leave.getStu_Id()}</td>
                            <td>${leave.getStudent_name()}</td>
                            <td>${leave.getCou_Id()}</td>
                            <td>${leave.getCourse_name()}</td>
                            <td>${leave.getReal_time()}</td>
                            <td>${leave.getLea_week()}</td>
                            <td>
                                <span >查看</span>
                                <input hidden value="${leave.getLea_Reason()}" class="reson">
                            </td>
                        </tr>
                    </c:forEach>
                    <c:forEach items="${verify}" var="left">
                        <tr>
                            <td>${left.getStu_Id()}</td>
                            <td>${left.getStudent_name()}</td>
                            <td>${left.getCou_Id()}</td>
                            <td>${left.getCourse_name()}</td>
                            <td>${left.getReal_time()}</td>
                            <td>${left.getLea_week()}</td>
                            <td>
                                ${left.getIsPass()}
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
        </div>
    </div>
    <div class="footer">网络课程签到系统</div>
    <div class="leave_form">
        <form action="" method="POST" name="form1">
                <span class="close">×</span>
                <h1>请假信息表</h1>
                <div class="Sno_f">学生学号: <input type="text" name="sno"   class="Sno" readonly><br></div>
                <div class="Sname_f">学生姓名: <input type="text" name="sname"   class="Sname" readonly><br></div>
                课程号: <input type="text" name="Cid"   class="Cid" readonly><br>
                课程名: <input type="text" name="cname"   class="Cname" readonly><br>
                <div class="week_f">请假周次: <input type="number" name="cweek"  class="week" min=1 max=20 readonly><br></div>
                <div class="reason_f">请假理由: <textarea name="c_reason"  cols="30" rows="10" class="reason" readonly></textarea></div>
                <input type="hidden" value="">
            <input type="button" value="接受" class="submit">
            <input type="button" value="拒绝" class="cancel">
        </form>
    </div>
    <div class="background"></div>
    <script>
        // $(".location span").html($(".current a").html()+"页面");
        if($(".func li span").html()==0){
            $(".func li span").hide();
        }
        $(".submit").click(function(){
            document.form1.action="/Course/changeLeaveCase?agree=1";//接受
            document.form1.submit();
        })
        $(".cancel").click(function(){
            document.form1.action="/Course/changeLeaveCase?agree=2";//拒绝
            document.form1.submit();
        })
    </script>
</body>
</html>