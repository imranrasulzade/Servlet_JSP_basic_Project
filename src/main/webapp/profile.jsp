<%@ page import="demoregistermanagement.demoregistermanagement.Member" %>
<%--
  Created by IntelliJ IDEA.
  User: imran
  Date: 01/08/2023
  Time: 21:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
    <style><%@include file="profile.css" %></style>
</head>

<%
    Boolean login = (Boolean) session.getAttribute("login");
    if(login != null && login){
    Member member = (Member) session.getAttribute("member");
%>
<body>
<%
    String name = null;
    Cookie[] cookies = request.getCookies();
    for(Cookie cookie : cookies) {
        if(cookie.getName().equals("name"))
            name = cookie.getValue();
    }
%>
<br>
<a href="admin.jsp"><span style="border: 2px solid #ccc; padding: 5px; border-radius: 3px;">User: <%= name %></span></a>
<a style="margin-left: 85%" href="LogoutServlet"><button>Log out</button></a>
<h1><%= member.getName() %>'s profile</h1>
<div class="profile-container">
    <div class="profile-row">
        <label>ID:</label>
        <span><%= member.getId() %></span>
    </div>
    <div class="profile-row">
        <label>Name:</label>
        <span><%= member.getName() %></span>
    </div>
    <div class="profile-row">
        <label>Surname:</label>
        <span><%= member.getSurname() %></span>
    </div>
    <div class="profile-row">
        <label>Age:</label>
        <span><%= member.getAge() %></span>
    </div>
    <div class="profile-row">
        <label>Phone:</label>
        <span><%= member.getPhone() %></span>
    </div>
    <div class="profile-row">
        <label>Status:</label>
        <span><%= member.getStatus() == 1 ? "Active" : "Inactive" %></span>
        <%
            }else{
            response.sendRedirect("index.jsp");
            }
        %>
    </div>
</div>

<br>
<a href="list.jsp"><button>Cancel</button></a>

</body>
</html>
