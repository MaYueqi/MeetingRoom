<%--
  Created by IntelliJ IDEA.
  User: NIce
  Date: 2018-04-26
  Time: 12:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Error</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css">
    <script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
    <style>
        .range {
            height: 16vw;
            line-height: 16vw;
            text-align: center;
            font-size: 10vw;
            top: 50%;
            margin-top: -8vw;
        }
    </style>
</head>
<body class="layui-bg-gray">
<div style="position: absolute; width: 100%; height: 100%;">
    <div class="layui-row" style="height: 100%; line-height: 16vh; top:50%; margin-top: -8vw">
        <div class="layui-col-md-offset1 layui-col-md2 layui-bg-cyan layui-circle range">
            <div >X</div>
        </div>
        <div class="layui-col-md2 layui-bg-blue layui-circle range">
            <div>4</div>
        </div>
        <div class="layui-col-md2 layui-bg-orange layui-circle range layui-anim layui-anim-loop">
            <div class="layui-anim layui-anim-rotate layui-anim-loop">0</div>
        </div>
        <div class="layui-col-md2 layui-bg-green layui-circle range">
            <div >4</div>
        </div>
        <div class="layui-col-md2 layui-bg-black layui-circle range">
            <div >X</div>
        </div>
    </div>
    <div align="center">
        <hr>
        <a href="${pageContext.request.contextPath}/index.do"><h1>Back Home</h1></a>
        <hr>
    </div>
</div>
</body>
</html>
