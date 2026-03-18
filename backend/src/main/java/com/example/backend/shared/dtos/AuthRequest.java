package com.example.backend.shared.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthRequest {
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email no es válido")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    private String password;
}
