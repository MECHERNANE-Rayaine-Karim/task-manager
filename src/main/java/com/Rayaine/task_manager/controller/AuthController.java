package com.Rayaine.task_manager.controller;

import com.Rayaine.task_manager.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/api/auth/register")
    public ResponseEntity<?> register(@RequestBody Map<String,String> request ){
        authService.register(request.get("username"),request.get("password"));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @PostMapping("/api/auth/login")
    public ResponseEntity<?> login(@RequestBody Map<String,String> request ){
        String token =  authService.login(request.get("username"),request.get("password"));
        Map<String,String> response = new HashMap<>();
        response.put("token",token);
        return ResponseEntity.ok(response);
    }


}
