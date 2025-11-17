package com.example.backendmentoring.user;

public interface UserReader {

    User getByEmail(String email);
}
