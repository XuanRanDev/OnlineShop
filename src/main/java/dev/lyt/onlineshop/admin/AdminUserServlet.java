package dev.lyt.onlineshop.admin;

import dev.lyt.onlineshop.model.User;
import dev.lyt.onlineshop.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminUserServlet", urlPatterns = "/filterAdmin/adminUser")
public class AdminUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao dao = new UserDao();
        List<User> list = dao.findAll();

        request.setAttribute("list", list);
        System.out.println(list);
        request.getRequestDispatcher("/adminUser.jsp").forward(request, response);
    }
}
