package com.mmalaenko.service.impl;

import com.mmalaenko.model.Product;
import com.mmalaenko.repository.ProductRepository;
import com.mmalaenko.service.ProductService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAll() {
        return productRepository.getAll();
    }

    @Override
    public Product getProductByName(String productName) {
        return productRepository.getProductByName(productName);
    }
}
