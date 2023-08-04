package demoregistermanagement.demoregistermanagement;

import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/ChangePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("id") != null){
            int id = Integer.parseInt(request.getParameter("id"));
            request.setAttribute("id", id);
            request.getRequestDispatcher("changePassword.jsp").forward(request, response);
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Boolean login = (Boolean) session.getAttribute("login");
        if (login != null && login) {
            String idString = request.getParameter("id");
            int id = Integer.parseInt(idString);
            String oldPassword = request.getParameter("oldPassword");
            String newPassword = request.getParameter("newPassword");
            String hashedNewPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());

            DbClass dbConnection = new DbClass();
            try {
                    dbConnection.connect();
                    ResultSet rs = DbClass.CONNECTION.createStatement().executeQuery(IQuery.getAdminById(id));
                    Admin admin = new Admin();
                    if (rs != null) {
                        while (rs.next()) {
                            admin.setUsername(rs.getString("username"));
                            admin.setPassword(rs.getString("password"));
                        }
                    }
                    if (verifiedPassword(oldPassword, admin.getPassword())) {
                        PreparedStatement preparedStatement = DbClass.CONNECTION.prepareStatement(IQuery.updateAdminPassword());
                        preparedStatement.setString(1, hashedNewPassword);
                        preparedStatement.setInt(2, id);
                        int row = preparedStatement.executeUpdate();
                        if (row > 0) {
                            System.out.println("Password changed successfully");
                        }
                        dbConnection.disconnect();
                        response.sendRedirect("admin.jsp?id=" + id);

                    } else {
                        System.out.println("OLD PASSWORD INVALID!");
                    }
                }catch (SQLException e){
                e.printStackTrace();
                }finally {
                dbConnection.disconnect();
                }
            }
            else {
                    response.sendRedirect("index.jsp");
                }
        }
    public static boolean verifiedPassword(String insertedPassword, String hashedPassword) {
        return BCrypt.checkpw(insertedPassword, hashedPassword);
    }
}
