package com.labs.librarymanagementsystemapi.dtos;

import com.labs.librarymanagementsystemapi.models.Book;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class AuthorDto {

    private BigInteger id;
    private String firstName;
    private String lastName;
}
