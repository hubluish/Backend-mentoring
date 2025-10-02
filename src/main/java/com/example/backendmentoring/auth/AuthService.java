package com.example.backendmentoring.auth;

import com.example.backendmentoring.auth.dto.SignUpRequest;
import com.example.backendmentoring.auth.dto.SignUpResponse;
import com.example.backendmentoring.user.DuplicateEmailException;
import com.example.backendmentoring.user.User;
import com.example.backendmentoring.user.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public SignUpResponse signUp(SignUpRequest req) {
        if (userRepository.existsByEmail(req.email())) {
            throw new DuplicateEmailException(req.email());
        }

        String hashed = passwordEncoder.encode(req.password());
        User savedUser = userRepository.save(User.of(req.email(), req.username(), hashed));

        return SignUpResponse.from(savedUser);
    }
}
