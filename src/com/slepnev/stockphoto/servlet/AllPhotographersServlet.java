package com.slepnev.stockphoto.servlet;

import com.slepnev.stockphoto.service.PhotographerService;
import com.slepnev.stockphoto.util.JSPHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/photographers")
public class AllPhotographersServlet extends HttpServlet {

    private final PhotographerService photographerService = PhotographerService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setAttribute("photographers", photographerService.findAll());
        req.getRequestDispatcher(JSPHelper.getPath("photographers")).forward(req, resp);
    }
}