package com.slepnev.stockphoto.dao;

import com.slepnev.stockphoto.entity.AdminEntity;
import com.slepnev.stockphoto.exception.DaoException;
import com.slepnev.stockphoto.util.ConnectionManager;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class AdminDao implements Dao<Integer, AdminEntity> {

    private static final AdminDao INSTANCE = new AdminDao();
    private static final String SAVE_SQL = """
            INSERT INTO admin (username, email, password, photographer_id, users_id)
            VALUES (?,?,?,?,?);
            """;
    private static final String FIND_ALL_SQL = """
            SELECT id,
            username,
            email,
            password,
            photographer_id,
            users_id
            FROM admin
            """;
    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE id = ?
            """;
    private static final String UPDATE_SQL = """
            UPDATE admin
            SET username = ?,
            email = ?,
            password = ?,
            photographer_id = ?,
            users_id = ?
            WHERE id = ?
            """;
    private static final String DELETE_SQL = """
            DELETE FROM admin
            WHERE id = ?;
            """;

    private AdminDao() {
    }

    public static AdminDao getInstance() {
        return INSTANCE;
    }

    @Override
    public AdminEntity save(AdminEntity admin) {
        try (var connection = ConnectionManager.get();) {
            var preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS);
            setValues(admin, preparedStatement);
            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                admin.setId(generatedKeys.getInt(AdminEntity.getAdminIdColumn()));
            }
            return admin;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<AdminEntity> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            var resultSet = preparedStatement.executeQuery();
            List<AdminEntity> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(AdminEntity.build(resultSet));
            }
            return result;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<AdminEntity> findById(Integer id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setInt(1, id);
            var resultSet = preparedStatement.executeQuery();
            AdminEntity admin = null;
            if (resultSet.next()) {
                admin = AdminEntity.build(resultSet);
            }
            return Optional.ofNullable(admin);

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(AdminEntity admin) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            setValues(admin, preparedStatement);
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
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private void setValues(AdminEntity admin, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, admin.getUsername());
        preparedStatement.setString(2, admin.getEmail());
        preparedStatement.setString(3, admin.getPassword());
        preparedStatement.setObject(4, admin.getPhotographerId());
        preparedStatement.setObject(5, admin.getUsersId());
    }
}