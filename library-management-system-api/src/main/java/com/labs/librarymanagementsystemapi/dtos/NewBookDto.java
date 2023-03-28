package com.labs.librarymanagementsystemapi.dtos;


import com.labs.librarymanagementsystemapi.enums.Category;
import com.labs.librarymanagementsystemapi.models.Author;
import lombok.Data;

import java.math.BigInteger;

@Data
public class NewBookDto {
    private String title;
    private BigInteger authorId;

    private Category category;

    private Long year;

    private Long totalNumberOfCopies;

    private Long availableNumberOfCopies;


}
