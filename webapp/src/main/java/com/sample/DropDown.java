package com.sample;

import com.sample.model.ItemType;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(
        name = "DropDown",
        urlPatterns = "/dropdown"
)
public class DropDown extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String itemType = req.getParameter("Type");

        ListService listService = new ListService();
        ItemType l = ItemType.valueOf(itemType);

        List itemTypes = listService.getAvailableTypes(l);

        req.setAttribute("types", itemTypes);
        RequestDispatcher view = req.getRequestDispatcher("result.jsp");
        view.forward(req, resp);

    }
}
