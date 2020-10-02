package com.mmalaenko.service;

import com.mmalaenko.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAll();
    Product getProductByName(String productName);
}
