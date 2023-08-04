package demoregistermanagement.demoregistermanagement;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String phone = request.getParameter("phone");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        Admin admin = new Admin(name, surname, phone, username, hashedPassword);
        try {
            DbClass dbConnection = new DbClass();
            dbConnection.connect();
            ResultSet rs = DbClass.CONNECTION.createStatement().executeQuery(IQuery.getAdminsUsername());
            boolean shert = false;
            while (rs.next()){
                if(username.equals(rs.getString("username"))){
                    shert = false;
                    response.sendRedirect("register.jsp");
                }else{
                    shert = true;
                }
            }
            if(shert){
                PreparedStatement preparedStatement = DbClass.CONNECTION.prepareStatement(IQuery.insertAdminQuery());
                preparedStatement.setString(1, admin.getName());
                preparedStatement.setString(2, admin.getSurname());
                preparedStatement.setString(3, admin.getPhone());
                preparedStatement.setString(4, admin.getUsername());
                preparedStatement.setString(5, admin.getPassword());
                int row = preparedStatement.executeUpdate();
                if (row > 0)
                    System.out.println("Successfully new user registration!");

                preparedStatement.close();
            }else{
                System.out.println("USERNAME DUPLICATED. Retry please!");
                return;
            }
            rs.close();
            dbConnection.disconnect();
            response.sendRedirect("index.jsp");
            }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

