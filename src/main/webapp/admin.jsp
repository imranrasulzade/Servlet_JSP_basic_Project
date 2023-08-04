<%@ page import="demoregistermanagement.demoregistermanagement.Admin" %><%--
  Created by IntelliJ IDEA.
  User: imran
  Date: 03/08/2023
  Time: 04:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        Boolean login = (Boolean) session.getAttribute("login");
        if(login != null && login){
            Admin admin = (Admin) session.getAttribute("admin");
    %>
    <title>Admin</title>
    <style><%@include file="admin.css" %></style>
</head>
<body>
<div class="container">
    <br>
    <br>
    <a style="margin-left: 85%" href="LogoutServlet"><button>Log out</button></a>
    <h1>Admin`s profile</h1>
    <div class="admin-info">
        <div class="info-item">
            <span class="label">Name:</span>
            <span class="value"><%= admin.getName() %></span>
        </div>
        <div class="info-item">
            <span class="label">Surname:</span>
            <span class="value"><%= admin.getSurname() %></span>
        </div>
        <div class="info-item">
            <span class="label">Phone:</span>
            <span class="value"><%= admin.getPhone() %></span>
        </div>
        <div class="info-item">
            <span class="label">Username:</span>
            <span class="value"><%= admin.getUsername() %></span>
        </div>
        <div class="info-item">
            <span class="label">Password:</span>
            <span class="value"> <a href="ChangePasswordServlet?id=<%= admin.getId() %>">change password</a></span>
        </div>
    </div>
<!--
    <a href="EditAdmin.jsp?id=<%= admin.getId() %>&name=<%= admin.getName() %>&surname=<%= admin.getSurname() %>&phone=<%= admin.getPhone() %>&username=<%= admin.getUsername() %>">Edit</a>
    <br>
    <a href="DeleteAdminServlet?id=<%= admin.getId() %>">Delete account</a>
-->
    <br>
    <br>
    <br>
    <a href="list.jsp"><button>Cancel</button></a>
</div>
<%
    }else{
        response.sendRedirect("index.jsp");
    }
%>


</body>
</html>
