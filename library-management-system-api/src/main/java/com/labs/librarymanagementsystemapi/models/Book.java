package com.labs.librarymanagementsystemapi.models;

import com.labs.librarymanagementsystemapi.enums.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books")
@Entity(name = "books")
@SQLDelete(sql = "UPDATE product_ingredients SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")

public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;

    @ManyToOne
    @JoinColumn(name="author_id", referencedColumnName = "id")
    private Author author;

    @OneToMany(
            mappedBy = "book",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    List<BorrowedBook> borrowHistoryList = new ArrayList<>();


    @NonNull
    private Category category;

    @NonNull
    private Long totalNumberOfCopies;

    @NotBlank
    private Long availableNumberOfCopies;

    private boolean deleted = Boolean.FALSE;

}
