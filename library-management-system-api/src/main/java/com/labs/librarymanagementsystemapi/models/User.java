package com.labs.librarymanagementsystemapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.labs.librarymanagementsystemapi.enums.Role;
import java.math.BigInteger;

@Getter
@Setter
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;
    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
    private String firstName;
    private String lastName;


}
