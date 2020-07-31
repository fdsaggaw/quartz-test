
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>

    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>登录页面</title>
    <link href="../../css/bootstrap.min.css" rel="stylesheet">
    <script src="../../js/jquery-2.1.0.min.js"></script>
    <script src="../../js/bootstrap.min.js"></script>
</head>
<body>
<div class="container" style="width: 400px;">
    <form id="loginForm" action="/client/login">
        <div class="form-group">
            <label for="username">用户名：</label>
            <input type="text" class="form-control" id="username"  name="username" placeholder="请输入用户名" />
        </div>

        <div class="form-group">
            <label for="password">密码：</label>
            <input type="password" class="form-control" id="password" name="password"  placeholder="请输入密码" />

        </div>

        <div class="form-group" style="text-align: center">
            <input class="btn btn-default" type="submit" id="button" value="登录">
            <input class="btn btn-default" type="button" onclick="location.href='${pageContext.request.contextPath}/user/registerPage'" value="注册">
        </div>
    </form>
    <div id="errorMsg" style="color:red;text-align: center">
        <span>${msg}</span>
    </div>

</div>
</body>
</html>
