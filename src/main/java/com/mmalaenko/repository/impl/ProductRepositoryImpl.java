package com.mmalaenko.repository.impl;

import com.mmalaenko.model.Product;
import com.mmalaenko.model.User;
import com.mmalaenko.repository.ProductRepository;
import com.mmalaenko.utill.DBConnector;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class ProductRepositoryImpl implements ProductRepository {

    private static final String SQL_SELECT_ALL_PRODUCTS = "SELECT*FROM products";
    private static final String SQL_SELECT_BY_NAME="SELECT*FROM products WHERE name=?";

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        try (Connection connection = DBConnector.getConnection()) {
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_PRODUCTS);
                products = getProducts(resultSet);
            }
        } catch (SQLException e) {
            log.error("Error SELECT ALL products", e.getMessage());
        }
        return products;
    }

    @Override
    public Product getProductByName(String productName) {
        Product product=null;
        try(Connection connection = DBConnector.getConnection()) {
            try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_NAME)) {
                preparedStatement.setString(1, productName);
                final ResultSet resultSet = preparedStatement.executeQuery();
                product = getProduct(resultSet);
            }
        } catch (SQLException e) {
            log.error("Error while select from Products, name:{[]}", productName,e.getMessage());
        }
        return product;
    }

    private Product getProduct(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            return Product.builder()
                    .id(resultSet.getInt("id"))
                    .name(resultSet.getString("name"))
                    .price(resultSet.getDouble("price"))
                    .build();
        }
        return null;
    }

    private List<Product> getProducts(ResultSet resultSet) throws SQLException {
        List<Product> products = new ArrayList<>();
        while (resultSet.next()) {
            products.add(Product.builder()
                    .id(resultSet.getInt("id"))
                    .name(resultSet.getString("name"))
                    .price(resultSet.getDouble("price"))
                    .build());
        }
        return products;
    }
}
