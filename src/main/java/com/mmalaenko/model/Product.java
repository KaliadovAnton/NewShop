package com.mmalaenko.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Product {

    private int id;
    private String name;
    private double price;

    @Override
    public String toString() {
        return name + " " + price;
    }
}
