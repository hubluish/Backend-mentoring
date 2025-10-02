package com.example.backendmentoring.common;

import com.example.backendmentoring.user.DuplicateEmailException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateEmailException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiErrorResponse handleDuplicateEmail(DuplicateEmailException e, HttpServletRequest req) {
        return ApiErrorResponse.of("CONFLICT", e.getMessage(), req.getRequestURI());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse handleIllegalArgument(IllegalArgumentException e, HttpServletRequest req) {
        return ApiErrorResponse.of("BAD_REQUEST", e.getMessage(), req.getRequestURI());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiErrorResponse handleOthers(Exception e, HttpServletRequest req) {
        return ApiErrorResponse.of("INTERNAL_SERVER_ERROR", "Unexpected error", req.getRequestURI());
    }
}
