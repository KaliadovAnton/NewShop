package com.mmalaenko.repository;

public interface OrderGoodRepository {
    void save(int orderID, int productID);
}
