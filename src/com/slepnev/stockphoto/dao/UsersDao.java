package com.slepnev.stockphoto.dao;

import com.slepnev.stockphoto.entity.UsersEntity;
import com.slepnev.stockphoto.exception.DaoException;
import com.slepnev.stockphoto.util.ConnectionManager;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersDao implements Dao<Integer, UsersEntity> {

    private static final UsersDao INSTANCE = new UsersDao();

    private UsersDao() {
    }

    private static final String SAVE_SQL = """
            INSERT INTO users (username, email, password)
            VALUES (?,?,?);
            """;

    private static final String FIND_ALL_SQL = """
            SELECT id,
            username,
            email,
            password
            FROM users
            """;
    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE id = ?
            """;
    private static final String UPDATE_SQL = """
            UPDATE users
            SET username = ?,
            email = ?,
            password = ?
            WHERE id = ?
            """;
    private static final String DELETE_SQL = """
            DELETE FROM users
            WHERE id = ?;
            """;

    public static UsersDao getInstance() {
        return INSTANCE;
    }

    @Override
    public UsersEntity save(UsersEntity user) {
        try (var connection = ConnectionManager.get();) {
            var preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS);
            setValues(user, preparedStatement);
            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getInt(UsersEntity.getUserIdColumn()));
            }
            return user;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<UsersEntity> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            var resultSet = preparedStatement.executeQuery();
            List<UsersEntity> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(UsersEntity.build(resultSet));
            }
            return result;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<UsersEntity> findById(Integer id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setInt(1, id);
            var resultSet = preparedStatement.executeQuery();
            UsersEntity user = null;
            if (resultSet.next()) {
                user = UsersEntity.build(resultSet);
            }
            return Optional.ofNullable(user);

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(UsersEntity user) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            setValues(user, preparedStatement);
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

    private void setValues(UsersEntity user, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getEmail());
        preparedStatement.setString(3, user.getPassword());
    }
}