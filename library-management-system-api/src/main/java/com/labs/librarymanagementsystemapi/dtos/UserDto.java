package com.labs.librarymanagementsystemapi.dtos;

import com.labs.librarymanagementsystemapi.enums.Role;
import com.labs.librarymanagementsystemapi.models.User;
import lombok.Data;

import java.math.BigInteger;

@Data
public class UserDto {
    private BigInteger id;
    private String username;
    private Role role;
    private String firstName;
    private String lastName;

    public UserDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.role = user.getRole();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
    }

}
