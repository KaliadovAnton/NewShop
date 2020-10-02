package com.mmalaenko.service;

import com.mmalaenko.model.Order;

import java.util.List;

public interface OrderService {

    void saveOrder(Order order);
    List <Order> getListOrderByUser(int userID);
}
