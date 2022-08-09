package com.slepnev.stockphoto.entity;

import com.slepnev.stockphoto.dao.PhotographerDao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhotoEntity {

    private static final PhotographerDao photographerDao = PhotographerDao.getInstance();
    private static final String PHOTO_ID_COLUMN = "id";
    private static final String PHOTO_THEME_COLUMN = "photo_theme";
    private static final String PHOTO_FORMAT_COLUMN = "photo_format";
    private static final String PHOTO_RESOLUTION_COLUMN = "resolution";
    private static final String PHOTO_PHOTOGRAPHER_ID_COLUMN = "photographer_id";
    private static final String PHOTO_SIZE_COLUMN = "size";
    private static final String PHOTO_IS_FREE_COLUMN = "is_free";
    private static final String PHOTO_COST_COLUMN = "cost";
    private static final String PHOTO_CREATED_AT_COLUMN = "created_at";
    private static final String PHOTO_LINK = "link";

    Long id;
    String photoTheme;
    String photoFormat;
    String resolution;
    PhotographerEntity photographer;
    Double size;
    Boolean isFree;
    BigDecimal cost;
    LocalDateTime createdAt;
    String link;


    public static PhotoEntity buildPhotoEntity(ResultSet resultSet) throws SQLException {
        return new PhotoEntity(
                resultSet.getLong(PHOTO_ID_COLUMN),
                resultSet.getString(PHOTO_THEME_COLUMN),
                resultSet.getString(PHOTO_FORMAT_COLUMN),
                resultSet.getString(PHOTO_RESOLUTION_COLUMN),
                photographerDao.findById(resultSet.getInt(PHOTO_PHOTOGRAPHER_ID_COLUMN),
                        resultSet.getStatement().getConnection()).orElse(null),
                resultSet.getDouble(PHOTO_SIZE_COLUMN),
                resultSet.getBoolean(PHOTO_IS_FREE_COLUMN),
                resultSet.getBigDecimal(PHOTO_COST_COLUMN),
                resultSet.getTimestamp(PHOTO_CREATED_AT_COLUMN).toLocalDateTime(),
                resultSet.getString(PHOTO_LINK));
    }

    public static String getIdColumn() {
        return PHOTO_ID_COLUMN;
    }
}