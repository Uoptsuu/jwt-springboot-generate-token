package com.spring.jwt.controller;

import com.spring.jwt.model.login.LoginRequest;
import com.spring.jwt.model.login.LoginResponse;
import com.spring.jwt.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest authRequest){
        LoginResponse loginResponse = authService.authenticateLogin(authRequest);
        loginResponse.setId(UUID.randomUUID().toString());
        if (loginResponse.getToken() == null){
            return ResponseEntity.status(HttpStatusCode.valueOf(404)).body(loginResponse);
        } else return ResponseEntity.ok(loginResponse);
    }
}
