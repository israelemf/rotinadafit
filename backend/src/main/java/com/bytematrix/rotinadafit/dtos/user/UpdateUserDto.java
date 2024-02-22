package com.bytematrix.rotinadafit.dtos.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UpdateUserDto(UUID id,
                            String username,
                            String email,
                            String password,
                            String phone) {
}
