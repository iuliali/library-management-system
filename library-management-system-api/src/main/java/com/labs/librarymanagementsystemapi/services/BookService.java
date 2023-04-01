package com.labs.librarymanagementsystemapi.services;

import com.labs.librarymanagementsystemapi.dtos.BookDetails;
import com.labs.librarymanagementsystemapi.dtos.NewBookDto;
import com.labs.librarymanagementsystemapi.enums.Category;
import com.labs.librarymanagementsystemapi.exceptions.*;
import com.labs.librarymanagementsystemapi.models.Author;
import com.labs.librarymanagementsystemapi.models.Book;
import com.labs.librarymanagementsystemapi.repositories.AuthorRepository;
import com.labs.librarymanagementsystemapi.repositories.BookRepository;
import com.labs.librarymanagementsystemapi.repositories.BorrowedBooksRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    private final BorrowedBooksRepository booksHistoryRepository;

    private boolean isNegative(Long num) {
        return num < 0;
    }

    public List<Book> getAllBooks(Optional<Category> category) {
        Sort sort = Sort.by("title");
        if (category.isEmpty()) {
            return bookRepository.findAllAvailableBooks(sort);
        } else {
            return bookRepository.findAllAvailableBooksByCategory(category.get(), sort);
        }
    }
    private void validateYear(Long year) throws NegativeYearException {
        if (isNegative(year)) {
            throw new NegativeYearException(BookService.class);
        }
    }

    private void validateNoCopies(Long noCopies) throws NegativeNumberCopiesException {
        if (isNegative(noCopies)) {
            throw new NegativeNumberCopiesException(BookService.class);
        }
    }
    public void addNewBook(NewBookDto newBookDto)
            throws NegativeNumberCopiesException, BookAlreadyExistException,
            NegativeYearException {

        validateNoCopies(newBookDto.getTotalNumberOfCopies());
        validateNoCopies(newBookDto.getAvailableNumberOfCopies());
        validateYear(newBookDto.getYear());

        List<Book> books =bookRepository.findBooksByTitleAndAndAuthor(
                newBookDto.getTitle(),
                newBookDto.getAuthorId());
        if (!books.isEmpty()) {
            throw new BookAlreadyExistException(BookService.class);
        }
        Author author = authorRepository.getAuthorById(newBookDto.getAuthorId()).orElseThrow(
                () -> new AuthorDoesNotExistException(BookService.class)
        );
        Book book = Book.builder()
                .title(newBookDto.getTitle())
                .category(newBookDto.getCategory())
                .year(newBookDto.getYear())
                .borrowHistoryList(new ArrayList<>())
                .totalNumberOfCopies(newBookDto.getTotalNumberOfCopies())
                .availableNumberOfCopies(newBookDto.getAvailableNumberOfCopies())
                .build();
        book.setAuthor(author);
        bookRepository.save(book);
    }

    public void editDetails(BigInteger bookId, BookDetails bookDetails) throws BookDoesNotExistException, NegativeYearException, NegativeNumberCopiesException {
        Book book = bookRepository.getBookById(bookId).orElseThrow(() ->
                new BookDoesNotExistException(BookService.class)
        );
        // check if any field is null
        // update not null fields

        if(bookDetails.getTitle() != null) {
            book.setTitle(bookDetails.getTitle());
        }

        if(bookDetails.getAuthorId() != null) {
            Author author = authorRepository.getAuthorById(bookDetails.getAuthorId()).orElseThrow(() ->
                    new AuthorDoesNotExistException(BookService.class)
            );
            book.setAuthor(author);
        }
        if(bookDetails.getCategory() != null) {
            book.setCategory(bookDetails.getCategory());
        }
        if(bookDetails.getYear() != null) {
            validateYear(bookDetails.getYear());
            book.setTitle(bookDetails.getTitle());
        }
        if(bookDetails.getTotalNumberOfCopies() != null) {
            validateNoCopies(bookDetails.getTotalNumberOfCopies());
            book.setTotalNumberOfCopies(bookDetails.getTotalNumberOfCopies());
        }
        if(bookDetails.getAvailableNumberOfCopies() != null) {
            validateNoCopies(bookDetails.getAvailableNumberOfCopies());
            book.setAvailableNumberOfCopies(bookDetails.getAvailableNumberOfCopies());
        }

        bookRepository.save(book);
    }

    public void borrowBook(Book book) {
        book.setAvailableNumberOfCopies(book.getAvailableNumberOfCopies() - 1);
    }

    public void returnBook(Book book) {
        book.setAvailableNumberOfCopies(book.getAvailableNumberOfCopies() + 1);
    }
}
