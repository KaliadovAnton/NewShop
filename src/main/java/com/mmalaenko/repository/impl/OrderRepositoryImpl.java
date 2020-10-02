package com.mmalaenko.repository.impl;

import com.mmalaenko.model.Order;
import com.mmalaenko.repository.OrderRepository;
import com.mmalaenko.utill.DBConnector;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class OrderRepositoryImpl implements OrderRepository {

    private static final String SQL_SELECT_BY_USER_ID = "SELECT*FROM orders WHERE userID=?";
    public static final String SQL_INSERT_ORDER = "INSERT INTO orders(userID,total_price) VALUES(?,?)";

//    @Override
//    public Order getOrderByUserId(int userID) {
//        Order order = null;
//        try (Connection connection = DBConnector.getConnection()) {
//            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_USER_ID)) {
//                preparedStatement.setInt(1, userID);
//                final ResultSet resultSet = preparedStatement.executeQuery();
//                order = getOrder(resultSet);
//            }
//        } catch (SQLException e) {
//            log.error("Error while select order by user id, id:[{}]", userID, e.getMessage());
//        }
//        return order;
//    }

    @Override
    public void saveOrder(Order order) {
        try (Connection connection = DBConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_ORDER, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setInt(1, order.getUserID());
                preparedStatement.setDouble(2, order.getTotalPrice());
                order.setId(preparedStatement.executeUpdate());

        } catch (SQLException e) {
            log.error("Error while INSERT Order", e.getMessage());
            throw new RuntimeException();
        }

    }

    @Override
    public List<Order> getListOrderByUser(int userID) {
        List<Order> orders= new ArrayList<>();
        try(Connection connection=DBConnector.getConnection();
        PreparedStatement preparedStatement= connection.prepareStatement(SQL_SELECT_BY_USER_ID)){
            preparedStatement.setInt(1, userID);
            ResultSet resultSet= preparedStatement.executeQuery();
            orders = getOrders(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    private Order getOrder(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            return Order.builder()
                    .id(resultSet.getInt("id"))
                    .userID(resultSet.getInt("userID"))
                    .totalPrice(resultSet.getDouble("total_price"))
                    .build();
        }
        return null;
    }

    private List<Order> getOrders(ResultSet resultSet) throws SQLException {
        List<Order> orders=new ArrayList<>();
        while (resultSet.next()) {
            orders.add(Order.builder()
                    .id(resultSet.getInt("id"))
                    .userID(resultSet.getInt("userID"))
                    .totalPrice(resultSet.getDouble("total_price"))
                    .build());
        }
        return  orders;
    }
}
