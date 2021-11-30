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
                <li class="current"><a href="${pageContext.request.contextPath}/teacher/autPut">成绩导出</a></li>
                <li><a href="${pageContext.request.contextPath}/Course/leaveCdnFromTeacher">请假情况<span>${number}</span></a></li>
                <li><a href="${pageContext.request.contextPath}/logout">退出系统</a></li>
            </ul>
        </div>
        <div class="right">
            <div class="location">
                <strong>您现在所在的位置是:</strong>
                <span></span>
            </div>
            <div class="title">成绩导出</div>
            <div class="con">
                <table>
                    <tr>
                        <th width="20%">课程号</th>
                        <th width="20%">课程名</th>
                        <th width="20%">学生学号</th>
                        <th width="20%">学生姓名</th>
                        <th width="20%">平时成绩</th>
                    </tr>
                    <c:forEach items="${teh_outputList}" var="teh_output" begin="${(pageNum-1)*14}" end="${pageNum*14-1}">
                        <tr>
                            <td>${teh_output.getCourse_Id()}</td>
                            <td>${teh_output.getCourse_Name()}</td>
                            <td>${teh_output.getStudent_Id()}</td>
                            <td>${teh_output.getStudent_Name()}</td>
                            <td>${teh_output.getPrice()}</td>
                        </tr>
                    </c:forEach>
                </table>
                <div class="position">
                    <div class="total">
                        共<span class="totals">${teh_outputList_size}</span>条记录&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="cpage"></span>${pageNum}/<span class="tpage">${maxPage}</span>页
                    </div>

                    <div class="jump">
                        <a href="${pageContext.request.contextPath}/teacher/autPut?strNum=1">[首页]</a>
                        <c:if test="${pageNum>1}">
                            <a href="${pageContext.request.contextPath}/teacher/autPut?strNum=${strNum-1 }">[上一页]</a>
                        </c:if>
                        <c:if test="${pageNum==1}">
                            [上一页]
                        </c:if>

                        <c:if test="${pageNum<maxPage}">
                            <a href="${pageContext.request.contextPath}/teacher/autPut?strNum=${strNum+1 }">[下一页]</a>
                        </c:if>
                        <c:if test="${pageNum==maxPage}">
                            [下一页]
                        </c:if>
                        <a href="${pageContext.request.contextPath}/teacher/autPut?strNum=${maxPage}">[尾页]</a>
                    </div>
                    <div class="goto">
                        <form method="post" action="${pageContext.request.contextPath}/teacher/autPut">
                            跳转至<input type="text" class="page" name="strNum">页&nbsp;
                            <input type="submit" value="GO" class="go">
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="footer">网络课程签到系统</div>
    <div class="background"></div>
    <script>
        $(".location span").html($(".current a").html()+"页面");
        if($(".func li span").html()==0){
            $(".func li span").hide();
        }
    </script>
</body>
</html>