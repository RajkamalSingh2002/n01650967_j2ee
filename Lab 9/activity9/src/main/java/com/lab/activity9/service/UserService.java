package com.lab.activity9.service;

import com.lab.activity9.dto.SignupRequest;
import com.lab.activity9.entity.User;
import com.lab.activity9.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public String registerUser(SignupRequest request) {
        log.info("Attempting to register user: {}", request.getUsername());

        if (userRepository.existsByUsername(request.getUsername())) {
            return "Username already exists";
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            return "Email already exists";
        }

        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .username(request.getUsername())
                .password(request.getPassword()) // plain text only for class activity
                .dob(request.getDob())
                .build();

        userRepository.save(user);
        log.info("User registered successfully: {}", request.getUsername());
        return "SUCCESS";
    }

    public Optional<User> login(String username, String password) {
        log.info("Login attempt for username: {}", username);

        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            if (user.getPassword().equals(password)
                    && user.isEnabled()
                    && user.isAccountNonExpired()
                    && user.isAccountNonLocked()
                    && user.isCredentialsNonExpired()) {
                log.info("Login successful for username: {}", username);
                return Optional.of(user);
            }
        }

        log.warn("Invalid login for username: {}", username);
        return Optional.empty();
    }
}
