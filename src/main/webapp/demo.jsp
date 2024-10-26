<%@ page import="demoregistermanagement.demoregistermanagement.DbClass" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %><%--
  Created by IntelliJ IDEA.
  User: imran
  Date: 7/29/2024
  Time: 8:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home page</title>
</head>
<body>

<%
    DbClass dbClass = new DbClass();
    dbClass.connect();
    Statement statement = DbClass.CONNECTION.createStatement();
    ResultSet resultSet = statement.executeQuery("select * from members.employees");
%>
<table border="solid 1px black">

    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Surname</th>
    </tr>

    <%
        int a = 5;
        while (resultSet.next()) { %>
    <tr>
        <td><%=resultSet.getInt("id")%></td>
        <td><%=resultSet.getString("name")%></td>
        <td><%=resultSet.getInt("manager_id")%></td>
    </tr>
    <%
        a++;
        }

    %>



</table>

</body>
</html>
