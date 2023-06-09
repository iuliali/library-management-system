package com.labs.librarymanagementsystemapi.models;

import jakarta.persistence.*;
import lombok.*;
import com.labs.librarymanagementsystemapi.enums.Role;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;
    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    List<BorrowedBook> borrowedBooksList = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Role role;
    private String firstName;
    private String lastName;




}
