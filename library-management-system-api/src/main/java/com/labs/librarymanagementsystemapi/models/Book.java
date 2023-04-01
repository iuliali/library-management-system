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
@SQLDelete(sql = "UPDATE books SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")

public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;

    @NotBlank
    private String title;


    @ManyToOne
    @JoinColumn(name="author_id", referencedColumnName = "id")
    private Author author;



    @NonNull
    private Category category;

    @NonNull
    private Long year;

    @NonNull
    private Long totalNumberOfCopies;

    @NonNull
    private Long availableNumberOfCopies;

    private boolean deleted = Boolean.FALSE;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author=" + author +
                ", category=" + category +
                ", year=" + year +
                ", totalNumberOfCopies=" + totalNumberOfCopies +
                ", availableNumberOfCopies=" + availableNumberOfCopies +
                ", deleted=" + deleted +
                '}';
    }
}
