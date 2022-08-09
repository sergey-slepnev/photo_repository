package com.slepnev.stockphoto.dao;

import com.slepnev.stockphoto.entity.PhotographerEntity;
import com.slepnev.stockphoto.exception.DaoException;
import com.slepnev.stockphoto.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PhotographerDao implements Dao<Integer, PhotographerEntity> {

    private static final PhotographerDao INSTANCE = new PhotographerDao();
    private static final String SAVE_SQL = """
            INSERT INTO photographer(username, email, password, phone_number, social_network, status)
            VALUES (?,?,?,?,?,?);
            """;
    public static final String FIND_ALL_SQL = """
            SELECT id,
                username,
                email,
                password,
                phone_number,
                social_network,
                status
                FROM photographer
            """;
    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE id = ?
            """;
    private static final String UPDATE_SQL = """
            UPDATE photographer
            SET username = ?,
                email = ?,
                password = ?,
                phone_number = ?,
                social_network = ?,
                status = ?
            WHERE id = ?
            """;
    private static final String DELETE_SQL = """
            DELETE FROM photographer
            WHERE id = ?
            """;

    private PhotographerDao() {
    }

    public static PhotographerDao getInstance() {
        return INSTANCE;
    }

    @Override
    public PhotographerEntity save(PhotographerEntity photographer) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            setValues(photographer, preparedStatement);
            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                photographer.setId(generatedKeys.getInt(PhotographerEntity.getIdColumn()));
            }
            return photographer;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<PhotographerEntity> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            var resultSet = preparedStatement.executeQuery();
            List<PhotographerEntity> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(PhotographerEntity.build(resultSet));
            }
            return result;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }


    public Optional<PhotographerEntity> findById(Integer id, Connection connection) {
        try (var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setInt(1, id);
            var resultSet = preparedStatement.executeQuery();
            PhotographerEntity photographer = null;
            if (resultSet.next()) {
                photographer = PhotographerEntity.build(resultSet);
            }
            return Optional.ofNullable(photographer);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Optional<PhotographerEntity> findById(Integer id) {
        try (var connection = ConnectionManager.get()) {
            return findById(id, connection);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(PhotographerEntity photographer) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            setValues(photographer, preparedStatement);
            preparedStatement.setInt(7, photographer.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean delete(Integer id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException exception) {
            throw new DaoException(exception);
        }
    }

    private void setValues(PhotographerEntity photographer, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, photographer.getUsername());
        preparedStatement.setString(2, photographer.getEmail());
        preparedStatement.setString(3, photographer.getPassword());
        preparedStatement.setString(4, photographer.getPhoneNumber());
        preparedStatement.setString(5, photographer.getSocialNetwork());
        preparedStatement.setString(6, photographer.getStatus());
    }
}