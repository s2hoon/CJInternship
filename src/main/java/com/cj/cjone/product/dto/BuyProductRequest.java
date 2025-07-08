package com.cj.cjone.product.dto;

import lombok.Getter;

@Getter
public class BuyProductRequest {
    private Long userId;
    private Long productId;
    private boolean usePoint;
}