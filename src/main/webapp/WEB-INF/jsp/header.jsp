<%--
  Created by IntelliJ IDEA.
  User: NIce
  Date: 2018-04-25
  Time: 14:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="layui-header layui-bg-green">
    <ul class="layui-nav layui-bg-green" style="float: left">
        <li class="layui-nav-item">
            <a href="${pageContext.request.contextPath}/index.do">Meeting Room Book System</a>
        </li>
        <c:if test="${empty user}">
            <li class="layui-nav-item">
                <a href="${pageContext.request.contextPath}/toLogin.do">Login</a>
            </li>
            <li class="layui-nav-item">
                <a href="${pageContext.request.contextPath}/toRegister.do">Sign Up</a>
            </li>
        </c:if>
    </ul>
    <c:if test="${not empty user}">
        <ul class="layui-nav layui-bg-green" style="float: right">
            <li class="layui-nav-item">${user.username}</li>
            <li class="layui-nav-item">
                <a href="${pageContext.request.contextPath}/user/logout.do">Logout</a>
            </li>
        </ul>

        <ul class="layui-nav layui-bg-green" style="float: right">
            <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/bookRecord/toBookRecord.do">Book Record</a></li>
        </ul>
    </c:if>
</div>
<hr>
