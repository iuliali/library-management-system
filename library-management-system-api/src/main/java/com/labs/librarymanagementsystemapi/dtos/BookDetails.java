package com.labs.librarymanagementsystemapi.dtos;

import com.labs.librarymanagementsystemapi.enums.Category;
import com.labs.librarymanagementsystemapi.models.Author;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;

import java.math.BigInteger;

@Data
public class BookDetails {

    private String title;

    private BigInteger authorId;

    private Category category;

    private Long year;

    private Long totalNumberOfCopies;

    private Long availableNumberOfCopies;

}
