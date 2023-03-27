package com.labs.librarymanagementsystemapi.dtos;

import com.labs.librarymanagementsystemapi.enums.Role;
import com.labs.librarymanagementsystemapi.models.BorrowedBook;
import com.labs.librarymanagementsystemapi.models.User;
import lombok.Data;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Data
public class UserDto {
    private BigInteger id;
    private String username;
    private Role role;
    private String firstName;
    private String lastName;
    List<BorrowedBook> borrowedBooksList = new ArrayList<>();

    public UserDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.role = user.getRole();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
    }

}
