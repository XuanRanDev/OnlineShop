package dev.lyt.onlineshop.servlet;

import dev.lyt.onlineshop.dao.LoginDao;
import dev.lyt.onlineshop.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created By XuanRan on 2022/05/23
 * 登录请求处理类
 */
@WebServlet("/loginReq")
public class Login_DServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        User user = new User();
        user.setLoginId(request.getParameter("loginId"));
        user.setPassword(request.getParameter("password"));
        //判断是否登录成功
        User curUser = LoginDao.validatePassword(user);
        if (curUser != null) {
            LoginDao.updateLastOnlineTime(curUser);
            HttpSession session = request.getSession(true);
            //将数据传入session中
            session.setAttribute("user", curUser);
            //登录成功跳转主界面
            response.sendRedirect("./main");
        } else {
            response.getWriter().println("<script LANGUAGE='javascript'> alert('用户名或密码错误！');self.location='login'</script>");
        }
    }
}
