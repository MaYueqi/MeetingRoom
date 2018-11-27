<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Book Record</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css">
    <script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/jquery-1.11.3.min.js"></script>
</head>
<body class="layui-container">
<%@include file="header.jsp" %>
<div class="layui-row">
    <form id="revokeForm" action="${pageContext.request.contextPath}/bookRecord/revoke.do" method="post">
        <input id="bookRecordId" type="hidden" name="id">
    </form>
    <table id="dataTable"></table>
</div>
</body>
</html>
<script type="text/javascript">
    function revoke(id) {
        layui.use('layer', function () {
            var layer = layui.layer;
            layer.open({
                title: 'warn'
                , content: 'Revoke Book?'
                , btn: ['OK']
                , yes: function (index, layero) {
                    var $ = layui.$;
                    $('#bookRecordId').val(id);
                    $('#revokeForm')[0].submit();
                }
            });
        });

    }
</script>
<script type="text/html" id="btn1">
    <input class="layui-btn layui-btn-danger layui-btn-sm" type="button" value="Revoke" onclick="revoke('{{d.id}}')">
</script>
<script type="text/javascript">
    layui.use(['table'], function () {
        var table = layui.table;
        table.render({
            lang: 'en',
            elem: '#dataTable',
            url: '${pageContext.request.contextPath}/bookRecord/findBookRecordByPage.do',
            method: 'post',
            cols: [[
                {
                    field: 'date',
                    title: 'Date',
                    align: 'center'
                },
                {
                    field: 'start_time',
                    title: 'Start Time',
                    align: 'center'
                },
                {
                    field: 'end_time',
                    title: 'End Time',
                    align: 'center'
                },
                {
                    field: 'site',
                    title: 'Site',
                    align: 'center'
                },
                {
                    field: 'meeting_room',
                    title: 'Meeting Room',
                    align: 'center'
                },
                {
                    field: 'id',
                    title: 'do',
                    templet: '#btn1',
                    align: 'center'
                }
            ]],
            page: {
                limit: 10,
                limits: [10, 20, 50],
            }
        });
    });
    $(function () {
        var msg = '${msg}';
        if (msg !== '') {
            layui.use('layer', function () {
                var layer = layui.layer;
                layer.open({
                    title: 'Msg'
                    , content: msg
                    , btn: ['OK']
                });
            });
        }
    })
</script>