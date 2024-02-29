package com.bytematrix.rotinadafit.services.user;

import com.bytematrix.rotinadafit.dtos.user.CreateUserDto;
import com.bytematrix.rotinadafit.dtos.user.ResponseUserDto;
import com.bytematrix.rotinadafit.dtos.user.UpdateUserDto;
import com.bytematrix.rotinadafit.entities.User;
import com.bytematrix.rotinadafit.repositories.UserRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<ResponseUserDto> listAll() {
        List<User> users = this.userRepository.findAll();
        List<ResponseUserDto> usersDto = new ArrayList<>();

        users.forEach(user -> {
            usersDto.add(new ResponseUserDto(user.getId(),
                    user.getUsername(),
                    user.getEmail(),
                    user.getPhone(),
                    user.getCreatedAt()));
        });

        return usersDto;
    }

    @Override
    public void save(CreateUserDto createUserDto) {
        String email = createUserDto.email();
        String username = createUserDto.username();
        String encryptedPassword = this.passwordEncoder.encode(createUserDto.password());

        if (this.userRepository.findUserByEmail(email).isPresent()) {
            throw new EntityExistsException("Esse email já está em uso. Tente outro.");
        }

        if (this.userRepository.findUserByUsername(username).isPresent()) {
            throw new EntityExistsException("Esse nome de usuário já está em uso. Tente outro.");
        }

        var user = new User();
        BeanUtils.copyProperties(createUserDto, user);

        user.setPassword(encryptedPassword);

        this.userRepository.save(user);
    }

    @Override
    public String update(UpdateUserDto updateUserDto) {
        var userToUpdate = this.userRepository.findById(updateUserDto.id())
                .orElseThrow(() -> new EntityNotFoundException(("Usuário não encontrado!")));;

        if (updateUserDto.email() != null) {
            userToUpdate.setEmail(updateUserDto.email());
            this.userRepository.save(userToUpdate);
            return "Email alterado com sucesso!";
        } else if (updateUserDto.username() != null) {
            userToUpdate.setUsername(updateUserDto.username());
            this.userRepository.save(userToUpdate);
            return "Nome de usuário alterado com sucesso!";
        } else if (updateUserDto.password() != null) {
            userToUpdate.setPassword(updateUserDto.password());
            this.userRepository.save(userToUpdate);
            return "Senha alterada com sucesso!";
        }
        return null;
    }

    @Override
    public void deleteById(UUID id) {
        User userToDelete = this.userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(("Usuário não encontrado!")));

        this.userRepository.deleteById(id);
    }
}
