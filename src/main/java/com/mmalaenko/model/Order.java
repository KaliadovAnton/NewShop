package com.mmalaenko.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class Order {
    private int id;
    private int userID;
    private double totalPrice;


}
