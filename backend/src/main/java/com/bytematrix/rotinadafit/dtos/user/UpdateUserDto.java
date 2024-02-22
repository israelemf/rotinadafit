package com.bytematrix.rotinadafit.dtos.user;

import jakarta.validation.constraints.Email;
import java.util.UUID;

public record UpdateUserDto(UUID id,
                            String username,
                            @Email
                            String email,
                            String password,
                            String phone) {
}
