package com.cj.cjone.point.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cj.cjone.point.dto.PointDto;
import com.cj.cjone.point.service.PointService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/point")
@RequiredArgsConstructor
public class PointController {

	private final PointService pointService;

	// 포인트 적립 API
	@PostMapping("/increase")
	public ResponseEntity<PointDto.Response> increasePoint(@RequestBody PointDto.Request request) {
		PointDto.Response response = pointService.increasePoint(request);
		return ResponseEntity.ok(response);
	}

	// 포인트 차감 API
	@PostMapping("/decrease")
	public ResponseEntity<PointDto.Response> decreasePoint(@RequestBody PointDto.Request request) {
		PointDto.Response response = pointService.decreasePoint(request);
		return ResponseEntity.ok(response);
	}

	// 포인트 조회 API
	@GetMapping
	public ResponseEntity<PointDto.Response> getPoint(@RequestParam Long userId) {
		PointDto.Response response = pointService.getPointByUserId(userId);  // ✅ 메서드명도 변경
		return ResponseEntity.ok(response);
	}




}