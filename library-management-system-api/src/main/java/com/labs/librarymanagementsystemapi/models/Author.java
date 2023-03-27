package com.labs.librarymanagementsystemapi.models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity(name= "authors")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="authors",
        uniqueConstraints={
        @UniqueConstraint(columnNames = {"firstName", "lastName"})
})
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;

    @OneToMany(
            mappedBy = "author",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Book> bookList = new ArrayList<>();
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
}
