package com.slepnev.stockphoto.temp;

import com.slepnev.stockphoto.dao.*;
import com.slepnev.stockphoto.entity.PhotoEntity;
import com.slepnev.stockphoto.entity.PhotographerEntity;
import com.slepnev.stockphoto.enums.PhotoFormatEnum;
import com.slepnev.stockphoto.enums.PhotoThemeEnum;
import com.slepnev.stockphoto.enums.PhotographerStatusEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static com.slepnev.stockphoto.temp.RandomUtils.*;


public final class Test {

    private static final AdminDao ADMIN_DAO = AdminDao.getInstance();
    private static final PhotographerDao PHOTOGRAPHER_DAO = PhotographerDao.getInstance();
    private static final PhotoDao PHOTO_DAO = PhotoDao.getInstance();
    private static final UsersDao USERS_DAO = UsersDao.getInstance();
    private static final OrderDao ORDER_DAO = OrderDao.getInstance();

    private Test() {
    }

    public static void photographerSaveTest() {
        var photographer = new PhotographerEntity();
        photographer.setUsername(randomUsername());
        photographer.setEmail(randomEmail());
        photographer.setPassword(randomName());
        photographer.setPhoneNumber(randomPhoneNumber());
        photographer.setSocialNetwork(randomSocialNetworkLink());
        photographer.setStatus(PhotographerStatusEnum.ACTIVE.name());
        PHOTOGRAPHER_DAO.save(photographer);
    }

    public static void photographerFindAllTest() {
        var all = PHOTOGRAPHER_DAO.findAll();
        System.out.println("Find all photographers test:");
        for (PhotographerEntity entity : all) {
            System.out.println(entity);
        }
    }

    public static Optional<PhotographerEntity> photographerFindByIdTest(Integer id) {
        var maybePhotographer = PHOTOGRAPHER_DAO.findById(id);
        System.out.println("Find by id = " + id + " maybePhotographer test:");
        maybePhotographer.ifPresent(System.out::println);
        return maybePhotographer;
    }

    public static void photographerUpdateTest(Optional<PhotographerEntity> maybePhotographer) {
        maybePhotographer.ifPresent(photographer -> {
            photographer.setStatus(PhotographerStatusEnum.BANNED.name());
            PHOTOGRAPHER_DAO.update(photographer);
        });
        System.out.println(maybePhotographer);
    }

    public static void photographerDeleteTest(Integer id) {
        PHOTOGRAPHER_DAO.delete(id);
    }

    //-----------------------
    public static void photoSaveTest() {
        var photo = new PhotoEntity();
        photo.setPhotoTheme(PhotoThemeEnum.CATS.name());
        photo.setPhotoFormat(PhotoFormatEnum.JPEG.name());
        photo.setResolution(randomResolution());
//        photo.setPhotographer();
        photo.setSize(randomSize());
        photo.setIsFree(true);
        photo.setCost(BigDecimal.valueOf(150, 35));
        photo.setCreatedAt(LocalDateTime.now());
        PHOTO_DAO.save(photo);
    }

    public static Optional<PhotoEntity> photoFindByIdTest(Long id) {
        var maybePhoto = PHOTO_DAO.findById(id);
        System.out.println("Find by id = " + id + " maybePhoto test:");
        maybePhoto.ifPresent(System.out::println);
        return maybePhoto;
    }





//
//    private static void updateTest() {
//        var instance = PhotographerDao.getInstance();
//        var maybePhotographer = instance.findById(6);
//        maybePhotographer.ifPresent(photographer -> {
//            photographer.setName("Varvara Slepneva");
//            instance.update(photographer);
//        });
//    }
//
//    private static void deleteTest() {
//        var adminDao = AdminDao.getInstance();
//        var admin = new AdminEntity();
//        admin.setUsername("Kolya");
//        admin.setEmail("Kolya@mainl.com");
//        admin.setPassword("KolyaPassword");
//        admin.setPhotographerId(123);
//        admin.setUsersId(123);
//        var save = adminDao.save(admin);
//    }

//    public static void adminSaveTest() {
//        var adminEntity = new AdminEntity();
//        adminEntity.setUsername(randomUsername());
//        adminEntity.setEmail(randomEmail());
//        adminEntity.setPassword(randomPassword());
//
//    }

    }