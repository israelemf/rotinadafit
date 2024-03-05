package com.bytematrix.rotinadafit.dtos.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateUserDto(@NotBlank
                            String username,
                            @NotBlank @Email(message = "Informe um email válido!")
                            String email,
                            @NotBlank
                            @Size(min = 8, message = "A senha exige no minimo 8 caracteres!")
                            String password,
                            @NotBlank
                            @Size(min = 11, max = 11, message = "O número de telefone deve conter 11 digitos!")
                            String phone) {
}
