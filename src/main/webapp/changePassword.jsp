<%--
  Created by IntelliJ IDEA.
  User: imran
  Date: 03/08/2023
  Time: 04:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Change Password</title>
  <style><%@include file="changePassword.css" %></style>
  <%

    Boolean login = (Boolean) session.getAttribute("login");
    if(login != null && login){
    int id = (int) request.getAttribute("id");
    //int id = Integer.parseInt(request.getParameter("id"));

  %>
</head>
<body>
<h1>Change Password</h1>
<br>
<div class="container">
  <form action="ChangePasswordServlet" method="POST" onsubmit="return validateForm()">
    <a style="margin-left: 85%" href="LogoutServlet">Log out</a>

    <input type="hidden" id="id" name="id" value="<%= id %>">
    <label for="oldPassword">Current Password:</label>
    <input type="password" id="oldPassword" name="oldPassword" required>

    <label for="newPassword">New Password:</label>
    <input type="password" id="newPassword" name="newPassword" required>

    <label for="confirmPassword">Confirm New Password:</label>
    <input type="password" id="confirmPassword" name="confirmPassword" required>
    <div id="error-message" style="display: none; color: red;">Passwords are not the same!</div>

    <button type="submit">Change</button>
  </form>
  <br>
  <br>
  <a href="list.jsp"><button>Go to member list</button></a>
</div>
<script>
  function validateForm() {
    var newPassword = document.getElementById("newPassword").value;
    var confirmPassword = document.getElementById("confirmPassword").value;

    if (newPassword !== confirmPassword) {
      document.getElementById("error-message").style.display = "block";
      return false;
    } else {
      document.getElementById("error-message").style.display = "none";
      return true;
    }
  }
</script>
<%
  }else{
    response.sendRedirect("index.jsp");
  }
%>
</body>
</html>
