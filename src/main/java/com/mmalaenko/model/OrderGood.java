package com.mmalaenko.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class OrderGood {
    private int id;
    private  int orderID;
    private  int productID;

}
