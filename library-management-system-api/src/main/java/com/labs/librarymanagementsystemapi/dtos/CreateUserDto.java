package com.labs.librarymanagementsystemapi.dtos;

import com.labs.librarymanagementsystemapi.enums.Role;
import com.labs.librarymanagementsystemapi.models.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Builder
@Data
public class CreateUserDto {
    @NotBlank
    @Size(min = 5, max = 50)
    private String username;

    @NotBlank
    @Size(min = 10)
    private String password;

    @NonNull
    private Role role;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    public static User toUser(CreateUserDto createUserDto) {
        var newUser = new User();
        newUser.setUsername(createUserDto.getUsername());
        newUser.setRole(createUserDto.getRole());
        newUser.setFirstName(createUserDto.getFirstName());
        newUser.setLastName(createUserDto.getLastName());
        return newUser;
    }


}
