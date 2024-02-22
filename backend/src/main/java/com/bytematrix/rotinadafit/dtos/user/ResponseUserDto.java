package com.bytematrix.rotinadafit.dtos.user;

import jakarta.validation.constraints.Email;

import java.time.LocalDateTime;

public record ResponseUserDto(String username,
                              String email,
                              String phone,
                              LocalDateTime createdAt) {
}
