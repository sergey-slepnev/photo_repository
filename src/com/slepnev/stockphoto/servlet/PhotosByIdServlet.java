package com.slepnev.stockphoto.servlet;

import com.slepnev.stockphoto.service.PhotoService;
import com.slepnev.stockphoto.service.PhotographerService;
import com.slepnev.stockphoto.util.JSPHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/photosById")
public class PhotosByIdServlet extends HttpServlet {

    private final PhotoService photoService = PhotoService.getInstance();
    private final PhotographerService photographerService = PhotographerService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        var photographerId = Integer.valueOf(req.getParameter("photographerId"));
        req.setAttribute("photosById", photoService.findAllByPhotographer(photographerId));
        req.setAttribute("photographers", photographerService.findAll());
        req.getRequestDispatcher(JSPHelper.getPath("photosById")).forward(req, resp);
    }
}