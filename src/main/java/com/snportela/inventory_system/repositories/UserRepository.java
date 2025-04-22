package com.snportela.inventory_system.repositories;

import com.snportela.inventory_system.domain.User;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

@NonNullApi
public interface UserRepository extends JpaRepository<User, UUID> {

    @Query(value = "SELECT * FROM users WHERE deleted_at IS NULL", nativeQuery = true)
    Page<User> findAll(Pageable pageable);

    @Query(value = "SELECT * FROM users WHERE user_id = ?1 AND deleted_at IS NULL", nativeQuery = true)
    Optional<User> findById(UUID userId);

    @Query(value = "SELECT * FROM users WHERE email = ?1 AND deleted_at IS NULL", nativeQuery = true)
    Optional<User> findByEmail(String email);

    @Query(value = "SELECT * FROM users WHERE reset_token = ?1 AND deleted_at IS NULL", nativeQuery = true)
    Optional<User> findByResetToken(String resetToken);

    @Modifying
    @Query(value = "UPDATE users SET deleted_at = NOW() WHERE user_id = ?1 AND deleted_at IS NULL", nativeQuery = true)
    void deleteById(UUID userId);


}
