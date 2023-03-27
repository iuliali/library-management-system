package com.labs.librarymanagementsystemapi.repositories;

import com.labs.librarymanagementsystemapi.models.BorrowedBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface BorrowedBooksHistoryRepository extends JpaRepository<BorrowedBook, BigInteger> {
}
