package com.cj.cjone.point.repository;

import com.cj.cjone.point.entity.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PointRepository extends JpaRepository<Point, Long> {
	// 사용자 ID로 포인트 정보를 조회
	Optional<Point> findByUserId(Long userId);
}