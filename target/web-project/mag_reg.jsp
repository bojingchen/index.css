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
               <span>签到情况页面</span>
           </div>
           <div class="check">
               <div class="course current">课程信息</div>
           </div>
           <div class="con">
                <div class="course_item" style="display: block;"><table>
                    <tr>
                        <th width="10%">课程号</th>
                        <th width="10%">课程名</th>
                        <th width="10%">教室</th>
                        <th width="10%">学时</th>
                        <th width="10%">时间</th>
                        <th width="30%">操作</th>
                    </tr>
                        <c:forEach items="${courseList}" var="course" begin="${pageNum*14}" end="${(pageNum+1)*14-1}">
                            <tr>
                                <td>${course.getCou_id()}</td>
                                <td>${course.getCou_Name()}</td>
                                <td>${course.getCouClassroom()}</td>
                                <td>${course.getCouPeriod()}</td>
                                <td>${course.getCourse_RealTime()}</td>
                                <td>
                                  <a href="${pageContext.request.contextPath}/Sign/getSignCases?courseId=${course.getCou_id()}" >查看</a>
                                </td>
                            </tr>
                        </c:forEach>
                </table>
                <div class="position">
                    <div class="total">
                        共<span class="totals">${course_size}</span>条记录&nbsp;;&nbsp;&nbsp;&nbsp;<span class="cpage"></span>${pageNum+1}/<span class="tpage">${maxPage}</span>页
                    </div>
                    <div class="jump">
                        <a href="${pageContext.request.contextPath}/Course/showMan_Reg?strNum=0">[首页]</a>
                        <c:if test="${pageNum>0}">
                            <a href="${pageContext.request.contextPath}/Course/showMan_Reg?strNum=${strNum-1}">[上一页]</a>
                        </c:if>
                        <c:if test="${pageNum==0}">
                            [上一页]
                        </c:if>

                        <c:if test="${pageNum<maxPage-1}">
                            <a href="${pageContext.request.contextPath}/Course/showMan_Reg?strNum=${strNum+1}">[下一页]</a>
                        </c:if>
                        <c:if test="${pageNum==maxPage-1}">
                            [下一页]
                        </c:if>
                        <a href="${pageContext.request.contextPath}/Course/showMan_Reg?strNum=${maxPage-1}">[尾页]</a>
                    </div>
                    <div class="goto">
                        <form action="" method="POST">
                            <input type="text" class="page" name="strNum">页
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