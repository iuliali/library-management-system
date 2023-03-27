package com.labs.librarymanagementsystemapi.dtos;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class NewAuthorDto {
    private String firstName;
    private String lastName;

    private List<BookDto> bookList = new ArrayList<>();

}
