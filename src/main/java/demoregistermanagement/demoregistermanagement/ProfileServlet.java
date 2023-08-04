package demoregistermanagement.demoregistermanagement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Boolean login = (Boolean) session.getAttribute("login");
        if (login != null && login) {
            String idString = request.getParameter("id");
            int id = Integer.parseInt(idString);

            DbClass dbConnection = new DbClass();
            try {
                dbConnection.connect();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try(ResultSet rs = DbClass.CONNECTION.createStatement().executeQuery(IQuery.getMemberById(id))){
                if(rs != null){
                    Member member = new Member();
                    while(rs.next()){
                        member.setId(rs.getInt("id"));
                        member.setName(rs.getString("name"));
                        member.setSurname(rs.getString("surname"));
                        member.setAge(rs.getInt("age"));
                        member.setPhone(rs.getString("phone"));
                        member.setStatus(rs.getInt("status"));
                    }
                    rs.close();
                    dbConnection.disconnect();
                    HttpSession session2 = request.getSession();
                    session2.setAttribute("member" , member);
                    request.getRequestDispatcher("profile.jsp").forward(request, response);
                }
            }catch (SQLException e){
                e.printStackTrace();
        }
    }else{
            response.sendRedirect("index.jsp");
        }
}
}
