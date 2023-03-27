package com.labs.librarymanagementsystemapi.repositories;

import com.labs.librarymanagementsystemapi.enums.Category;
import com.labs.librarymanagementsystemapi.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, BigInteger> {

//    @Query("SELECT b FROM books b WHERE b.category=:category AND b.availableNumberOfCopies > 0")
//    List<Book> getAllBooksByCategory(Category category);
//
//    @Query("SELECT b FROM books b WHERE b.availableNumberOfCopies > 0")
//    List<Book> getAllBooks();
//
//
//    @Modifying
//    @Query("UPDATE books b SET b.deleted=:deleted WHERE b.id=:book")
//    void updateDeletedByProductId(BigInteger bookId, boolean deleted);


}
