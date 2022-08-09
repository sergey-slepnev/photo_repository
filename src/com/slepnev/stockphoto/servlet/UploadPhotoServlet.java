package com.slepnev.stockphoto.servlet;

import com.slepnev.stockphoto.dto.UploadPhotoDTO;
import com.slepnev.stockphoto.enums.PhotoThemeEnum;
import com.slepnev.stockphoto.exception.ValidationException;
import com.slepnev.stockphoto.service.PhotoService;
import com.slepnev.stockphoto.util.JSPHelper;
import com.slepnev.stockphoto.validator.UploadPhotoValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@MultipartConfig(fileSizeThreshold = 1024 * 1024)
@WebServlet("/upload")
public class UploadPhotoServlet extends HttpServlet {

    private final UploadPhotoValidator photoValidator = UploadPhotoValidator.getInstance();
    private final PhotoService photoService = PhotoService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("themes", PhotoThemeEnum.values());
        req.getRequestDispatcher(JSPHelper.getPath("uploadPhoto")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var photo = req.getPart("photo");
        var uploadPhotoDTO = UploadPhotoDTO.builder()
                .photoTheme(req.getParameter("theme"))
                .photoFormat(photoValidator.determinePhotoFormat(photo.getSubmittedFileName()))
                .resolution(photoValidator.determineResolution(photo.getInputStream()))
                .photographerId(1)
                .size(photo.getSize() / 1024.0 / 1024.0)
                .isFree(Boolean.valueOf(req.getParameter("isFree")))
                .cost(BigDecimal.valueOf(Double.parseDouble("100")))
                .createdAt(LocalDateTime.now())
                .photo(photo)
                .build();

        try {
            photoService.create(uploadPhotoDTO);
            resp.sendRedirect("/successful");
        } catch (ValidationException exception) {
            req.setAttribute("errors", exception.getErrors());
            doGet(req, resp);
        }
    }
}
