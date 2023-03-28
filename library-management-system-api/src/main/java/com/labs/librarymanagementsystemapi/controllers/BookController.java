package com.labs.librarymanagementsystemapi.controllers;

import com.labs.librarymanagementsystemapi.dtos.NewAuthorDto;
import com.labs.librarymanagementsystemapi.dtos.NewBookDto;
import com.labs.librarymanagementsystemapi.exceptions.AuthorNameAlreadyExistException;
import com.labs.librarymanagementsystemapi.exceptions.BookAlreadyExistException;
import com.labs.librarymanagementsystemapi.exceptions.NegativeNumberCopiesException;
import com.labs.librarymanagementsystemapi.exceptions.NegativeYearException;
import com.labs.librarymanagementsystemapi.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @GetMapping("/get")
    public ResponseEntity<?> getBooks() {
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNewBook(@RequestBody NewBookDto newBookDto) throws NegativeNumberCopiesException, BookAlreadyExistException, NegativeYearException {
        bookService.addNewBook(newBookDto);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }
}
