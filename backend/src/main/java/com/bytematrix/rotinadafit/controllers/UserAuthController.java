package com.bytematrix.rotinadafit.controllers;

import com.bytematrix.rotinadafit.dtos.authentication.AuthRequestDto;
import com.bytematrix.rotinadafit.dtos.authentication.AuthResponseDto;
import com.bytematrix.rotinadafit.services.user.UserAuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserAuthController {
    private final UserAuthService userAuthService;

    public UserAuthController(UserAuthService userAuthService) {
        this.userAuthService = userAuthService;
    }

    @PostMapping("/user/auth")
    public ResponseEntity<AuthResponseDto> login(@RequestBody AuthRequestDto authRequestDto) {
        return ResponseEntity.ok(this.userAuthService.authenticateManager(authRequestDto));
    }
}
