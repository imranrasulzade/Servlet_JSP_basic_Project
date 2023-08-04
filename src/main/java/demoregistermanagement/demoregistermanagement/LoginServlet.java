package demoregistermanagement.demoregistermanagement;

import org.mindrot.jbcrypt.BCrypt;

import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "loginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        String insertedUsername = request.getParameter("username");
        String insertedPassword = request.getParameter("password");

        DbClass dbConnection = new DbClass();
        try {
            dbConnection.connect();
            ResultSet rs = DbClass.CONNECTION.createStatement().executeQuery(IQuery.getAdminByUsername(insertedUsername));
            if(rs != null) {
                while (rs.next()) {
                        Admin admin = new Admin();
                        admin.setId(rs.getInt("id"));
                        admin.setName(rs.getString("name"));
                        admin.setSurname(rs.getString("surname"));
                        admin.setUsername(rs.getString("username"));
                        admin.setPhone(rs.getString("phone"));
                        admin.setPassword(rs.getNString("password"));
                        admin.setStatus(rs.getInt("status"));

                        if (admin.getUsername().equals(insertedUsername) && verifiedPassword(insertedPassword, admin.getPassword()) && admin.getStatus() == 1) {
                            HttpSession session = request.getSession();
                            session.setAttribute("login", true);
                            session.setAttribute("admin", admin);

                            Cookie nameCookie = new Cookie("name", admin.getName());
                            nameCookie.setMaxAge(3600);
                            response.addCookie(nameCookie);
                            doGet(request, response);
                        } else {
                            response.sendRedirect("index.jsp");
                        }
                }
                rs.close();
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }finally {
            dbConnection.disconnect();
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        boolean login = (session != null && session.getAttribute("login") != null);
        if (login) {
            System.out.println("Logged in successfully!");
            response.sendRedirect("list.jsp");
        } else {
            response.sendRedirect("index.jsp");
        }
    }
    public static boolean verifiedPassword(String insertedPassword, String hashedPassword) {
        return BCrypt.checkpw(insertedPassword, hashedPassword);
    }
    public void destroy() {
    }

}