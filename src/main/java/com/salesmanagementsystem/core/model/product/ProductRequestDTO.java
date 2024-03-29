package com.salesmanagementsystem.core.model.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductRequestDTO {

    private String name;
    private String description;
    private String category;
    private int quantity;
    private double price;
}
