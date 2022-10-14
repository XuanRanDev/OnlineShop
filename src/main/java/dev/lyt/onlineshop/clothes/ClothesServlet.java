package dev.lyt.onlineshop.clothes;

import dev.lyt.onlineshop.dao.ClothesDao;
import dev.lyt.onlineshop.model.Clothes;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/clothes")
public class ClothesServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ClothesDao dao = new ClothesDao();
        List<Clothes> clothesList = dao.findAll();

        request.setAttribute("clothesList", clothesList);
        request.getRequestDispatcher("Clothes.jsp").forward(request, response);
    }
}
