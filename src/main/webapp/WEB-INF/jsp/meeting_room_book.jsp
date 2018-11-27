<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>MRBook</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css" media="all">
    <script src="${pageContext.request.contextPath}/static/js/jquery-1.11.3.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
    <script>
        var startTimeOptions = new Array(${fn:length(timeList)});
        var endTimeOptions = new Array(${fn:length(timeList)});
        <c:forEach items="${timeList}" var="timeItem" varStatus="siteVarStatus">
        startTimeOptions[${siteVarStatus.index}] = '<option value="${timeItem.index}"><fmt:formatDate pattern="HH:mm" value="${timeItem.startTime}"/> </option>';
        endTimeOptions[${siteVarStatus.index}] = '<option value="${timeItem.index}"><fmt:formatDate pattern="HH:mm" value="${timeItem.endTime}"/> </option>';
        </c:forEach>
    </script>
</head>
<body class="layui-container">
<%@include file="header.jsp" %>
<div class="layui-row">
    <div class="layui-col-md10 layui-col-lg-offset1">
        <form class="layui-form" action="${pageContext.request.contextPath}/bookRecord/book.do" method="post">
            <input type="hidden" name="date" value="<fmt:formatDate value="${date}" pattern="yyyy-MM-dd"/>">
            <input type="hidden" name="meetingRoomId" value="${meetingRoom.id}">
            <input type="hidden" name="meetingRoomId" value="${meetingRoom.id}">
            <table class="layui-table">
                <tr class="layui-table-header">
                    <td colspan="4" align="center">
                        Meeting Room Book
                    </td>
                </tr>
                <tr>
                    <td align="center" width="20%">Meeting Room:</td>
                    <td width="30%">&emsp;${site.name}-${meetingRoom.name}</td>
                    <td align="center" width="20%">Date:</td>
                    <td width="30%">&emsp;${param.date}</td>
                </tr>
                <tr>
                    <td align="center">Start Time:</td>
                    <td><select name="startTimeIndex" id="startTimeIndex" lay-filter="startTimeIndex">
                        <c:forEach items="${timeList}" var="timeItem" varStatus="siteVarStatus">
                            <option value="${timeItem.index}"><fmt:formatDate value="${timeItem.startTime}"
                                                                           pattern="HH:mm"/></option>
                        </c:forEach>
                    </select></td>
                    <td align="center">End Time:</td>
                    <td><select name="endTimeIndex" id="endTimeIndex">
                        <c:forEach items="${timeList}" var="timeItem" varStatus="siteVarStatus">
                            <option value="${timeItem.index}"><fmt:formatDate value="${timeItem.endTime}"
                                                                           pattern="HH:mm"/></option>
                        </c:forEach>
                    </select></td>
                </tr>
                <tr>
                    <td colspan="4" align="center">
                        <input class="layui-btn" type="submit" value="Book">
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
</body>
</html>

<script>
    layui.use('form', function () {
        var form = layui.form;
        form.render();
        form.on('select(startTimeIndex)', function (data) {
            var endTimeIndexOptionsStr = "";
            for (var i = data.elem.selectedIndex; i < endTimeOptions.length; i++) {
                endTimeIndexOptionsStr += endTimeOptions[i] + " ";
            }
            $('#endTimeIndex')[0].innerHTML = endTimeIndexOptionsStr;
            layui.use('form', function () {
                var form = layui.form;
                form.render();
            });
        })
    })
    $(function () {

    })
</script>
