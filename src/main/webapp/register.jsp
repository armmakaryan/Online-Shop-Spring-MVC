<%@ page import="com.smartCode.springMvc.util.constants.Parameter" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 6/20/2023
  Time: 1:05 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Register Page</title>
</head>
<body>


<h3 class="message"><%= request.getAttribute(Parameter.MESSAGE_ATTRIBUTE) != null ?
        request.getAttribute(Parameter.MESSAGE_ATTRIBUTE) : "" %>
</h3>

<div class="container">
    <div class="registration form">
        <header>Signup</header>
        <form method="post" action="/register">
            <div class="register_form">
                <input type="text" placeholder="Enter your name" name="name">
                <input type="text" placeholder="Enter your lastname" name="lastname">
                <input type="text" placeholder="Enter your balance" name="balance">
                <input type="email" placeholder="Enter your email" name="email">
                <input type="password" placeholder="Enter your password" name="password">
                <input type="text" placeholder="Enter your age" name="age">
            </div>
            <input type="submit" class="button" value="register">
        </form>
        <div class="signup">
            <span class="signup">Already have an account?
             <a href="indx.jsp">Login</a>
            </span>
        </div>
    </div>
</div>
</form>
</body>
</html>