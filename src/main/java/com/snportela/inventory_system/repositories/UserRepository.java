package com.snportela.inventory_system.repositories;

import com.snportela.inventory_system.domain.User;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

@NonNullApi
public interface UserRepository extends JpaRepository<User, UUID> {

    Page<User> findAll(Pageable pageable);

    Optional<User> findById(UUID userId);

    Optional<User> findByEmail(String email);

    Optional<User> findByResetToken(String resetToken);

}
