package com.bytematrix.rotinadafit.services.user;

import com.bytematrix.rotinadafit.configuration.security.authentication.JwtService;
import com.bytematrix.rotinadafit.configuration.security.authentication.UserDetailsImpl;
import com.bytematrix.rotinadafit.dtos.authentication.AuthRequestDto;
import com.bytematrix.rotinadafit.dtos.authentication.AuthResponseDto;
import com.bytematrix.rotinadafit.entities.User;
import com.bytematrix.rotinadafit.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserAuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserAuthService(UserRepository userRepository,
                           JwtService jwtService,
                           AuthenticationManager authenticationManager, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public AuthResponseDto authenticateManager(AuthRequestDto authRequest) {
        var user = new User();

        if (authRequest.email().contains("@")) {
            user = userRepository.findUserByEmail(authRequest.email())
                    .orElseThrow(() -> new EntityNotFoundException("O login " + authRequest.email() + " não existe!"));
        } else {
            user = userRepository.findUserByUsername(authRequest.email())
                    .orElseThrow(() -> new EntityNotFoundException("O login " + authRequest.email() + " não existe!"));
        }

        if (!this.passwordEncoder.matches(authRequest.password(), user.getPassword())) {
            throw new BadCredentialsException("Senha incorreta!");
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.email(),
                        authRequest.password()
                )
        );

        var jwtToken = jwtService.generateToken(new UserDetailsImpl(user));

        return new AuthResponseDto(jwtToken);
    }
}
