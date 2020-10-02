package com.mmalaenko.service.impl;

import com.mmalaenko.model.Order;
import com.mmalaenko.repository.OrderRepository;
import com.mmalaenko.service.OrderService;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;



    @Override
    public void saveOrder(Order order) {
    orderRepository.saveOrder(order);
    }

    @Override
    public List<Order> getListOrderByUser(int userID) {
        return orderRepository.getListOrderByUser(userID);
    }
}