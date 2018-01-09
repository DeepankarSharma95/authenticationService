package com.pavoindus.authentication.service;

public interface AuthenticationService {

    String signup(String username, String password, String firstName, String lastName);
    String login(String username, String password);
    String validateAuthenticationToken(String authToken);
    boolean validateNewSignup(String username, String password);
    boolean logout(String authToken);
}
