package com.slepnev.stockphoto.mapper;

import com.slepnev.stockphoto.dao.PhotographerDao;
import com.slepnev.stockphoto.dto.UploadPhotoDTO;
import com.slepnev.stockphoto.entity.PhotoEntity;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class UploadPhotoMapper implements Mapper<UploadPhotoDTO, PhotoEntity> {

    private static final UploadPhotoMapper INSTANCE = new UploadPhotoMapper();
    private static final String IMAGE_FOLDER = "photographers/";

    private final PhotographerDao photographerDao = PhotographerDao.getInstance();

    @Override
    public PhotoEntity mapFrom(UploadPhotoDTO object) {
       return PhotoEntity.builder()
                .photoTheme(object.photoTheme())
                .photoFormat(object.photoFormat())
                .resolution(object.resolution())
                .photographer(photographerDao.findById(object.photographerId()).orElse(null))
                .size(object.size())
                .isFree(object.isFree())
                .cost(object.cost())
                .createdAt(object.createdAt())
                .link(IMAGE_FOLDER + object.photo().getSubmittedFileName()).
                build();
    }

    public static UploadPhotoMapper getInstance() {
        return INSTANCE;
    }
}
