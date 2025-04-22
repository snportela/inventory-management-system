package com.snportela.inventory_system.services;

import com.snportela.inventory_system.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface UserService {

    User save(User user);

    Page<User> findAll(Pageable pageable);

    Optional<User> findByEmail(String email);

    User update(UUID userId, User user);

    void delete(UUID userId);

    void sendPasswordResetEmail(String email, String token);

    void redeemPassword(String email);

    void resetPassword(String token, String password);
}
