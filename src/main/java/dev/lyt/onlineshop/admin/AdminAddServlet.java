package dev.lyt.onlineshop.admin;

import dev.lyt.onlineshop.model.User;
import dev.lyt.onlineshop.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/addAdmin")
public class AdminAddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        UserDao dao = new UserDao();
        String loginId = request.getParameter("loginId");
        User user = dao.searchByUsername(loginId);
        user.setU_flag("管理员");
        UserDao.updatePermission(loginId, "管理员");

        System.out.println(user);
        response.getWriter().println("<script LANGUAGE='javascript'> alert('添加管理员成功!!！');history.go(-1);</script>");
    }
}
