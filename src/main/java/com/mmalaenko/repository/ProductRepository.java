package com.mmalaenko.repository;

import com.mmalaenko.model.Product;

import java.util.List;

public interface ProductRepository {

    List<Product> getAll();
    Product getProductByName(String productName);
}
