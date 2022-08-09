package com.slepnev.stockphoto.servlet;

import com.slepnev.stockphoto.dto.CreatePhotographerDTO;
import com.slepnev.stockphoto.enums.GenderEnum;
import com.slepnev.stockphoto.enums.RoleEnum;
import com.slepnev.stockphoto.exception.ValidationException;
import com.slepnev.stockphoto.service.PhotographerService;
import com.slepnev.stockphoto.util.JSPHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    private final PhotographerService photographerService = PhotographerService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("roles", RoleEnum.values());
        req.setAttribute("genders", GenderEnum.values());
        req.getRequestDispatcher(JSPHelper.getPath("registration"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var createPhotographerDTO = CreatePhotographerDTO.builder()
                .username(req.getParameter("username"))
                .email(req.getParameter("email"))
                .password(req.getParameter("password"))
                .phoneNumber(req.getParameter("phoneNumber"))
                .socialNetwork(req.getParameter("socialNetwork"))
                .status("UNDER_VERIFICATION")
                .build();

        try {
            photographerService.create(createPhotographerDTO);
            resp.sendRedirect("/photographer_login");
        } catch (ValidationException exception) {
            req.setAttribute("errors", exception.getErrors());
            doGet(req, resp);
        }
    }
}