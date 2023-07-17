<%@ page import="com.smartCode.springMvc.util.constants.Parameter" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 6/19/2023
  Time: 8:57 PM
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
    <title>Login Page</title>
</head>
<body>

<h3 class="message"><%= request.getAttribute(Parameter.MESSAGE_ATTRIBUTE) != null ?
        request.getAttribute(Parameter.MESSAGE_ATTRIBUTE) : "" %>
</h3>

<div class="container">
    <div class="login form">
        <header>Login</header>
        <form method="post" action="/login">
            <input type="email" placeholder="Enter your email" name="email">
            <input style="margin-bottom: 15px;" type="password" placeholder="Enter your password" name="password">
            <div class="checkbox_div">
                <input class="checkbox" type="checkbox" name="rememberMe">
                <span style="width: 100%">Remember me</span>
            </div>
            <input type="submit" class="button" value="login">
        </form>
        <div class="signup">
        <span class="signup">Don't have an account?
         <a href="register.jsp">Signup</a>
        </span>
        </div>
    </div>
</div>


</body>
</html>