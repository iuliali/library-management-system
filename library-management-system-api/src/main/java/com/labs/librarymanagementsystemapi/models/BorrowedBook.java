package com.labs.librarymanagementsystemapi.models;

import com.labs.librarymanagementsystemapi.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "borrowed_books_history")
@Entity(name = "borrowed_books_history")
@SQLDelete(sql = "UPDATE books SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")

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

    private boolean deleted = Boolean.FALSE;

}
