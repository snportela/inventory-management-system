package com.snportela.inventory_system.controllers;

import com.snportela.inventory_system.domain.User;
import com.snportela.inventory_system.dtos.UserDto;
import com.snportela.inventory_system.dtos.UserRedeemPasswordDto;
import com.snportela.inventory_system.dtos.UserResetPasswordDto;
import com.snportela.inventory_system.mappers.UserMapper;
import com.snportela.inventory_system.services.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping
        public ResponseEntity<List<UserDto>> listUsers(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "20") int size,
            @RequestParam(required = false, defaultValue = "name") String sortField,
            @RequestParam(required = false, defaultValue = "asc") String order){
        Sort sort = order.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortField).ascending(): Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<User> userList = userService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(userList.stream().map(userMapper::userToDto).collect(Collectors.toList()));
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getUser(@PathVariable("username") String email) {
        Optional<User> foundUser = userService.findByEmail(email);

        if(foundUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(foundUser.map(userMapper::userToDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") UUID userId, @RequestBody UserDto userDto) {
        User updatedUser = userService.update(userId, userMapper.dtoToUser(userDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.userToDto(updatedUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") UUID userId) {
        userService.delete(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/redeem-password")
    public ResponseEntity<String> redeemPassword(@RequestBody UserRedeemPasswordDto userDto) {
        userService.redeemPassword(userDto.email());
        return ResponseEntity.status(HttpStatus.OK).body("Sent the redeem password link to your email");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody UserResetPasswordDto userDto) {
        userService.resetPassword(userDto.token(), userDto.password());

        return ResponseEntity.status(HttpStatus.OK).body("Credentials updated.");
    }
}
