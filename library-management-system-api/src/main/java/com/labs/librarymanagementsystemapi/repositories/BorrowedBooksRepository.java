package com.labs.librarymanagementsystemapi.repositories;

import com.labs.librarymanagementsystemapi.enums.Status;
import com.labs.librarymanagementsystemapi.models.BorrowedBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public interface BorrowedBooksRepository extends JpaRepository<BorrowedBook, BigInteger> {

    @Override
    Optional<BorrowedBook> findById(BigInteger bigInteger);

    @Query("SELECT b FROM borrowed_books_history b WHERE b.book.id=:bookId AND b.user.id=:userId AND b.status=:status")
    Optional<BorrowedBook> findByBookIdAAndUserIdAndStatus(BigInteger bookId, BigInteger userId, Status status);

    @Query("SELECT b FROM borrowed_books_history b WHERE b.book.id=:bookId")
    List<BorrowedBook> findAllByBookId(BigInteger bookId);

    @Modifying
    @Query("UPDATE borrowed_books_history bb SET bb.deleted=:deleted WHERE bb.book.id=:bookId")
    void updateDeletedByBookId(BigInteger bookId, boolean deleted);
}
