package com.slepnev.stockphoto.service;

import com.slepnev.stockphoto.dao.PhotoDao;
import com.slepnev.stockphoto.dto.PhotoDTO;
import com.slepnev.stockphoto.dto.UploadPhotoDTO;
import com.slepnev.stockphoto.mapper.UploadPhotoMapper;
import com.slepnev.stockphoto.util.PropertiesUtil;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;
import static java.util.stream.Collectors.toSet;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class PhotoService {

    private static final PhotoService INSTANCE = new PhotoService();

    private final UploadPhotoMapper uploadPhotoMapper = UploadPhotoMapper.getInstance();
    private final String basePath = PropertiesUtil.get("photo.base.url");
    private final PhotoDao photoDao = PhotoDao.getInstance();

    public static PhotoService getInstance() {
        return INSTANCE;
    }

    public List<PhotoDTO> findAll() {
        return photoDao.findAll().stream()
                .map(photo -> new PhotoDTO(
                        photo.getPhotoTheme(),
                        photo.getResolution(),
                        photo.getCost()
                )).toList();
    }

    public List<PhotoDTO> findAllByPhotographer(Integer photographerId) {
        return photoDao.findAllByPhotographer(photographerId).stream()
                .map(photo -> new PhotoDTO(
                        photo.getPhotoTheme(),
                        photo.getResolution(),
                        photo.getCost()
                )).toList();
    }

    public Set<String> themes() {
        return findAll().stream().map(PhotoDTO::photoTheme).collect(toSet());
    }

    public List<PhotoDTO> findAllByTheme(String theme) {
        return photoDao.findByTheme(theme).stream()
                .map(photo -> new PhotoDTO(
                        photo.getPhotoTheme(),
                        photo.getResolution(),
                        photo.getCost()
                )).toList();
    }

    @SneakyThrows
    public void create(UploadPhotoDTO uploadPhotoDTO) {
        var photoEntity = uploadPhotoMapper.mapFrom(uploadPhotoDTO);
        upload(photoEntity.getLink(), uploadPhotoDTO.photo().getInputStream());
        photoDao.save(photoEntity);
    }

    @SneakyThrows
    private void upload(String photoPath, InputStream imageContent) {
        var photoFullPath = Path.of(basePath, photoPath);
        try(imageContent) {
            Files.createDirectories(photoFullPath.getParent());
            Files.write(photoFullPath, imageContent.readAllBytes(), CREATE, TRUNCATE_EXISTING);
        }
    }
}