package demoregistermanagement.demoregistermanagement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "AddOrUpdateServlet", value = "/AddOrUpdateServlet")
public class AddOrUpdateServlet extends HttpServlet  {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Boolean login = (Boolean) session.getAttribute("login");

        if (login != null && login) {
            if(request.getParameter("id") != ""){
                System.out.println("update processing...");
                doPut(request, response);
            }else{
                System.out.println("insert processing...");
                insertMember(request, response);
                request.getRequestDispatcher("list.jsp").forward(request, response);
            }
        }else {
            response.sendRedirect("index.jsp");
        }
    }
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        Member updateM = new Member();
        updateM.setId(id);
        updateM.setName(request.getParameter("name"));
        updateM.setSurname(request.getParameter("surname"));
        updateM.setAge(Integer.parseInt(request.getParameter("age")));
        updateM.setPhone(request.getParameter("phone"));
        updateM.setStatus(Integer.parseInt(request.getParameter("status")));

        DbClass dbConnection = new DbClass();
        try {
            dbConnection.connect();
            PreparedStatement preStatement = DbClass.CONNECTION.prepareStatement(IQuery.updateMemberQuery(updateM.getId()));
            preStatement.setString(1, updateM.getName());
            preStatement.setString(2, updateM.getSurname());
            preStatement.setInt(3, updateM.getAge());
            preStatement.setString(4, updateM.getPhone());
            preStatement.setInt(5, updateM.getStatus());
            preStatement.executeUpdate();
            System.out.println("Member updated successfully!");
            preStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            dbConnection.disconnect();
            request.getRequestDispatcher("list.jsp").forward(request, response);
        }

    }

    private void insertMember(HttpServletRequest request, HttpServletResponse response) {
        Member newMember = new Member();
        newMember.setName(request.getParameter("name"));
        newMember.setSurname(request.getParameter("surname"));
        if (request.getParameter("age") != null)
            newMember.setAge(Integer.parseInt(request.getParameter("age")));

        newMember.setPhone(request.getParameter("phone"));
        if (request.getParameter("status") != null)
            newMember.setStatus(Integer.parseInt(request.getParameter("status")));

        Member member = new Member(newMember.getId(), newMember.getName(), newMember.getSurname(), newMember.getAge(), newMember.getPhone(), newMember.getStatus());
        DbClass dbConnection = new DbClass();
        try {
            dbConnection.connect();
            PreparedStatement preparedStatement = DbClass.CONNECTION.prepareStatement(IQuery.insertMemberQuery());
            preparedStatement.setString(1, member.getName());
            preparedStatement.setString(2, member.getSurname());
            preparedStatement.setInt(3, member.getAge());
            preparedStatement.setString(4, member.getPhone());
            preparedStatement.setInt(5, member.getStatus());

            int row = preparedStatement.executeUpdate();
            if (row > 0) {
                System.out.println("New member added successfully!");
                response.setContentType("text/html");
                preparedStatement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dbConnection.disconnect();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Boolean login = (Boolean) session.getAttribute("login");
        if (login != null && login) {
            String idString = request.getParameter("id");
            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            String ageString = request.getParameter("age");
            String phone = request.getParameter("phone");
            String statusString = request.getParameter("status");

            int id = Integer.parseInt(idString);
            int age = Integer.parseInt(ageString);
            int status = Integer.parseInt(statusString);

            Member m = new Member(id, name, surname, age, phone, status);

            request.setAttribute("m", m);
            RequestDispatcher dispatcher = request.getRequestDispatcher("add.jsp");
            dispatcher.forward(request, response);
        }else{
            response.sendRedirect("index.jsp");
        }
    }
}
