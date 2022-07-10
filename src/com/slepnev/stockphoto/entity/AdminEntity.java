package com.slepnev.stockphoto.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminEntity {

    private static final String ADMIN_ID_COLUMN = "id";
    private static final String ADMIN_USERNAME_COLUMN = "username";
    private static final String ADMIN_EMAIL_COLUMN = "email";
    private static final String ADMIN_PASSWORD_COLUMN = "password";
    private static final String ADMIN_PHOTOGRAPHER_ID_COLUMN = "photographer_id";
    private static final String ADMIN_USER_ID_COLUMN = "user_id";

    private Integer id;
    private String username;
    private String email;
    private String password;
    private Integer photographerId;
    private Integer usersId;

    public AdminEntity(Integer id, String username, String email,
                       String password, Integer photographerId, Integer usersId) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.photographerId = photographerId;
        this.usersId = usersId;
    }

    public AdminEntity() {
    }

    public static AdminEntity build(ResultSet resultSet) throws SQLException {
        return new AdminEntity(
                resultSet.getInt(ADMIN_ID_COLUMN),
                resultSet.getString(ADMIN_USERNAME_COLUMN),
                resultSet.getString(ADMIN_EMAIL_COLUMN),
                resultSet.getString(ADMIN_PASSWORD_COLUMN),
                resultSet.getInt(ADMIN_PHOTOGRAPHER_ID_COLUMN),
                resultSet.getInt(ADMIN_USER_ID_COLUMN)
        );
    }

    public static String getAdminIdColumn() {
        return ADMIN_ID_COLUMN;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPhotographerId() {
        return photographerId;
    }

    public void setPhotographerId(Integer photographerId) {
        this.photographerId = photographerId;
    }

    public Integer getUsersId() {
        return usersId;
    }

    public void setUsersId(Integer usersId) {
        this.usersId = usersId;
    }

    @Override
    public String toString() {
        return "Admin{" +
               "id=" + id +
               ", name='" + username + '\'' +
               ", email='" + email + '\'' +
               ", password='" + password + '\'' +
               ", photographerId=" + photographerId +
               ", usersId=" + usersId +
               '}';
    }
}