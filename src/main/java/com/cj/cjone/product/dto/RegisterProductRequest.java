package com.cj.cjone.product.dto;

import lombok.Getter;

@Getter
public class RegisterProductRequest {
    private String productName;
    private Long price;
    private String description;
}