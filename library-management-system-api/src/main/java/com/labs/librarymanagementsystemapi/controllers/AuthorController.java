package com.labs.librarymanagementsystemapi.controllers;

import com.labs.librarymanagementsystemapi.dtos.NewAuthorDto;
import com.labs.librarymanagementsystemapi.exceptions.AuthorNameAlreadyExistException;
import com.labs.librarymanagementsystemapi.models.Author;
import com.labs.librarymanagementsystemapi.services.AuthorService;
import org.h2.mvstore.db.RowDataType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/authors")
public class AuthorController {
    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addAuthor(@RequestBody NewAuthorDto newAuthorDto) throws AuthorNameAlreadyExistException {
        authorService.addNewAuthor(newAuthorDto);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @GetMapping("/get")
    public ResponseEntity<?> getAuthor() {
        return new ResponseEntity<>(authorService.getAuthors(), HttpStatus.OK);
    }



}
