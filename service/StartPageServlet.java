package com.slepnev.stockphoto.service;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/stockphoto")
public class StartPageServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
     resp.setContentType("text/html");
        var writer = resp.getWriter();
        writer.write("Start page for Stock photo");
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}