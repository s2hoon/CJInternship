package com.cj.cjone.product.service;

import com.cj.cjone.point.entity.Point;
import com.cj.cjone.point.repository.PointRepository;
import com.cj.cjone.product.entity.Product;
import com.cj.cjone.product.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final PointRepository pointRepository;


    @Transactional
    public Product buyProduct(Long userId, Long productId, boolean usePoint) {
        // 상품 조회
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NoSuchElementException("해당 상품이 존재하지 않습니다."));

        // 사용자 포인트 조회 또는 생성
        Point point = pointRepository.findByUserId(userId)
                .orElseGet(() -> pointRepository.save(new Point(userId)));

        if (usePoint) {
            // 포인트로 구매 → 차감
            point.decrease(product.getPrice().intValue());
        } else {
            // 일반 결제 → 5% 적립
            int reward = (int) (product.getPrice() * 0.05);
            point.increase(reward);
        }

        return product;
    }

    @Transactional
    public Product registerProduct(String productName, Long price, String description) {
        Product product = new Product(productName, price, description);
        return productRepository.save(product);
    }
}