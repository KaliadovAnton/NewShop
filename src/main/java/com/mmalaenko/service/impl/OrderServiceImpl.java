package com.mmalaenko.service.impl;

import com.mmalaenko.model.Order;
import com.mmalaenko.repository.OrderRepository;
import com.mmalaenko.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
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