package com.slepnev.stockphoto.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhotographerEntity {

    private static final String PHOTOGRAPHER_ID_COLUMN = "id";
    private static final String PHOTOGRAPHER_USERNAME_COLUMN = "username";
    private static final String PHOTOGRAPHER_EMAIL_COLUMN = "email";
    private static final String PHOTOGRAPHER_PASSWORD_COLUMN = "password";
    private static final String PHOTOGRAPHER_PHONE_NUMBER_COLUMN = "phone_number";
    private static final String PHOTOGRAPHER_SOCIAL_NETWORK_COLUMN = "social_network";
    private static final String PHOTOGRAPHER_STATUS_COLUMN = "status";

    private Integer id;
    private String username;
    private String email;
    private String password;
    private String phoneNumber;
    private String socialNetwork;
    private String status;

    public static PhotographerEntity build(ResultSet resultSet) throws SQLException {
        return new PhotographerEntity(
                resultSet.getInt(PHOTOGRAPHER_ID_COLUMN),
                resultSet.getString(PHOTOGRAPHER_USERNAME_COLUMN),
                resultSet.getString(PHOTOGRAPHER_EMAIL_COLUMN),
                resultSet.getString(PHOTOGRAPHER_PASSWORD_COLUMN),
                resultSet.getString(PHOTOGRAPHER_PHONE_NUMBER_COLUMN),
                resultSet.getString(PHOTOGRAPHER_SOCIAL_NETWORK_COLUMN),
                resultSet.getString(PHOTOGRAPHER_STATUS_COLUMN)
        );
    }

    public static String getIdColumn() {
        return PHOTOGRAPHER_ID_COLUMN;
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


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSocialNetwork() {
        return socialNetwork;
    }

    public void setSocialNetwork(String socialNetwork) {
        this.socialNetwork = socialNetwork;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PhotographerEntity{" +
               "id=" + id +
               ", userName='" + username + '\'' +
               ", email='" + email + '\'' +
               ", name='" + password + '\'' +
               ", phoneNumber='" + phoneNumber + '\'' +
               ", socialNetwork='" + socialNetwork + '\'' +
               ", status=" + status +
               '}';
    }
}