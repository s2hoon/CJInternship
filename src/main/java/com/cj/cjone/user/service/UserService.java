package com.cj.cjone.user.service;

import com.cj.cjone.user.User;
import com.cj.cjone.user.dto.SignInRequest;
import com.cj.cjone.user.dto.SignUpRequest;
import com.cj.cjone.user.dto.TokenResponse;
import com.cj.cjone.user.repository.UserRepository;
import com.cj.cjone.user.utils.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
//    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public TokenResponse signUp(SignUpRequest request) {
        User newUser = User.builder()
                .username(request.username())
                .password(new BCryptPasswordEncoder().encode(request.password()))
                .email(request.email())
                .nickname(request.nickname())
                .age(request.age())
                .build();
        String accessToken = jwtUtil.createAccessToken(newUser);
//        String refreshToken = jwtUtil.createRefreshToken(newUser);

        userRepository.save(newUser);
//        refreshTokenRepository.save(
//                RefreshToken.builder()
//                        .user(newUser)
//                        .token(refreshToken)
//                        .build()
//        );
        return TokenResponse.builder()
                .accessToken(accessToken)
//                .refreshToken(refreshToken)
                .build();
    }

    //TODO: 예외처리 및 예외 메시지 분리
    @Transactional
    public TokenResponse signIn(SignInRequest request) {
        User user = userRepository.findByUsername(request.username())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
        if (!new BCryptPasswordEncoder().matches(request.password(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
//        RefreshToken refreshTokenEntity = refreshTokenRepository.findByUserId(user.getId())
//                .orElseThrow(() -> new IllegalArgumentException("토큰이 존재하지 않습니다."));
        String accessToken = "";
//        String refreshToken = refreshTokenEntity.getToken();
//        if (jwtUtil.isValidRefreshToken(refreshToken)) {
            accessToken = jwtUtil.createAccessToken(user);
            return TokenResponse.builder()
                    .accessToken(accessToken)
//                    .refreshToken(refreshToken)
                    .build();
//        } else {
////            refreshToken = jwtUtil.createRefreshToken(user);
////            refreshTokenEntity.updateToken(refreshToken);
//        }

    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
