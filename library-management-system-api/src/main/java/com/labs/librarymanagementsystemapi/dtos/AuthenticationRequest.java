package com.labs.librarymanagementsystemapi.dtos;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String username;
    private String password;
}
