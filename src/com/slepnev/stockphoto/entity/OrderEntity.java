package com.slepnev.stockphoto.entity;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderEntity {

    private static final String ORDER_ID_COLUMN = "id";
    private static final String ORDER_STATUS_COLUMN = "status";
    private static final String ORDER_NUMBER_OF_PHOTO_COLUMN = "number_of_photo";
    private static final String ORDER_PHOTO_ID_COLUMN = "photo_id";
    private static final String ORDER_COST_COLUMN = "cost";
    private static final String ORDER_USERS_ID_COLUMN = "users_id";

    private Integer id;
    private String status;
    private Integer numberOfPhoto;
    private Long photoId;
    private BigDecimal cost;
    private Integer usersId;

    public OrderEntity(Integer id, String status, Integer numberOfPhoto, Long photoId, BigDecimal cost, Integer usersId) {
        this.id = id;
        this.status = status;
        this.numberOfPhoto = numberOfPhoto;
        this.photoId = photoId;
        this.cost = cost;
        this.usersId = usersId;
    }

    public static OrderEntity build(ResultSet resultSet) throws SQLException {
        return new OrderEntity(
                resultSet.getInt(ORDER_ID_COLUMN),
                resultSet.getString(ORDER_STATUS_COLUMN),
                resultSet.getInt(ORDER_NUMBER_OF_PHOTO_COLUMN),
                resultSet.getLong(ORDER_PHOTO_ID_COLUMN),
                resultSet.getBigDecimal(ORDER_COST_COLUMN),
                resultSet.getInt(ORDER_USERS_ID_COLUMN)
        );
    }

    public static String getOrderIdColumn() {
        return ORDER_ID_COLUMN;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getNumberOfPhoto() {
        return numberOfPhoto;
    }

    public void setNumberOfPhoto(Integer numberOfPhoto) {
        this.numberOfPhoto = numberOfPhoto;
    }

    public Long getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Long photoId) {
        this.photoId = photoId;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Integer getUsersId() {
        return usersId;
    }

    public void setUsersId(Integer usersId) {
        this.usersId = usersId;
    }

    @Override
    public String toString() {
        return "UserOrderEntity{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", numberOfPhoto=" + numberOfPhoto +
                ", photoId=" + photoId +
                ", cost=" + cost +
                ", usersId=" + usersId +
                '}';
    }
}
