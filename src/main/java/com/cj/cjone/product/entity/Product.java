package com.cj.cjone.product.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;

    private Long price;

    private String description;

    public Product(String productName, Long price, String description) {
        this.productName = productName;
        this.price = price;
        this.description = description;
    }
}