package com.bytematrix.rotinadafit.services.user;

import com.bytematrix.rotinadafit.dtos.user.CreateUserDto;
import com.bytematrix.rotinadafit.dtos.user.ResponseUserDto;
import com.bytematrix.rotinadafit.dtos.user.UpdateUserDto;
import com.bytematrix.rotinadafit.entities.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<ResponseUserDto> listAll();
    void save(CreateUserDto createUserDto);
    String update(UpdateUserDto updateUserDto);
    void deleteById(UUID id);
}
