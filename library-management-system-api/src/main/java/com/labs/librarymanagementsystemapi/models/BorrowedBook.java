package com.labs.librarymanagementsystemapi.models;

import com.labs.librarymanagementsystemapi.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "borrowed_books_history")
@Entity(name = "borrowed_books_history")
public class BorrowedBook {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Book book;

    @NotNull
    private Status status;

    @NotNull
    private LocalDateTime borrowedAt;

    private LocalDateTime returnedAt;

}
