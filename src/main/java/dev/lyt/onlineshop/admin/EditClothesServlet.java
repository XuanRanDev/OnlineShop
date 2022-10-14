package dev.lyt.onlineshop.admin;


import dev.lyt.onlineshop.model.Clothes;
import dev.lyt.onlineshop.dao.ClothesDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/editClothes")
public class EditClothesServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Integer id = Integer.parseInt(request.getParameter("id"));
            Clothes clothes = new ClothesDao().searchById(id);
            request.setAttribute("food", clothes);
        } catch (Exception er) {
            er.printStackTrace();
        }

        request.getRequestDispatcher("editClothes.jsp").forward(request, response);
    }
}
