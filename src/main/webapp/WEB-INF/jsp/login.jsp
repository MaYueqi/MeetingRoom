<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css">
    <script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/jquery-1.11.3.min.js"></script>
</head>
<body class="layui-container">
<%@include file="header.jsp"%>
<div class="layui-row">
    <div class="layui-col-md6 layui-col-lg-offset3">
        <form class="layui-form" action="${pageContext.request.contextPath}/user/login.do" method="post">
            <table class="layui-table">
                <tr>
                    <td colspan="2" align="center">
                        User Login
                    </td>
                </tr>
                <tr>
                    <td width="35%" align="center">
                        Username:
                    </td>
                    <td><input type="text" name="username" class="layui-input" required/></td>
                </tr>
                <tr>
                    <td align="center">Password:</td>
                    <td><input type="password" name="password" class="layui-input" required/></td>
                </tr>
                <tr>
                    <td align="center">
                        <input type="checkbox" title="Auto" value="true" name="autoLogin" id="autoLogin">
                    </td>
                    <td>
                        <input type="submit" value="Login" class="layui-btn">
                        &emsp;
                        <a href="${pageContext.request.contextPath}/toRegister.do">Sign Up</a>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
</body>
</html>
<script type="text/javascript">
    layui.use('form', function () {
        var form = layui.form;
        form.render();
    });

    $(function () {
        var msg = "${msg}";
        if (msg !== '') {
            layui.use('layer', function(){
                var layer = layui.layer;
                layer.open({
                    title: 'Error',
                    content: msg
                });
            });
        }
    });
</script>