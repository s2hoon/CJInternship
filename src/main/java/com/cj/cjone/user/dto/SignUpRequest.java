package com.cj.cjone.user.dto;

public record SignUpRequest(
        String username,
        String password,
        String email,
        String nickname,
        Integer age
) {

}