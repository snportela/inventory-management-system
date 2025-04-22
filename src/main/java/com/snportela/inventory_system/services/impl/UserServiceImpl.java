package com.snportela.inventory_system.services.impl;

import com.snportela.inventory_system.domain.User;
import com.snportela.inventory_system.exceptions.NotFoundException;
import com.snportela.inventory_system.infra.security.ResetTokenService;
import com.snportela.inventory_system.repositories.UserRepository;
import com.snportela.inventory_system.services.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final EmailService emailService;

    private final ResetTokenService resetTokenService;

    public UserServiceImpl(UserRepository userRepository, EmailService emailService, ResetTokenService resetTokenService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.resetTokenService = resetTokenService;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }


    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User update(UUID userId, User user) {
        User existingUser = userRepository.findById(userId).orElseThrow(NotFoundException::new);

        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPhone(user.getPhone());
        existingUser.setRole(user.getRole());
        return userRepository.save(existingUser);
    }

    @Override
    public void delete(UUID userId) {
        userRepository.findById(userId).orElseThrow(NotFoundException::new);
        userRepository.deleteById(userId);
    }

    @Override
    public void sendPasswordResetEmail(String email, String token) {
        String subject = "Password Reset Request";
        String resetUrl = "https://seusite.com/reset?token=" + token;
        String body = "Click the link to reset your password: " + resetUrl;

        emailService.sendEmail(email, subject, body);
    }

    @Override
    public void redeemPassword(String email) {
        User foundUser = userRepository.findByEmail(email).orElseThrow(NotFoundException::new);

        var token = resetTokenService.generateResetToken(foundUser);
        foundUser.setResetToken(token);

        userRepository.save(foundUser);

        sendPasswordResetEmail(email, token);
    }

    @Override
    public void resetPassword(String token, String password) {
        resetTokenService.validateResetToken(token);

        User foundUser = userRepository.findByResetToken(token).orElseThrow(NotFoundException::new);

        String encryptedPassword = new BCryptPasswordEncoder().encode(password);

        foundUser.setPassword(encryptedPassword);
        foundUser.setResetToken(null);

        userRepository.save(foundUser);
    }
}
