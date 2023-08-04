<%@ page import="demoregistermanagement.demoregistermanagement.Member" %><%--
  Created by IntelliJ IDEA.
  User: imran
  Date: 14/07/2023
  Time: 15:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%
        HttpSession session1 = request.getSession();
        Boolean login = (Boolean) session1.getAttribute("login");

        if (!(login != null && login)) {
            response.sendRedirect("index.jsp");
        }
    %>
    <title>Add Member</title>

    <style><%@include file="add.css" %></style>

</head>
<body>
<%
    String name = null;
    Cookie[] cookies = request.getCookies();
    for(Cookie cookie : cookies) {
        if(cookie.getName().equals("name"))
            name = cookie.getValue();
    }
%>
<a href="admin.jsp"><span style="border: 2px solid #ccc; padding: 5px; border-radius: 3px;">User: <%= name %></span></a>
<a style="margin-left: 85%" href="LogoutServlet"><button>Log out</button></a>

<br>
<br>

<h1>Add Member</h1>

<form action="AddOrUpdateServlet" method="POST">
    <%
        Member m = (Member) request.getAttribute("m");
    %>
    <label for="id">ID: <%= m == null ? "auto" : m.getId() %></label>
    <input type="hidden" id="id" name="id" value="<%= m == null ? "" : m.getId() %>">

    <label for="name">Name:</label>
    <input type="text" id="name" name="name" value="<%= m == null ? "" : m.getName() %>" required>

    <label for="surname">Surname:</label>
    <input type="text" id="surname" name="surname" value="<%= m == null ? "" : m.getSurname() %>" required>

    <label for="age">Age:</label>
    <input type="text" id="age" name="age" value="<%= m == null ? "" : m.getAge() %>" required>

    <label for="phone">Phone:</label>
    <input type="text" id="phone" name="phone" value="<%= m == null ? "" : m.getPhone() %>" required>

    <label for="status">Status:</label>
    <input type="text" id="status" name="status" value="<%= m == null ? "" : m.getStatus() %>" required>

    <button type="submit">Save</button>

</form>

</body>
</html>

