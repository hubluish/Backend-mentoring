package com.example.backendmentoring.common;

import java.time.LocalDateTime;

public record ApiErrorResponse(
        LocalDateTime time,
        String status,
        String message,
        String requestURI
) {
    public static ApiErrorResponse of(String status, String message, String requestURI) {
        return new ApiErrorResponse(LocalDateTime.now(), status, message, requestURI);
    }
}
