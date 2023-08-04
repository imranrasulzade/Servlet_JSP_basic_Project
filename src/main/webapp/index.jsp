<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Login</title>

  <style><%@include file="login.css" %></style>
</head>
<body>
<h1>Login</h1>
<div class="container">
<form action="LoginServlet" method="POST">
  <label for="username">Username:</label>
  <input type="text" id="username" name="username" required>

  <label for="password">Password:</label>
  <input type="password" id="password" name="password" required>

  <button type="submit">Log in</button>

  <br>
</form>
  <a href="register.jsp"><button>Sign up</button></a>
</div>
</body>
</html>
