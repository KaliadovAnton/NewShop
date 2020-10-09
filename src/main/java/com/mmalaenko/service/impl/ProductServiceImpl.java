package com.mmalaenko.service.impl;

import com.mmalaenko.model.Product;
import com.mmalaenko.repository.ProductRepository;
import com.mmalaenko.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(@Qualifier("productRepositoryImpl") ProductRepository productRepository) {
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
