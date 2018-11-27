<%--
  Created by IntelliJ IDEA.
  User: NIce
  Date: 2018-04-25
  Time: 14:11
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css">
    <script src="${pageContext.request.contextPath}/static/js/jquery-1.11.3.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
    <script type="text/javascript">
        function book(timeIndex) {
            $("[name=timeIndex]").val(timeIndex);
            $('#toBookUI')[0].submit();
        }
    </script>
</head>
<body class="layui-container">
<%@include file="header.jsp" %>
<form id="toBookUI" action="${pageContext.request.contextPath}/bookRecord/toBookUI.do" method="post">
    <input type="hidden" name="date" value="${param.date}">
    <input type="hidden" name="siteId" value="${param.siteId}">
    <input type="hidden" name="meetingRoomId" value="${param.meetingRoomId}">
    <input type="hidden" name="timeIndex">
</form>
<c:if test="${not empty timeList}">
    <h3 align="center">${param.date} ${site.name} ${meetingRoom.name}</h3>
    <table class="layui-table">
        <tr class="layui-table-header">
            <td width="5%" align="center">index</td>
            <td width="20%" align="center">start time</td>
            <td width="20%" align="center">end time</td>
            <td width="30%" align="center">user</td>
            <td width="25%" align="center">do</td>
        </tr>
        <c:forEach items="${timeList}" var="time" varStatus="status">

            <tr
                    <c:if test="${not empty map[time.index]}">
                        class="layui-bg-red"
                    </c:if>
            >
                <td align="center">
                        ${status.index + 1}
                </td>
                <td align="center">
                    <fmt:formatDate value="${time.startTime}" pattern="HH:mm"/>
                </td>
                <td align="center">
                    <fmt:formatDate value="${time.endTime}" pattern="HH:mm"/>
                </td>
                <td align="center">
                    <c:if test="${not empty map[time.index]}">
                        ${map[time.index]['username']}
                    </c:if>
                </td>
                <td align="center">
                    <c:if test="${empty map[time.index]}">
                        <a class="layui-btn" href="javascript:void(0)" onclick="book('${time.index}')">
                            Book
                        </a>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>
