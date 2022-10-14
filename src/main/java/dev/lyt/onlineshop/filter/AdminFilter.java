package dev.lyt.onlineshop.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class AdminFilter implements Filter {

    public static final String[] adminAuthLink = {"admin.jsp", "addClothes.jsp", "/admin/*", "addUser", "addAdmin", "clothes", "addClothes", "orders", "adminUser"};

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //将ServletRequest类型转换为HttpServletRequest
        HttpServletRequest request = (HttpServletRequest) req;
        //将ServletResponse类型转换为HttpServletResponse
        HttpServletResponse response = (HttpServletResponse) resp;

        String url = ((HttpServletRequest) req).getRequestURL().toString();

        for (String u : adminAuthLink) {
            if (url.contains(u) || url.matches(u)) {
                //获取浏览器的session值
                Object o = request.getSession(true).getAttribute("loginId");
                //如果获取的session值为空则跳转登录界面
                if (o == null) {
                    response.sendRedirect("adminLogin");
                    return;
                }
            }
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
