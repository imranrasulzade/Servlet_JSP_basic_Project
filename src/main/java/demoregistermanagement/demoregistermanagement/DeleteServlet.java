package demoregistermanagement.demoregistermanagement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        boolean login = (session != null && session.getAttribute("login") != null);
        if (login && request.getParameter("id") != null) {
            String idString = request.getParameter("id");
            int id = Integer.parseInt(idString);
            DbClass dbConnection = new DbClass();
            try {
                dbConnection.connect();
                Statement statement = DbClass.CONNECTION.createStatement();
                statement.executeUpdate(IQuery.deleteQuery(id));
                System.out.println("Member deleted successfully!");
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }finally {
                dbConnection.disconnect();
                request.getRequestDispatcher("list.jsp").forward(request, response);
            }
        } else {
            response.sendRedirect("index.jsp");
        }
    }
}
