package com.snportela.inventory_system.services.impl;

import com.snportela.inventory_system.domain.SecurityUser;
import com.snportela.inventory_system.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.snportela.inventory_system.domain.User user =  userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new SecurityUser(user);
    }

}
