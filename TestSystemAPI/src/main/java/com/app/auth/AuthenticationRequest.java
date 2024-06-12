package com.app.auth;

public class AuthenticationRequest {

    private String email;

    private  String password;

    public AuthenticationRequest(Builder builder) {
        this.email = builder.email;
        this.password = builder.password;
    }

    public AuthenticationRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }


    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }


    public static class Builder {
        private String email;

        private  String password;


    }


}
