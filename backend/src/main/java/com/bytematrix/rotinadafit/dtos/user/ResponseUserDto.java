package com.bytematrix.rotinadafit.dtos.user;

import jakarta.validation.constraints.Email;

import java.time.LocalDateTime;
import java.util.UUID;

public record ResponseUserDto(UUID id,
                              String username,
                              String email,
                              String phone,
                              LocalDateTime createdAt) {
}
