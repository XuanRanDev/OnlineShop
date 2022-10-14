package dev.lyt.onlineshop.servlet;

import dev.lyt.onlineshop.dao.UserDao;
import dev.lyt.onlineshop.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegServlet", urlPatterns = "/reg")
public class RegServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        try {
            //loginId用户来判断
            String loginId = user.setLoginId(request.getParameter("loginId"));
            //调取网页输入的值并进行修改
            user.setPassword(request.getParameter("password"));
            user.setUserName(request.getParameter("userName"));
            user.setMobile(request.getParameter("mobile"));
            user.setU_flag("普通用户");        //默认注册为普通用户
            String email = user.setEmail(request.getParameter("email"));
            //判断loginId和email是否存在
            boolean flag = new UserDao().check(loginId);
            boolean flag1 = new UserDao().check1(email);
            if (flag) {
                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().write("<script language='javascript'>alert('该账号已被注册');window.location.href='register';</script>");
            } else if (flag1) {
                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().write("<script language='javascript'>alert('该邮箱已被注册');window.location.href='register';</script>");
            } else {
                //调用注册方法
                new UserDao().add(user);
                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().write("<script language='javascript'>alert('注册成功');window.location.href='login';</script>");
            }
        } catch (Exception er) {
            er.printStackTrace();
        }
    }
}
