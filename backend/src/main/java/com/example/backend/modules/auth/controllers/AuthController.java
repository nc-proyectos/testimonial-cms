package com.example.backend.modules.auth.controllers;

import com.example.backend.modules.auth.services.AuthService;
import com.example.backend.shared.ApiResponse;
import com.example.backend.shared.dtos.AuthRequest;
import com.example.backend.shared.dtos.AuthResponse;
import com.example.backend.shared.dtos.RegisterRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Auth", description = "Registro e inicio de sesión")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Registrar nuevo usuario")
    public ApiResponse<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        return ApiResponse.ok(authService.register(request));
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Iniciar sesión")
    public ApiResponse<AuthResponse> login(@Valid @RequestBody AuthRequest request) {
        return ApiResponse.ok(authService.login(request));
    }
}
