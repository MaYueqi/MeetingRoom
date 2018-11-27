<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Register</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css" media="all">
</head>
<body class="layui-container">
<%@include file="header.jsp"%>
<div class="layui-row">
    <div class="layui-col-md6 layui-col-lg-offset3">
        <form class="layui-form layui-form-pane" action="${pageContext.request.contextPath}/user/register.do" method="post">
            <table class="layui-table">
                <tr>
                    <td colspan="2" align="center">
                        Register
                    </td>
                </tr>
                <tr>
                    <td align="center" width="35%">
                        Username:
                    </td>
                    <td><input type="text" name="username" class="layui-input" required/></td>
                </tr>
                <tr>
                    <td align="center">Password:</td>
                    <td><input type="password" name="password" class="layui-input" required/></td>
                </tr>
                <tr>
                    <td align="center">Site:</td>
                    <td >
                        <select id="siteId"   name="siteId" required>
                            <option>请选择</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Sign up" class="layui-btn"> &emsp;
                        <a href="${pageContext.request.contextPath}/toLogin.do">Login</a>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
</body>
</html>
<script src="${pageContext.request.contextPath}/static/js/jquery-1.11.3.min.js"></script>
<script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
<script type="text/javascript">
    renderForm();
    function renderForm(){
        layui.use('form', function(){
            var form = layui.form;
            form.render();
        });
    }
    $(function () {
        $.ajax({
            url: '${pageContext.request.contextPath}/site/findAll.do',
            type: 'post',
            dataType: 'json',
            success: function (data) {
                var options = "";
                for (var i = 0; i < data.length; i++) {
                    options += "<option value=" + data[i]['id'] + ">" + data[i]['name'] + "</option>";
                }
                $('#siteId')[0].innerHTML = options;
                renderForm();
            }
        });
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