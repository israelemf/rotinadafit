package com.bytematrix.rotinadafit.controllers;

import com.bytematrix.rotinadafit.dtos.user.CreateUserDto;
import com.bytematrix.rotinadafit.dtos.user.ResponseUserDto;
import com.bytematrix.rotinadafit.dtos.user.UpdateUserDto;
import com.bytematrix.rotinadafit.services.user.UserServiceImpl;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<ResponseUserDto>> listAllUsers() {
        return ResponseEntity.ok().body(this.userService.listAll());
    }
    @PostMapping
    public ResponseEntity<String> saveUser(@RequestBody @Valid CreateUserDto createUserDto) {
        userService.save(createUserDto);

        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário criado com sucesso!");
    }

    @PutMapping
    public ResponseEntity<String> updateUser(@RequestBody @Valid UpdateUserDto updateUserDto) {
        return ResponseEntity.ok().body(userService.update(updateUserDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") UUID id) {
        this.userService.deleteById(id);

        return ResponseEntity.ok().body("Usuário excluído!");
    }
}
