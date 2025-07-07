package com.cj.cjone.point.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cj.cjone.point.entity.Point;

public interface PointRepository extends JpaRepository<Point, Long> {
	// 사용자 ID로 포인트 정보를 조회
	Optional<Point> findByUserId(Long userId);
}