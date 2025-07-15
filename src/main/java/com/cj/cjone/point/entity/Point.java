package com.cj.cjone.point.entity;



import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Point {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false)
	private Long userId; // 사용자 ID

	@Column(nullable = false, unique = true)
	private String username; // 🔥 사용자 이름 (로그인 ID)

	private int balance; // 포인트 잔액

	public Point(Long userId) {
		this.userId = userId;
		this.balance = 0; // 초반 포인트 금액
	}

	public Point(Long userId, String username) {
		this.userId =userId;
		this.balance = 0;
		this.username = username;
	}

	// 비즈니스 로직: 포인트 증가
	public void increase(int amount) {
		if (amount < 0) {
			throw new IllegalArgumentException("포인트는 0보다 커야 합니다.");
		}
		this.balance += amount;
	}

	// 비즈니스 로직: 포인트 감소
	public void decrease(int amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("포인트는 0보다 커야 합니다.");
		}
		if (this.balance < amount) {
			throw new IllegalStateException("포인트가 부족합니다.");
		}
		this.balance -= amount;
	}
}