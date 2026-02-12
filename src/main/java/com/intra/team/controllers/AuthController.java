package com.intra.team.controllers;


import com.intra.team.dtos.LoginDTO;
import com.intra.team.dtos.RegisterDTO;
import com.intra.team.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // ✅ REGISTER
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterDTO dto) {

        authService.register(dto);
        return ResponseEntity.ok("User registered successfully");
    }

    // ✅ LOGIN
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@Valid @RequestBody LoginDTO dto) {

        String token = authService.login(dto);

        return ResponseEntity.ok(
                Map.of(
                        "token", token,
                        "type", "Bearer"
                )
        );
    }


        @GetMapping("/test")
        public String test() {
            return "Protected API works";
        }



}
