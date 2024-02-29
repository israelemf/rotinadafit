package com.bytematrix.rotinadafit.repositories;

import com.bytematrix.rotinadafit.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findUserByUsername(String username);
    Optional<User> findUserByEmail(String email);
    @Query("SELECT password FROM User u WHERE u.email = :email")
    String findPasswordByEmail(@Param("email") String email);
}
