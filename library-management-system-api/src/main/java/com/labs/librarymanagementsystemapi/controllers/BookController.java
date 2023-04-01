package com.labs.librarymanagementsystemapi.controllers;

import com.labs.librarymanagementsystemapi.dtos.BookDetails;
import com.labs.librarymanagementsystemapi.dtos.BookDto;
import com.labs.librarymanagementsystemapi.dtos.NewAuthorDto;
import com.labs.librarymanagementsystemapi.dtos.NewBookDto;
import com.labs.librarymanagementsystemapi.enums.Category;
import com.labs.librarymanagementsystemapi.exceptions.*;
import com.labs.librarymanagementsystemapi.services.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

//    @GetMapping("/get/{id}")
//    public ResponseEntity<?> getBooks(@Valid @PathVariable(name = "id") BigInteger id) {
//        return new ResponseEntity<>(bookService.getAllBooks(Optional.empty()),
//                HttpStatus.OK);
//    }
    @GetMapping(value = {"/get/all", "/get/all/{category}"})
    public ResponseEntity<?> getBooks(@Valid @PathVariable(name = "category") Optional<Category> category) {
        return new ResponseEntity<>(bookService.getAllBooks(category),
                HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNewBook(@RequestBody NewBookDto newBookDto) throws NegativeNumberCopiesException, BookAlreadyExistException, NegativeYearException {
        bookService.addNewBook(newBookDto);
        return new ResponseEntity<>("Successfully added", HttpStatus.CREATED);

    }

    @PatchMapping("/edit/details/{id}")
    public ResponseEntity<?> editBookDetails(@PathVariable BigInteger id,
                                             @Valid @RequestBody BookDetails bookDetails) throws BookDoesNotExistException, NegativeYearException, NegativeNumberCopiesException {

        bookService.editDetails(id, bookDetails);
        return new ResponseEntity<>("Successfully edited",HttpStatus.OK);
    }



}
