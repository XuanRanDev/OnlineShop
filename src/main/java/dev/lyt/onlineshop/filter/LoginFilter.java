package dev.lyt.onlineshop.filter;

import dev.lyt.onlineshop.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter({"/main", "/shopping", "/order_search", "/editUser", "/editPassword", "/selePwd", "/information"})
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //将ServletRequest类型转换为HttpServletRequest
        HttpServletRequest request = (HttpServletRequest) req;
        //将ServletResponse类型转换为HttpServletResponse
        HttpServletResponse response = (HttpServletResponse) resp;
        //获取浏览器的session值
        User o = (User) request.getSession().getAttribute("user");
        //如果获取的session值为空则跳转登录界面
        if (o == null) {
            response.sendRedirect("login");
        } else {
            chain.doFilter(req, resp);
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
