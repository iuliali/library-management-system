package com.labs.librarymanagementsystemapi.services;

import com.labs.librarymanagementsystemapi.dtos.AuthorDto;
import com.labs.librarymanagementsystemapi.dtos.NewAuthorDto;
import com.labs.librarymanagementsystemapi.exceptions.AuthorNameAlreadyExistException;
import com.labs.librarymanagementsystemapi.models.Author;
import com.labs.librarymanagementsystemapi.repositories.AuthorRepository;
import com.labs.librarymanagementsystemapi.repositories.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {
    public BookRepository bookRepository;
    public AuthorRepository authorRepository;

    @Autowired
    public AuthorService(BookRepository bookRepository,
                         AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }


    public List<AuthorDto> getAuthors() {
        List<AuthorDto> authorDtos = new ArrayList<>();

        List<Author> authors = authorRepository.findAll();
        authors.forEach(author -> authorDtos.add(AuthorDto.builder()
                .id(author.getId())
                .firstName(author.getFirstName())
                .lastName(author.getLastName())
                .build())
        );

        return authorDtos;
    }

    public List<Author> getAuthors(String firstName, String lastName) {
        return authorRepository.findAuthorByFirstNameAndLastName(firstName, lastName);
    }




    @Transactional
    public void addNewAuthor(NewAuthorDto newAuthorDto) throws AuthorNameAlreadyExistException {
        List<Author> authors = getAuthors(newAuthorDto.getFirstName(), newAuthorDto.getLastName());
        System.out.println(authors);
        if (!authors.isEmpty()) {
            throw new AuthorNameAlreadyExistException(AuthorService.class);
        }
        Author author = Author.builder()
                .firstName(newAuthorDto.getFirstName())
                .lastName(newAuthorDto.getLastName())
                .build();
        authorRepository.save(author);

    }


}
