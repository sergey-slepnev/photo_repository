package com.slepnev.stockphoto.validator;

import com.slepnev.stockphoto.dto.UploadPhotoDTO;
import com.slepnev.stockphoto.enums.PhotoFormatEnum;
import com.slepnev.stockphoto.util.PropertiesUtil;
import lombok.SneakyThrows;
import org.apache.tika.Tika;

import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;

public class UploadPhotoValidator implements Validator<UploadPhotoDTO> {


    private static final UploadPhotoValidator INSTANCE = new UploadPhotoValidator();
    private static final String SLASH = "/";
    private static final Integer MAX_SIZE = 5_242_880;//max size of photo = 5 MB

    private final String basePath = PropertiesUtil.get("photo.base.url");

    private UploadPhotoValidator() {
    }

    @Override
    public ValidationResult isValid(UploadPhotoDTO object) {
        var validationResult = new ValidationResult();
        return validationResult;
    }

    public boolean isPhotoValid(String path) {
        if (!determinePhotoFormat(path).isEmpty()) {
            if (determinePhotoSize(path) <= MAX_SIZE) {
                return true;
            }
        }
        try {
            var tika = new Tika();
            var photoType = formatPhotoType(tika.detect(path));
            for (PhotoFormatEnum format : PhotoFormatEnum.values()) {
                if (format.name().equalsIgnoreCase(photoType)) {
                    return true;//вывести сообщение о том, что фото не соответствует + размер
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return false;
    }

    public String determinePhotoFormat(String path) {
        String format = "Undetermined";
        try {
            var contentType = formatPhotoType(Files.probeContentType(Path.of(path)));
            for (PhotoFormatEnum formatEnum : PhotoFormatEnum.values()) {
                if (formatEnum.name().equalsIgnoreCase(contentType)) {
                    format = formatEnum.name();
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return format;
    }

    public Integer determinePhotoSize(String path) {
        int size = 0;
        try (var inputStream = new FileInputStream(basePath + path)) {
            size = inputStream.available();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return size;
    }

    public static UploadPhotoValidator getInstance() {
        return INSTANCE;
    }

    private String formatPhotoType(String photoType) {
        return photoType.substring(photoType.indexOf(SLASH) + 1);
    }

    @SneakyThrows
    public String determineResolution(InputStream path) {
        String resolution = "Undetermined";
        try {
            var read = ImageIO.read(path);
//            var read = ImageIO.read(Files.newInputStream(Path.of(basePath, path)));
            var width = read.getWidth();
            var height = read.getHeight();
            resolution = width + "x" + height;
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return resolution;
    }

    public Double getPhotoSizeInMB(String path) {
        var sizeInBytes = determinePhotoSize(path);
        var unroundedResultInMB = sizeInBytes / 1024.0 / 1024.0;
        var roundedResultInMB = BigDecimal.valueOf(unroundedResultInMB).setScale(2, RoundingMode.HALF_UP);
        return roundedResultInMB.doubleValue();
    }

}
