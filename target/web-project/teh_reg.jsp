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
        <span style="color: red">${information}</span>
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
                <li class="current"><a href="${pageContext.request.contextPath}/teacher/GetReg">课程签到</a></li>
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
            <div class="title">课程签到</div>
            <div class="con">
                <table>
                    <tr>
                        <th width="10%">课程号</th>
                        <th width="10%">课程名</th>
                        <th width="10%">教室</th>
                        <th width="10%">学时</th>
                        <th width="10%">时间</th>
                        <th width="30%">操作</th>
                    </tr>
                    <c:forEach items="${courseList}" var="course" begin="${(pageNum-1)*14}" end="${pageNum*14-1}">
                        <tr>
                            <td>${course.getCou_id()}</td>
                            <td>${course.getCou_Name()}</td>
                            <td>${course.getCouClassroom()}</td>
                            <td>${course.getCouPeriod()}</td>
                            <td>${course.getCourse_RealTime()}</td>
                            <td>
                                <a href="#">发布签到</a>
                            </td>
                            <td hidden>${course.getCou_Time()}</td>
                        </tr>
                    </c:forEach>
                </table>
                <div class="position">
                    <div class="total">
                        共<span class="totals">${course_size}</span>条记录&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="cpage"></span>${pageNum}/<span class="tpage">${maxPage}</span>页
                    </div>

                    <div class="jump">
                        <a href="${pageContext.request.contextPath}/teacher/GetReg?strNum=1">[首页]</a>
                        <c:if test="${pageNum>1}">
                            <a href="${pageContext.request.contextPath}/teacher/GetReg?strNum=${strNum-1 }">[上一页]</a>
                        </c:if>
                        <c:if test="${pageNum==1}">
                            [上一页]
                        </c:if>

                        <c:if test="${pageNum<maxPage}">
                            <a href="${pageContext.request.contextPath}/teacher/GetReg?strNum=${strNum+1 }">[下一页]</a>
                        </c:if>
                        <c:if test="${pageNum==maxPage}">
                            [下一页]
                        </c:if>
                        <a href="${pageContext.request.contextPath}/teacher/GetReg?strNum=${maxPage}">[尾页]</a>
                    </div>
                    <div class="goto">
                        <form method="post" action="${pageContext.request.contextPath}/teacher/GetReg">
                            跳转至<input type="text" class="page" name="strNum">页&nbsp;
                            <input type="submit" value="GO" class="go">
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="footer">网络课程签到系统</div>
    <div class="rege_form" style="height: 350px;">
        <form action="${pageContext.request.contextPath}/Sign/setSignFromTeacher" method="POST" name="form1">
            <span class="close">×</span>
            <h1>签到表</h1>
            课程名: <input type="text" name=""   class="Cname" readonly><br>
            <div class="Ctime_f">课程时间: <input type="text" name="time"   class="Ctime" readonly><br></div>
            <div class="week_f">上课周次: <input type="number" name="week"   class="week" min=1 max=20><br></div>
            <input type="submit" value="发起" class="submit">
            <input type="button" value="取消" class="cancel">
            <input type="text" value="" name="course_id" hidden class="course_id">
            <input type="text" value="" name="Ctime" hidden class="cetime">
        </form>
    </div>
    <div class="background"></div>
    <script>
        $(".location span").html($(".current a").html()+"页面");
        if($(".func li span").html()==0){
            $(".func li span").hide();
        }
        $(".cancel").click(function(){
            $(".rege_form").hide();
        })
        $(".close").click(function(){
            $(".rege_form").hide();
        })
        $(".con table tr td a").click(function(){
            $(".rege_form").show();
            $(".Cname").val($(this).parent().siblings().eq(1).html());
            $(".Ctime").val($(this).parent().siblings().eq(4).html());
            $(".course_id").val($(this).parent().siblings().eq(0).html());
            $(".cetime").val($(this).parent().siblings().eq(5).html())
        })
    </script>
</body>
</html>