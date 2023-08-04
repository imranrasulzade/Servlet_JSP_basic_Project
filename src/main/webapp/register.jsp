<%--
  Created by IntelliJ IDEA.
  User: imran
  Date: 01/08/2023
  Time: 19:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <style><%@include file="register.css" %></style>
</head>
<body>
<div class="container">
    <h1>Sign up</h1>
    <form action="RegisterServlet" method="post" onsubmit="return validateForm()">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required>

        <label for="surname">Surname:</label>
        <input type="text" id="surname" name="surname" required>

        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required>

        <label for="phone">Phone:</label>
        <input type="tel" id="phone" name="phone" required>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>

        <label for="retryPassword">Retry password:</label>
        <input type="password" id="retryPassword" name="retryPassword" required>

        <div id="error-message" style="display: none; color: red;">Passwords are not the same!</div>

        <button type="submit">Register</button>
    </form>
    <a href="index.jsp"><button>Sign in</button></a>
</div>

<script>
    function validateForm() {
        var password = document.getElementById("password").value;
        var retryPassword = document.getElementById("retryPassword").value;

        if (password !== retryPassword) {
            document.getElementById("error-message").style.display = "block";
            return false;
        } else {
            document.getElementById("error-message").style.display = "none";
            return true;
        }
    }
</script>
</body>
</html>
