package com.labs.librarymanagementsystemapi.services;

import com.labs.librarymanagementsystemapi.dtos.NewBookDto;
import com.labs.librarymanagementsystemapi.exceptions.BookAlreadyExistException;
import com.labs.librarymanagementsystemapi.exceptions.NegativeNumberCopiesException;
import com.labs.librarymanagementsystemapi.exceptions.NegativeYearException;
import com.labs.librarymanagementsystemapi.models.Author;
import com.labs.librarymanagementsystemapi.models.Book;
import com.labs.librarymanagementsystemapi.repositories.AuthorRepository;
import com.labs.librarymanagementsystemapi.repositories.BookRepository;
import com.labs.librarymanagementsystemapi.repositories.BorrowedBooksHistoryRepository;
import com.labs.librarymanagementsystemapi.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BookService {
    private final UserRepository userRepository;

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    private final BorrowedBooksHistoryRepository booksHistoryRepository;

    private boolean isNegative(Long num) {
        return num < 0;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAllAvailableBooks();
    }

    public void addNewBook(NewBookDto newBookDto)
            throws NegativeNumberCopiesException, BookAlreadyExistException,
            NegativeYearException {
        if (isNegative(newBookDto.getTotalNumberOfCopies()) ||
        isNegative(newBookDto.getAvailableNumberOfCopies())) {
            throw new NegativeNumberCopiesException(BookService.class);
        }
        if (isNegative(newBookDto.getYear())) {
            throw new NegativeYearException(BookService.class);
        }
        List<Book> books =bookRepository.findBooksByTitleAAndAndAuthor(
                newBookDto.getTitle(),
                newBookDto.getAuthorId());
        if (!books.isEmpty()) {
            throw new BookAlreadyExistException(BookService.class);
        }
        Author author = authorRepository.getAuthorById(newBookDto.getAuthorId());
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

}
