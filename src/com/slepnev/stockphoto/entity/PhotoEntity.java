package com.slepnev.stockphoto.entity;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class PhotoEntity {

    private static final String PHOTO_ID_COLUMN = "id";
    private static final String PHOTO_THEME_COLUMN = "photo_theme";
    private static final String PHOTO_FORMAT_COLUMN = "photo_format";
    private static final String PHOTO_RESOLUTION_COLUMN = "resolution";
    private static final String PHOTO_PHOTOGRAPHER_ID_COLUMN = "photographer_id";
    private static final String PHOTO_SIZE_COLUMN = "size";
    private static final String PHOTO_IS_FREE_COLUMN = "is_free";
    private static final String PHOTO_COST_COLUMN = "cost";
    private static final String PHOTO_CREATED_AT_COLUMN = "created_at";

    private Long id;
    private String photoTheme;
    private String photoFormat;
    private String resolution;
    private PhotographerEntity photographer;
    private Double size;
    private Boolean isFree;
    private BigDecimal cost;
    private LocalDateTime createdAt;

    public PhotoEntity(Long id, String photoTheme, String photoFormat, String resolution, PhotographerEntity photographer,
                       Double size, Boolean isFree, BigDecimal cost, LocalDateTime createdAt) {
        this.id = id;
        this.photoTheme = photoTheme;
        this.photoFormat = photoFormat;
        this.resolution = resolution;
        this.photographer = photographer;
        this.size = size;
        this.isFree = isFree;
        this.cost = cost;
        this.createdAt = createdAt;
    }

    public PhotoEntity() {
    }

    public static PhotoEntity build(ResultSet resultSet) throws SQLException {
        var photographerEntity = PhotographerEntity.build(resultSet);
        return new PhotoEntity(
                resultSet.getLong(PHOTO_ID_COLUMN),
                resultSet.getString(PHOTO_THEME_COLUMN),
                resultSet.getString(PHOTO_FORMAT_COLUMN),
                resultSet.getString(PHOTO_RESOLUTION_COLUMN),
                photographerEntity,
                resultSet.getDouble(PHOTO_SIZE_COLUMN),
                resultSet.getBoolean(PHOTO_IS_FREE_COLUMN),
                resultSet.getBigDecimal(PHOTO_COST_COLUMN),
                resultSet.getTimestamp(PHOTO_CREATED_AT_COLUMN).toLocalDateTime()
        );
    }

    public static String getIdColumn() {
        return PHOTO_ID_COLUMN;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhotoTheme() {
        return photoTheme;
    }

    public void setPhotoTheme(String photoTheme) {
        this.photoTheme = photoTheme;
    }

    public String getPhotoFormat() {
        return photoFormat;
    }

    public void setPhotoFormat(String photoFormat) {
        this.photoFormat = photoFormat;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public PhotographerEntity getPhotographer() {
        return photographer;
    }

    public void setPhotographer(PhotographerEntity photographer) {
        this.photographer = photographer;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public Boolean getFree() {
        return isFree;
    }

    public void setIsFree(Boolean free) {
        isFree = free;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "PhotoEntity{" +
                "id=" + id +
                ", photoTheme='" + photoTheme + '\'' +
                ", photoFormat='" + photoFormat + '\'' +
                ", resolution='" + resolution + '\'' +
                ", photographerId=" + photographer +
                ", size=" + size +
                ", isFree=" + isFree +
                ", price=" + cost +
                ", createdAt=" + createdAt +
                '}';
    }
}