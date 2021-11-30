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
                <li><a href="${pageContext.request.contextPath}/student/getCdn">签到情况</a></li>
                <li><a href="${pageContext.request.contextPath}/student/StuRequest">课程请假</a></li>
                <li><a href="${pageContext.request.contextPath}/student/checkIsSign">学生签到</a><c:if test="${flag.equals('true')}"><span></span></c:if></li>
                <li class="current"><a href="${pageContext.request.contextPath}/student/StuLeaveCdnMessage">请假情况</a></li>
                <li><a href="${pageContext.request.contextPath}/logout">退出系统</a></li>
            </ul>
        </div>
        <div class="right">
            <div class="location">
                <strong>您现在所在的位置是:</strong>
                <span></span>
            </div>
            <div class="title">请假情况</div>
            <div class="con">
                <table>
                    <tr>
                        <th width="20%">课程号</th>
                        <th width="20%">课程名</th>
                        <th width="20%">课程时间</th>
                        <th width="20%">请假周数</th>
                        <th width="20%">是否通过</th>
                    </tr>
                        <c:forEach items="${leaveCdnList}" var="leaveCdn" begin="${pageNum*14}" end="${(pageNum+1)*14-1}">
                            <tr>
                                <td>${leaveCdn.getCourse_id()}</td>
                                <td>${leaveCdn.getCourseName()}</td>
                                <td>${leaveCdn.getRealTime()}</td>
                                <td>${leaveCdn.getLeave_week()}</td>
                                <td>${leaveCdn.getPass()}</td>
                            </tr>
                        </c:forEach>
                </table>
                <div class="position">
                    <div class="total">
                        共<span class="totals">${student_size}</span>条记录&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="cpage"></span>${pageNum+1}/<span class="tpage">${maxPage}</span>页
                    </div>

                    <div class="jump">
                        <a href="${pageContext.request.contextPath}/student/StuLeaveCdnMessage?strNum=0">[首页]</a>
                        <c:if test="${pageNum>0}">
                            <a href="${pageContext.request.contextPath}/student/StuLeaveCdnMessage?strNum=${strNum-1 }">[上一页]</a>
                        </c:if>
                        <c:if test="${pageNum==0}">
                            [上一页]
                        </c:if>

                        <c:if test="${pageNum<maxPage-1}">
                            <a href="${pageContext.request.contextPath}/student/StuLeaveCdnMessage?strNum=${strNum+1 }">[下一页]</a>
                        </c:if>
                        <c:if test="${pageNum==maxPage-1}">
                            [下一页]
                        </c:if>
                        <a href="${pageContext.request.contextPath}/student/StuLeaveCdnMessage?strNum=${maxPage-1}">[尾页]</a>
                    </div>
                    <div class="goto">
                        <form method="post" action="${pageContext.request.contextPath}/student/StuLeaveCdnMessage">
                            跳转至<input type="text" class="page" name="strNum">页&nbsp;
                            <input type="submit" value="GO" class="go">
                        </form>
                    </div>
                </div>
            </div>
        </div>
        </div>
    </div>
    <div class="footer">网络课程签到系统</div>
    <div class="leave_form">
        <form action="" method="POST">
                <span class="close">×</span>
                <h1>请假信息表</h1>
                课程号: <input type="text" name="" id=""  class="Cno" disabled><br>
                课程名: <input type="text" name="" id=""  class="Cname" disabled><br>
                <div class="Sno_f">学生学号: <input type="text" name="" id=""  class="Sno" disabled><br></div>
                <div class="week_f">请假周次: <input type="number" name="" id=""  class="week" min=1 max=20><br></div>
                <div class="reason_f">请假理由: <textarea name="" id="" cols="30" rows="10" class="reason"></textarea></div>
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