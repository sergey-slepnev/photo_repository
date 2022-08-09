package com.slepnev.stockphoto.servlet;

import com.slepnev.stockphoto.service.PhotoService;
import com.slepnev.stockphoto.util.JSPHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/photosByTheme")
public class PhotosByThemeServlet extends HttpServlet {

    private final PhotoService photoService = PhotoService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var theme = req.getParameter("theme");
        req.setAttribute("photosByTheme", photoService.findAllByTheme(theme));
        req.getRequestDispatcher(JSPHelper.getPath("photosByTheme")).forward(req, resp);
    }
}
