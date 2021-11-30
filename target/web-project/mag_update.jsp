<%@ page import="com.zust.pro.tools.TransTool" %>
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
               <span></span>
               <p style="color: red"> ${information}</p>
           </div>
            <div class="check">
                <div class="stu">学生信息</div>
                <div class="teh">教师信息</div>
                <div class="course">课程信息</div>
            </div>
            <div class="con">
                <div class="stu_item">
                    <table>
                        <tr>
                            <th width="10%">学生学号</th>
                            <th width="10%">入学年份</th>
                            <th width="10%">学生姓名</th>
                            <th width="10%">学生性别</th>
                            <th width="10%">所在学院</th>
                            <th width="10%">所在班级</th>
                            <th width="30%">操作</th>
                        </tr>
                        <c:forEach items="${studentList}" var="student" begin="${(pageNum-1)*14}" end="${pageNum*14-1}">
                            <tr>
                                <td>${student.getId()}</td>
                                <td>${student.getStu_year()}</td>
                                <td>${student.getStu_Name()}</td>
                                <td>${student.getStu_Sex()}</td>
                                <td>${student.getStu_Academy()}</td>
                                <td>${student.getStu_Class()}</td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/managers/ChangeStudent?id=${student.getId()}&&page=0&strNum=${strNum}&strNum1=${strNum1}&strNum2=${strNum2}">修改</a>
                                    <a href="${pageContext.request.contextPath}/managers/DelStudent?id=${student.getId()}&page=0&strNum=${strNum}&strNum1=${strNum1}&strNum2=${strNum2}">删除</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    <div class="position">
                        <div class="total">
                            共<span class="totals">${student_size}</span>条记录&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="cpage"></span>${pageNum}/<span class="tpage">${maxPage}</span>页
                        </div>

                        <div class="jump">
                            <a href="${pageContext.request.contextPath}/managers/getMessage?page=0&strNum=1&strNum1=${strNum1 }&strNum2=${strNum2}">[首页]</a>
                            <c:if test="${pageNum>1}">
                                <a href="${pageContext.request.contextPath}/managers/getMessage?page=0&strNum=${strNum-1 }&strNum1=${strNum1}&strNum2=${strNum2}">[上一页]</a>
                            </c:if>
                            <c:if test="${pageNum==1}">
                                [上一页]
                            </c:if>

                            <c:if test="${pageNum<maxPage}">
                                <a href="${pageContext.request.contextPath}/managers/getMessage?page=0&strNum=${strNum+1 }&strNum1=${strNum1}&strNum2=${strNum2}">[下一页]</a>
                            </c:if>
                            <c:if test="${pageNum==maxPage}">
                                [下一页]
                            </c:if>
                            <a href="${pageContext.request.contextPath}/managers/getMessage?page=0&strNum=${maxPage}&strNum1=${strNum1}&strNum2=${strNum2}">[尾页]</a>
                        </div>
                        <div class="goto">
                            <form method="post" action="${pageContext.request.contextPath}/managers/getMessage">
                                跳转至<input type="text" class="page" name="strNum">页&nbsp;
                                <input type="submit" value="GO" class="go">
                            </form>
                        </div>
                    </div>
                </div>  <!--




                    从此处修改教师



                    -->

                <div class="teh_item"><table>
                    <tr>
                        <th width="10%">教师工号</th>
                        <th width="10%">教师生日</th>
                        <th width="10%">教师姓名</th>
                        <th width="10%">教师性别</th>
                        <th width="30%">操作</th>
                    </tr>

                    <c:forEach items="${teacherList}" var="teacher" begin="${(pageNum1-1)*14}" end="${pageNum1*14-1}">
                        <tr>
                            <td>${teacher.getId()}</td>
                            <td>${TransTool.getBirth(teacher.getTec_Birth())}</td>
                            <td>${teacher.getTec_Name()}</td>
                            <td>${teacher.getTec_Sex()}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/managers/ChangeTeacher?id=${teacher.getId()}&page=1&strNum=${strNum}&strNum1=${strNum1}&strNum2=${strNum2}">修改</a>
                                <a href="${pageContext.request.contextPath}/managers/DelTeacher?id=${teacher.getId()}&page=1&strNum=${strNum}&strNum1=${strNum1}&strNum2=${strNum2}">删除</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                    <div class="position">
                        <div class="total">
                            共<span class="totals">${teacher_size}</span>条记录&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="cpage"></span>${pageNum1}/<span class="tpage">${maxPage1}</span>页
                        </div>

                        <div class="jump">
                            <a href="${pageContext.request.contextPath}/managers/getMessage?page=1&strNum1=1&strNum=${strNum}&strNum2=${strNum2}">[首页]</a>
                            <c:if test="${pageNum1>1}">
                                <a href="${pageContext.request.contextPath}/managers/getMessage?page=1&strNum1=${strNum1-1}&strNum=${strNum}&strNum2=${strNum2}">[上一页]</a>
                            </c:if>
                            <c:if test="${pageNum1==1}">
                                [上一页]
                            </c:if>
                            <c:if test="${pageNum1<maxPage1}">
                                <a href="${pageContext.request.contextPath}/managers/getMessage?page=1&strNum1=${strNum1+1 }&strNum=${strNum}&strNum2=${strNum2}">[下一页]</a>
                            </c:if>
                            <c:if test="${pageNum1==maxPage1}">
                                [下一页]
                            </c:if>
                            <a href="${pageContext.request.contextPath}/managers/getMessage?page=1&strNum1=${maxPage1}&strNum=${strNum}&strNum2=${strNum2}">[尾页]</a>
                        </div>
                        <div class="goto">
                            <form method="post" action="${pageContext.request.contextPath}/managers/getMessage?page=1">
                                跳转至&nbsp;&nbsp;<input type="text" class="page" name="strNum1">页&nbsp;
                                <input type="submit" value="GO" class="go">
                            </form>
                        </div>
                    </div></div>
                <div class="course_item"><table>
                    <tr>
                        <th width="10%">课程号</th>
                        <th width="10%">课程名</th>
                        <th width="10%">教室</th>
                        <th width="10%">学时</th>
                        <th width="10%">时间</th>
                        <th width="30%">操作</th>
                    </tr>
                    <c:forEach items="${courseList}" var="course" begin="${(pageNum2-1)*14}" end="${pageNum2*14-1}">
                        <tr>
                            <td>${course.getCou_id()}</td>
                            <td>${course.getCou_Name()}</td>
                            <td>${course.getCouClassroom()}</td>
                            <td>${course.getCouPeriod()}</td>
                            <td>${course.getCourse_RealTime()}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/Course/ChangeCourse?id=${course.getCou_id()}&page=2&strNum=${strNum}&strNum1=${strNum1}&strNum2=${strNum2}">修改</a>
                            <a href="${pageContext.request.contextPath}/Course/DelCourse?id=${course.getCou_id()}&page=2&strNum=${strNum}&strNum1=${strNum1}&strNum2=${strNum2}">删除</a>
                        </td>
                        </tr>
                    </c:forEach>
                </table>
                    <div class="position">
                        <div class="total">
                            共<span class="totals">${course_size}</span>条记录&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="cpage"></span>${pageNum2}/<span class="tpage">${maxPage2}</span>页
                        </div>

                        <div class="jump">
                            <a href="${pageContext.request.contextPath}/managers/getMessage?page=2&strNum1=${strNum1}&strNum=${strNum}&strNum2=1">[首页]</a>
                            <c:if test="${pageNum2>1}">
                                <a href="${pageContext.request.contextPath}/managers/getMessage?page=2&strNum2=${strNum2-1}&strNum=${strNum}&strNum1=${strNum1}">[上一页]</a>
                            </c:if>
                            <c:if test="${pageNum2==1}">
                                [上一页]
                            </c:if>

                            <c:if test="${pageNum2<maxPage2}">
                                <a href="${pageContext.request.contextPath}/managers/getMessage?page=2&strNum2=${strNum2+1}&strNum=${strNum}&strNum1=${strNum1}">[下一页]</a>
                            </c:if>
                            <c:if test="${pageNum2==maxPage2}">
                                [下一页]
                            </c:if>
                            <a href="${pageContext.request.contextPath}/managers/getMessage?page=2&strNum2=${maxPage2}&strNum=${strNum}&strNum1=${strNum1}">[尾页]</a>
                        </div>
                        <div class="goto">
                            <form method="post" action="${pageContext.request.contextPath}/managers/getMessage?page=2">
                                跳转至&nbsp;&nbsp;<input type="text" class="page" name="strNum2">页&nbsp;
                                <input type="submit" value="GO" class="go">
                            </form>
                        </div>
                    </div></div>
            </div>
        </div>
    </div>
    <div class="footer">网络课程签到系统</div>
</body>
<script>
    $(function(){

        $(".location span").html($(".current a").html()+"页面");
        $(".check>div").click(function(){
            $(this).addClass("current").siblings().removeClass("current");
            var index=$(this).index();
            $(".con>div").eq(index).stop().show().siblings().hide();
        })
        if(window.sessionStorage.getItem("pos")){
            $(".check div:contains("+window.sessionStorage.getItem("pos")+")").click();
        }
        window.sessionStorage.clear();
        var page="${page}";
        if(page==0){
            $(".stu").click();
        }else if(page==1){
            $(".teh").click();
        }else {
            $(".course").click();
        }
    })
</script>
</html>