package com.example.backendmentoring.auth;

import com.example.backendmentoring.auth.dto.SignUpRequest;
import com.example.backendmentoring.auth.dto.SignUpResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    private final AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public SignUpResponse signUp(@RequestBody SignUpRequest request) {
        return authService.signUp(request);
    }
}

