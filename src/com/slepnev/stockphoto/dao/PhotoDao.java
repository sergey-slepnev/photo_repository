package com.slepnev.stockphoto.dao;

import com.slepnev.stockphoto.entity.PhotoEntity;
import com.slepnev.stockphoto.exception.DaoException;
import com.slepnev.stockphoto.util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PhotoDao implements Dao<Long, PhotoEntity> {

    private static final PhotoDao INSTANCE = new PhotoDao();
    private static final String SAVE_SQL = """
            INSERT INTO photo(photo_theme, photo_format, resolution, photographer_id, size, is_free, cost, created_at, link)
            VALUES (?,?,?,?,?,?,?,?,?)
            """;
    private static final String FIND_ALL_SQL = """
            SELECT photo.id,
            photo_theme,
            photo_format,
            resolution,
            photographer_id,
            size,
            is_free,
            cost,
            created_at,
            link
            FROM photo
                       
            """;
    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE photo.id = ?
            """;

    private static final String UPDATE_SQL = """
            UPDATE photo
            SET photo_theme = ?,
            photo_format = ?,
            resolution = ?,
            photographer_id = ?,
            size = ?,
            is_free = ?,
            cost = ?,
            created_at = ?,
            link = ?
            WHERE id = ?
            """;
    private static final String DELETE_SQL = """
            DELETE FROM photo
            WHERE id = ?
            """;
    private static final String FIND_ALL_BY_PHOTOGRAPHER_ID_SQL = """
            SELECT id,
            photo_theme, 
            photo_format, 
            resolution, 
            photographer_id, 
            size, is_free, 
            cost, 
            created_at,
            link
            FROM photo
            WHERE photographer_id = ?;
            """;

    private static final String FIND_BY_THEME = FIND_ALL_SQL + """
            WHERE photo_theme  = ?
            """;

    private PhotoDao() {
    }

    public static PhotoDao getInstance() {
        return INSTANCE;
    }

    @Override
    public PhotoEntity save(PhotoEntity photo) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            setValues(photo, preparedStatement);
            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                photo.setId(generatedKeys.getLong(PhotoEntity.getIdColumn()));
            }
            return photo;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<PhotoEntity> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            var resultSet = preparedStatement.executeQuery();
            List<PhotoEntity> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(PhotoEntity.buildPhotoEntity(resultSet));
            }
            return result;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<PhotoEntity> findById(Long id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            var resultSet = preparedStatement.executeQuery();
            PhotoEntity photo = null;
            if (resultSet.next()) {
                photo = PhotoEntity.buildPhotoEntity(resultSet);
            }
            return Optional.ofNullable(photo);

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(PhotoEntity photo) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            setValues(photo, preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean delete(Long id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<PhotoEntity> findAllByPhotographer(Integer photographerId) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_BY_PHOTOGRAPHER_ID_SQL)) {
            preparedStatement.setInt(1, photographerId);
            var resultSet = preparedStatement.executeQuery();
            List<PhotoEntity> photos = new ArrayList<>();
            while (resultSet.next()) {
                photos.add(PhotoEntity.buildPhotoEntity(resultSet));
            }
            return photos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<PhotoEntity> findByTheme(String theme) {
        try (Connection connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_THEME)) {
            preparedStatement.setString(1, theme);
            var resultSet = preparedStatement.executeQuery();
            List<PhotoEntity> photos = new ArrayList<>();
            while (resultSet.next()) {
                photos.add(PhotoEntity.buildPhotoEntity(resultSet));
            }
            return photos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setValues(PhotoEntity photo, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, photo.getPhotoTheme());
        preparedStatement.setString(2, photo.getPhotoFormat());
        preparedStatement.setString(3, photo.getResolution());
        preparedStatement.setInt(4, photo.getPhotographer().getId());
        preparedStatement.setDouble(5, photo.getSize());
        preparedStatement.setBoolean(6, photo.getIsFree()); //method's name?
        preparedStatement.setBigDecimal(7, photo.getCost());
        preparedStatement.setTimestamp(8, Timestamp.valueOf(photo.getCreatedAt()));
        preparedStatement.setString(9, photo.getLink());
    }
}