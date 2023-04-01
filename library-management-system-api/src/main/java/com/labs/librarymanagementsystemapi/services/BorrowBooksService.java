package com.labs.librarymanagementsystemapi.services;

import com.labs.librarymanagementsystemapi.dtos.BorrowBookDto;
import com.labs.librarymanagementsystemapi.enums.Status;
import com.labs.librarymanagementsystemapi.exceptions.BookAlreadyReturnedException;
import com.labs.librarymanagementsystemapi.exceptions.BookDoesNotExistException;
import com.labs.librarymanagementsystemapi.exceptions.BookNotBorrowedException;
import com.labs.librarymanagementsystemapi.exceptions.BorrowBookAlreadyBorrowedException;
import com.labs.librarymanagementsystemapi.models.Book;
import com.labs.librarymanagementsystemapi.models.BorrowedBook;
import com.labs.librarymanagementsystemapi.models.User;
import com.labs.librarymanagementsystemapi.repositories.BookRepository;
import com.labs.librarymanagementsystemapi.repositories.BorrowedBooksRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class BorrowBooksService {
    private final BookService bookService;
    private final UserService userService;
    private final BookRepository bookRepository;

    private final BorrowedBooksRepository borrowedBooksRepository;


    public void borrow(BorrowBookDto newBorrowBook, HttpServletRequest request) {
        Book book = bookRepository.getBookById(newBorrowBook.getBookId()).orElseThrow(
                () -> new BookDoesNotExistException(BorrowedBooksRepository.class)
        );
        // Decided to take directly from current user the id (from token)
        User user = userService.getUserFromHeader(request);

        BorrowedBook borrowedBook = borrowedBooksRepository.findByBookIdAAndUserIdAndStatus(
                book.getId(),
                user.getId(),
                Status.BORROWED).orElse(null);
        if (borrowedBook != null) {
            throw new BorrowBookAlreadyBorrowedException(BorrowBooksService.class);
        }
        if (book. getAvailableNumberOfCopies() == 0) {
            //do custom exception
            return;
        }
        bookService.borrowBook(book);
        BorrowedBook newBorrowedBook = BorrowedBook.builder()
                .user(user)
                .book(book)
                .borrowedAt(LocalDateTime.now())
                .status(Status.BORROWED)
                .build();
        borrowedBooksRepository.save(newBorrowedBook);
    }

    public void returnBook(BorrowBookDto newBorrowBook,
                           HttpServletRequest request) {
        Book book = bookRepository.getBookById(newBorrowBook.getBookId()).orElseThrow(
                () -> new BookDoesNotExistException(BorrowedBooksRepository.class)
        );

        User user = userService.getUserFromHeader(request);

        BorrowedBook borrowedBook = borrowedBooksRepository.findByBookIdAAndUserIdAndStatus(book.getId(), user.getId(),
                Status.BORROWED).orElseThrow(
                () -> new BookNotBorrowedException(BorrowBooksService.class)
        );

        bookService.returnBook(book);
        borrowedBook.setReturnedAt(LocalDateTime.now());
        borrowedBook.setStatus(Status.RETURNED);
        borrowedBooksRepository.save(borrowedBook);
    }
}
