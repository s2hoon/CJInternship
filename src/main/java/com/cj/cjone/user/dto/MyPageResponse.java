package com.cj.cjone.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MyPageResponse {
	private Long userId;
	private String username;
	private String email;
	private String nickname;
	private Integer age;
	private int pointBalance; // ✅ 포인트 정보 추가
}