package com.cj.cjone.product.controller;


import com.cj.cjone.product.dto.BuyProductRequest;
import com.cj.cjone.product.dto.RegisterProductRequest;
import com.cj.cjone.product.entity.Product;
import com.cj.cjone.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/buy")
    public Product buyProduct(@RequestBody BuyProductRequest request) {
        return productService.buyProduct(request.getUserId(), request.getProductId(), request.isUsePoint());
    }
    @PostMapping("/register")
    public Product registerProduct(@RequestBody RegisterProductRequest request) {
        return productService.registerProduct(
                request.getProductName(),
                request.getPrice(),
                request.getDescription()
        );
    }
}