package com.cj.cjone.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cj.cjone.user.dto.SignInRequest;
import com.cj.cjone.user.dto.SignUpRequest;
import com.cj.cjone.user.dto.TokenResponse;
import com.cj.cjone.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;

    @PostMapping("/signUp")
    public ResponseEntity<TokenResponse> signUp(@RequestBody SignUpRequest request) {
        if (userService.findByUsername(request.username()).isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.signUp(request));
    }

    @PostMapping("/signIn")
    public ResponseEntity<TokenResponse> signIn(@RequestBody SignInRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.signIn(request));
    }
}