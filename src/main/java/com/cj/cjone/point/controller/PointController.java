package com.cj.cjone.point.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cj.cjone.point.dto.PointDto;
import com.cj.cjone.point.service.PointService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/points")
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
}