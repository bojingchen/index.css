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
    <link rel="stylesheet" href="/css/mag_update.css">
    <script src="/js/jquery.min.js"></script>
    <script src="/js/common.js"></script>
    <script src="/js/mag_update.js"></script>
    <style>
        i {
            color: #2179a9;
            font-weight: 700;
            font-style: normal;
        }
        .location {
            position: relative;
        }
        .goback {
            position: absolute;
            right: 20px;
            width: 50px;
            top: 40px;
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
                <li><a href="${pageContext.request.contextPath}/managers/getMessage">修改信息</a></li>
                <li class="current"><a href="${pageContext.request.contextPath}/mag_reg.jsp">签到情况</a></li>
                <li><a href="${pageContext.request.contextPath}/logout">退出系统</a></li>
            </ul>
        </div>
        <div class="right">
           <div class="location">
               <strong>您现在所在的位置是:</strong>
               <span></span>&gt;<i>签到情况页面</i>
               <a href="${pageContext.request.contextPath}/Course/showMan_Reg" class="goback">返回</a>
           </div>
           <div class="check">
               <div class="course current">签到情况</div>
           </div>
           <div class="con">
                <div class="course_item" style="display: block;"><table>
                    <tr>
                        <th width="10%">学生学号</th>
                        <th width="10%">课程名</th>
                        <th width="10%">迟到次数</th>
                        <th width="10%">签到次数</th>
                        <th width="10%">总签次数</th>
                    </tr>
                 <c:forEach items="${electiveList}" var="elective">
                     <tr>
                         <td>${elective.getStu_id()}</td>
                         <td>${elective.getCourse_name()}</td>
                         <td>${elective.getLeave_times()}</td>
                         <td>${elective.getSign_times()}</td>
                         <td>${elective.getTotal_times()}</td>
                     </tr>
                 </c:forEach>
                </table>
                <div class="position">
                    <div class="total">
                        共<span class="totals"></span>条记录&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="cpage"></span>/<span class="tpage"></span>页
                    </div>
                    <div class="goto">
                        <form action="" method="POST">
                            跳转至&nbsp;&nbsp;<input type="text" class="page">页&nbsp;
                            <input type="submit" value="GO">
                        </form>
                    </div>
                </div>
            </div>
           </div>
        </div>
    </div>
    <div class="footer">网络课程签到系统</div>
</body>
</html>