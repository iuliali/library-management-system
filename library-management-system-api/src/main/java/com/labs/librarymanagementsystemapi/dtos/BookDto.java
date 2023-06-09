package com.labs.librarymanagementsystemapi.dtos;

import com.labs.librarymanagementsystemapi.enums.Category;
import com.labs.librarymanagementsystemapi.models.Author;
import com.labs.librarymanagementsystemapi.models.Book;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class BookDto {
    private BigInteger id;
    private String title;

    private Author author;
    private Long totalNumberOfCopies;

    private Long availableNumberOfCopies;

    List<Book> borrowHistoryList = new ArrayList<>();

    private Category category;

    private Long year;


}
