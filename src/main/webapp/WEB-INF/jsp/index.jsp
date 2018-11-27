<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
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
    <title>MRBS</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css" media="all">
    <script src="${pageContext.request.contextPath}/static/js/jquery-1.11.3.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
    <script type="text/javascript">
        function book(timeIndex, siteId, meetingRoomId) {
            $("[name=timeIndex]").val(timeIndex);
            $("[name=siteId]").val(siteId);
            $("[name=meetingRoomId]").val(meetingRoomId);
            $('#toBookUI')[0].submit();
        }
    </script>
</head>
<body class="layui-container">
<%@include file="header.jsp" %>
<form id="toBookUI" action="${pageContext.request.contextPath}/bookRecord/toBookUI.do" method="post" target="_blank">
    <input type="hidden" name="date" value="${nowDate}">
    <input type="hidden" name="siteId" >
    <input type="hidden" name="meetingRoomId">
    <input type="hidden" name="timeIndex">
</form>
<form class="layui-form" action="${pageContext.request.contextPath}/bookRecord/selectDetail.do" method="post">
    <div class="layui-row layui-col-space1">
        <div class="layui-col-md1 layui-col-lg-offset2">
            <label class="layui-form-label  " style="text-align: right">Date:&nbsp;</label>
        </div>
        <div class="layui-col-md2">
            <input type="text" class="layui-input" id="date" name="date" placeholder="yyyy-MM-dd" readonly>
        </div>
        <div class="layui-col-md1">
            <label class="layui-form-label " style="text-align: right">Site:&nbsp;</label>
        </div>
        <div class="layui-col-md2">
            <select id="siteId" name="siteId" lay-filter="siteId" required>
                <option value="">请选择</option>
            </select>
        </div>
        <div class="layui-col-md1">
            <label class="layui-form-label " style="text-align: right">MRoom:&nbsp;</label>
        </div>
        <div class="layui-col-md2">
            <select id="meetingRoomId" name="meetingRoomId" required>
                <option value="">请选择</option>
            </select>
        </div>
        <div class="layui-col-md1">
            <input class="layui-btn" type="submit" style="width: 100%" value="Search">
        </div>
    </div>
</form>
<hr>
<div class="layui-tab layui-tab-brief">
    <ul class="layui-tab-title layui-bg-gray ">
        <c:forEach items="${siteList}" var="site" varStatus="siteVarStatus">
            <li <c:if test="${site.id == user.siteId}">class="layui-this"</c:if>>${site.name}</li>
        </c:forEach>
    </ul>
    <div class="layui-tab-content">
        <c:forEach items="${siteList}" var="site" varStatus="siteVarStatus">
            <div class="layui-tab-item <c:if test="${site.id == user.siteId}">layui-show</c:if>">
                <table class="layui-table">
                    <tr>
                        <td align="center">MR</td>
                        <c:forEach items="${timeList}" var="time">
                            <td align="center"><fmt:formatDate value="${time.startTime}" pattern="HH:mm"/> </td>
                        </c:forEach>
                    </tr>
                    <c:forEach items="${meetingRoomGroupBySiteIdList[siteVarStatus.index]}" var="meetingRoom" varStatus="meetingRoomVarStatus">
                        <tr>
                            <td align="center">${meetingRoom.name}</td>
                            <c:forEach items="${timeList}" var="time">
                                <td >
                                    <c:if test="${not empty mapListList[siteVarStatus.index][meetingRoomVarStatus.index][time.index]}">
                                        <input type="button" value=" " class="layui-btn  layui-btn-danger" disabled>
                                    </c:if>
                                    <c:if test="${ empty mapListList[siteVarStatus.index][meetingRoomVarStatus.index][time.index]}">
                                        <input type="button" value="" onclick="book('${time.index}','${site.id}','${meetingRoom.id}')" class="layui-btn" >
                                    </c:if>
                                </td>
                            </c:forEach>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </c:forEach>
    </div>
</div>

<script>
    layui.use('element', function () {
        var element = layui.element;
    });
</script>

</body>
</html>

<script>
    var roomOptions;
    layui.use(['laydate', 'form'], function () {
        var laydate = layui.laydate;
        var nowDate = '${nowDate}';
        laydate.render({
            elem: '#date',
            min: 0,
            max: 7,
            showBottom: false,
            lang: 'en',
            value: nowDate
        });

        var form = layui.form;
        form.render();
        form.on('select(siteId)', function (data) {
            $("#meetingRoomId")[0].innerHTML = roomOptions[data.elem.selectedIndex];
            form.render();
        });
    });

    $(function () {
        $.ajax({
            url: '${pageContext.request.contextPath}/site/findAllWithMR.do',
            type: 'post',
            dataType: 'json',
            success: function (data) {
                var $siteId = $('#siteId');
                var $meetingRoomId = $('#meetingRoomId');
                var siteOptions = "";
                if (data.length > 0) {
                    roomOptions = new Array(data.length);
                    for (var i = 0; i < data.length; i++) {
                        var $site = data[i]['site'];
                        siteOptions += "<option value="
                            + $site.id +
                            ">"
                            + $site.name +
                            "</option>";
                        var $meetingRooms = data[i]['meetingRooms'];
                        var meetingOptions = "";
                        for (var j = 0; j < $meetingRooms.length; j++) {
                            var $meetingRoom = $meetingRooms[j];
                            meetingOptions += "<option value="
                                + $meetingRoom.id +
                                ">"
                                + $meetingRoom.name +
                                "</option>";
                        }
                        roomOptions[i] = meetingOptions;
                    }
                    $siteId[0].innerHTML = siteOptions;
                    $meetingRoomId[0].innerHTML = roomOptions[0];
                    layui.use('form', function () {
                        var form = layui.form;
                        form.render();
                    });
                }
            }
        })
    });

</script>
