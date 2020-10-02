package com.mmalaenko.service.impl;

import com.mmalaenko.repository.OrderGoodRepository;
import com.mmalaenko.service.OrderGoodService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderGoodServiceImpl implements OrderGoodService {
    private final OrderGoodRepository orderGoodRepository;

    @Override
    public void save(int orderID, int productID) {
        orderGoodRepository.save(orderID,productID);
    }
}
