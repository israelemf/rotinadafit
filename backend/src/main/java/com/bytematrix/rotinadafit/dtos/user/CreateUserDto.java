package com.bytematrix.rotinadafit.dtos.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateUserDto(@NotBlank
                            String username,
                            @NotBlank @Email
                            String email,
                            @NotBlank
                            String password,
                            @NotBlank
                            String phone) {
}
