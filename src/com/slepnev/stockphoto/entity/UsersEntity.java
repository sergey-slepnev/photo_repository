package com.slepnev.stockphoto.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersEntity {

    private static final String USER_ID_COLUMN = "id";
    private static final String USER_USERNAME_COLUMN = "username";
    private static final String USER_EMAIL_COLUMN = "email";
    private static final String USER_PASSWORD_COLUMN = "password";

    private Integer id;
    private String username;
    private String email;
    private String password;

    public UsersEntity(Integer id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public static UsersEntity build(ResultSet resultSet) throws SQLException {
        return new UsersEntity(
                resultSet.getInt(USER_ID_COLUMN),
                resultSet.getString(USER_USERNAME_COLUMN),
                resultSet.getString(USER_EMAIL_COLUMN),
                resultSet.getString(USER_PASSWORD_COLUMN)
        );
    }

    public static String getUserIdColumn() {
        return USER_ID_COLUMN;
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

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}