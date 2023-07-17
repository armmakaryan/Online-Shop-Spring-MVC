<%@ page import="java.util.List" %>
<%@ page import="com.smartCode.springMvc.util.constants.Parameter" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 6/20/2023
  Time: 5:05 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h3 class="message"><%= request.getAttribute(Parameter.MESSAGE_ATTRIBUTE) != null ?
        request.getAttribute(Parameter.MESSAGE_ATTRIBUTE) : "" %>
</h3>

<div class="container">
    <div class="form_div">
        <header>Change Password</header>
        <form class="form" method="post" action="/change">
            <input type="password" placeholder="Enter new password" name="newPassword">
            <input type="password" placeholder="Repeat password" name="repeatPassword">
            <input type="submit" class="button" value="Change">
        </form>
    </div>
</div>
</body>
</html>