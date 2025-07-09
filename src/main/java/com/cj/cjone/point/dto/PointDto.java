package com.cj.cjone.point.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

public class PointDto {

	// 요청 시 사용할 DTO
	@Getter
	@NoArgsConstructor
	public static class Request {
		private Long userId; // 포인트를 변경할 사용자 ID
		private int amount;  // 변경할 포인트 양
	}

	// 응답 시 사용할 DTO
	@Getter
	public static class Response {
		private Long userId;
		private int balance; // 변경 후 최종 포인트

		public Response(Long userId, int balance) {
			this.userId = userId;
			this.balance = balance;
		}
	}
}