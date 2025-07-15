package com.cj.cjone.user.service;

import java.time.Duration;
import java.util.Optional;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.cj.cjone.point.entity.Point;
import com.cj.cjone.point.repository.PointRepository;
import com.cj.cjone.user.User;
import com.cj.cjone.user.dto.MyPageResponse;
import com.cj.cjone.user.dto.SignInRequest;
import com.cj.cjone.user.dto.SignUpRequest;
import com.cj.cjone.user.dto.TokenResponse;
import com.cj.cjone.user.repository.UserRepository;
import com.cj.cjone.user.utils.JwtUtil;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PointRepository pointRepository;
    private final StringRedisTemplate redisTemplate;

    private static final Duration TOKEN_EXPIRE_TIME = Duration.ofHours(3); // 토큰 만료 시간 예시

    private String hashPassword(String plainPassword) {
        return BCrypt.withDefaults().hashToString(12, plainPassword.toCharArray());
    }

    private boolean verifyPassword(String plainPassword, String hashedPassword) {
        BCrypt.Result result = BCrypt.verifyer().verify(plainPassword.toCharArray(), hashedPassword);
        return result.verified;
    }

    @Transactional
    public TokenResponse signUp(SignUpRequest request) {
        User newUser = User.builder()
            .username(request.username())
            .password(hashPassword(request.password()))
            .email(request.email())
            .nickname(request.nickname())
            .age(request.age())
            .build();

        userRepository.save(newUser);

        String accessToken = jwtUtil.createAccessToken(newUser);
        redisTemplate.opsForValue().set("accessToken:" + newUser.getUsername(), accessToken, TOKEN_EXPIRE_TIME);

        return TokenResponse.builder()
            .accessToken(accessToken)
            .build();
    }

    @Transactional
    public TokenResponse signIn(SignInRequest request) {
        User user = userRepository.findByUsername(request.username())
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        if (!verifyPassword(request.password(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        String accessToken = jwtUtil.createAccessToken(user);
        redisTemplate.opsForValue().set("accessToken:" + user.getUsername(), accessToken, TOKEN_EXPIRE_TIME);

        return TokenResponse.builder()
            .accessToken(accessToken)
            .userId(user.getId())  // 🔥 userId 추가
            .build();
    }


    public MyPageResponse getMyPage(Long userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));

        int balance = pointRepository.findByUserId(userId)
            .map(Point::getBalance)
            .orElse(0); // 사용자가 포인트가 없을 수도 있음

        return MyPageResponse.builder()
            .userId(user.getId())
            .username(user.getUsername())
            .email(user.getEmail())
            .nickname(user.getNickname())
            .age(user.getAge())
            .pointBalance(balance) // ✅ 포인트 포함
            .build();
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}