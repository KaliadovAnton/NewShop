package com.mmalaenko.repository;

import com.mmalaenko.model.Order;

import java.util.List;

public interface OrderRepository {

//   Order getOrderByUserId(int userID);

    void saveOrder(Order order);

    List<Order> getListOrderByUser(int userID);
}
