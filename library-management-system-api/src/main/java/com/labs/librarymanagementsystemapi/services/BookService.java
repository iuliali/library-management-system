package com.labs.librarymanagementsystemapi.services;

import com.labs.librarymanagementsystemapi.dtos.BookDto;
import com.labs.librarymanagementsystemapi.enums.Category;
import com.labs.librarymanagementsystemapi.repositories.BookRepository;
import com.labs.librarymanagementsystemapi.repositories.BorrowedBooksHistoryRepository;
import com.labs.librarymanagementsystemapi.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookService {
    private final UserRepository userRepository;

    private final BookRepository bookRepository;

    private final BorrowedBooksHistoryRepository booksHistoryRepository;

//    public List<BookDto> getBooks(Optional<Category>) {
//
//    }

}
