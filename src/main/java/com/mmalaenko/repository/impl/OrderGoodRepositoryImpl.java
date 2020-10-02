package com.mmalaenko.repository.impl;

import com.mmalaenko.repository.OrderGoodRepository;
import com.mmalaenko.utill.DBConnector;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Slf4j
public class OrderGoodRepositoryImpl implements OrderGoodRepository {

    public static final String SQL_INSERT_ORDER_GOOD = "INSERT INTO order_good(orderID,productID) VALUES(?,?)";

    @Override
    public void save(int orderID, int productID) {
        try(Connection connection= DBConnector.getConnection()){
            try(PreparedStatement preparedStatement=connection.prepareStatement(SQL_INSERT_ORDER_GOOD)){
                preparedStatement.setString(1, Integer.toString(orderID));
                preparedStatement.setString(2, Integer.toString(productID));
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            log.error("Error while INSERT Order_Good",e.getMessage());
        }
    }
}
