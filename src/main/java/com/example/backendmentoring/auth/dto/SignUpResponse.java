package com.example.backendmentoring.auth.dto;

import com.example.backendmentoring.user.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SignUpResponse {
    private final String email;
    private final String username;

    public static SignUpResponse from(User user) {
        return new SignUpResponse(user.getEmail(), user.getUsername());
    }
}
