<%@ page import="demoregistermanagement.demoregistermanagement.DbClass" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="demoregistermanagement.demoregistermanagement.IQuery" %><%--
  Created by IntelliJ IDEA.
  User: imran
  Date: 14/07/2023
  Time: 14:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Member List</title>
    <style><%@include file="list.css" %></style>
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
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Surname</th>
        <th>Age</th>
        <th>Phone</th>
        <th>Status</th>
        <th>Edit</th>
        <th>Delete</th>
        <th>Profile</th>
    </tr>
    </thead>
    <tbody id="table-body">
    <%
        //HttpSession session1 = request.getSession();
        Boolean login = (Boolean) session.getAttribute("login");

        if (login != null && login) {
            try{
                DbClass dbConnection = new DbClass();
                dbConnection.connect();
                ResultSet rs = DbClass.CONNECTION.createStatement().executeQuery(IQuery.getAllMembers());
                if(rs != null) {
                    while (rs.next()) {
                        %>
                            <tr>
                                <td><%= rs.getInt("id") %></td>
                                <td><%= rs.getString("name") %></td>
                                <td><%= rs.getString("surname") %></td>
                                <td><%= rs.getInt("age")%></td>
                                <td><%= rs.getString("phone") %></td>
                                <td><%= rs.getInt("status") == 1 ? "Active" : "Inactive" %></td>
                                <td>
                                    <a href="AddOrUpdateServlet?id=<%= rs.getInt("id") %>&name=<%= rs.getString("name") %>&surname=<%= rs.getString("surname") %>&age=<%= rs.getInt("age") %>&phone=<%= rs.getString("phone") %>&status=<%= rs.getInt("status") %>">EDIT</a>
                                </td>
                                <td><a href="#" onClick="confirmDelete(<%= rs.getInt("id") %>)">Delete</a></td>
                                <td><a href="ProfileServlet?id=<%= rs.getInt("id")%>">Show profile</a></td>
                            </tr>
                        <%
                    }
                    rs.close();
                }
                dbConnection.disconnect();
                }catch (Exception e){
                    e.printStackTrace();
                }
        }else{
                            response.sendRedirect("index.jsp");
                        }
    %>

    <script>
        function confirmDelete(id) {
            var isConfirmed = confirm("confirm delete");

            if (isConfirmed) {
                window.location.href = "DeleteServlet?id=" + id;
            } else {
                window.location.href = "list.jsp";
            }
        }
    </script>
    </tbody>
</table>

<a href="add.jsp"><button>Add Member</button></a>
</body>
</html>

