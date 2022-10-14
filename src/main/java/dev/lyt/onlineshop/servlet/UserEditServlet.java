package dev.lyt.onlineshop.servlet;

import dev.lyt.onlineshop.dao.UserDao;
import dev.lyt.onlineshop.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserEditServlet", urlPatterns = "/userEdit")
public class UserEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UserEditServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userName = request.getParameter("userName");
        String mobile = request.getParameter("mobile");
        User person = (User) request.getSession().getAttribute("user");
        person.setUserName(userName);
        person.setMobile(mobile);
        UserDao.update(person);
        System.out.println("id: " + person.getU_id());
        System.out.println("loginId: " + person.getLoginId());
        System.out.println("email" + person.getEmail());
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().println("<script LANGUAGE='javascript'> alert('修改成功！');history.go(-1);</script>");
    }
}
