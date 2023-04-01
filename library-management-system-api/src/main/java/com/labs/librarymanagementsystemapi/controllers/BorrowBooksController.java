package com.labs.librarymanagementsystemapi.controllers;

import com.labs.librarymanagementsystemapi.dtos.BorrowBookDto;
import com.labs.librarymanagementsystemapi.services.BorrowBooksService;
import com.labs.librarymanagementsystemapi.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/borrow-books")
public class BorrowBooksController {
    private final BorrowBooksService borrowBooksService;
    private final UserService userService;

    public BorrowBooksController(BorrowBooksService borrowBooksService, UserService userService) {
        this.borrowBooksService = borrowBooksService;
        this.userService = userService;
    }

    @PostMapping("/borrow")
    public ResponseEntity<?> borrowBook(@RequestBody BorrowBookDto newBorrowBook,
                                        HttpServletRequest request) {
        borrowBooksService.borrow(newBorrowBook, request);
        return new ResponseEntity("Borrowed", HttpStatus.CREATED);
    }

    @PatchMapping("/return")
    public ResponseEntity<?> returnBook(@RequestBody BorrowBookDto newBorrowBook,
                                        HttpServletRequest request) {
        borrowBooksService.returnBook(newBorrowBook, request);
        return new ResponseEntity("Returned", HttpStatus.OK);
    }



}
