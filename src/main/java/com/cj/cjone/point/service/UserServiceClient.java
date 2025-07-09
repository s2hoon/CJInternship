package com.cj.cjone.point.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")
public interface UserServiceClient {

	// 사용자 서비스에 사용자가 존재하는지 확인하는 API가 있다고 가정
	@GetMapping("/users/exists/{userId}")
	void checkUserExists(@PathVariable("userId") Long userId);
}