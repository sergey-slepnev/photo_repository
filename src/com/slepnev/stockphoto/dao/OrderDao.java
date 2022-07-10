package com.slepnev.stockphoto.dao;

import com.slepnev.stockphoto.entity.OrderEntity;
import com.slepnev.stockphoto.exception.DaoException;
import com.slepnev.stockphoto.util.ConnectionManager;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDao implements Dao<Integer, OrderEntity> {

    private static final OrderDao INSTANCE = new OrderDao();
    private static final String SAVE_SQL = """
            INSERT INTO orders (status, number_of_photo, photo_id, cost, users_id)
            VALUES (?,?,?,?,?);
            """;
    private static final String FIND_ALL_SQL = """
            SELECT id, 
            status, 
            number_of_photo, 
            photo_id,
            cost, 
            users_id
            FROM orders
            """;
    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE id = ?
            """;
    private static final String UPDATE_SQL = """
            UPDATE orders
            SET status = ?,
            number_of_photo = ?,
            photo_id = ?,
            cost = ?,
            users_id = ?
            WHERE id = ?
            """;
    private static final String DELETE_SQL = """
            DELETE FROM orders
            WHERE id = ?;
            """;

    private OrderDao() {
    }

    public static OrderDao getInstance() {
        return INSTANCE;
    }

    @Override
    public OrderEntity save(OrderEntity order) {
        try (var connection = ConnectionManager.get();) {
            var preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS);
            setValues(order, preparedStatement);
            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                order.setId(generatedKeys.getInt(OrderEntity.getOrderIdColumn()));
            }
            return order;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<OrderEntity> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            var resultSet = preparedStatement.executeQuery();
            List<OrderEntity> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(OrderEntity.build(resultSet));
            }
            return result;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<OrderEntity> findById(Integer id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setInt(1, id);
            var resultSet = preparedStatement.executeQuery();
            OrderEntity order = null;
            if (resultSet.next()) {
                order = OrderEntity.build(resultSet);
            }
            return Optional.ofNullable(order);

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(OrderEntity order) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            setValues(order, preparedStatement);
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

    private void setValues(OrderEntity order, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, order.getStatus());
        preparedStatement.setInt(2, order.getNumberOfPhoto());
        preparedStatement.setLong(3, order.getPhotoId());
        preparedStatement.setBigDecimal(4, order.getCost());
        preparedStatement.setInt(5, order.getUsersId());
        preparedStatement.executeUpdate();
    }
}