package com.labs.librarymanagementsystemapi.repositories;

import com.labs.librarymanagementsystemapi.enums.Category;
import com.labs.librarymanagementsystemapi.models.Book;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, BigInteger>,
PagingAndSortingRepository<Book, BigInteger> {
    @Query("SELECT b FROM books b WHERE b.title=:title AND b.author.id=:authorId")
    List<Book> findBooksByTitleAndAndAuthor(String title, BigInteger authorId);


//    @Query("SELECT b FROM books b WHERE b.category=:category AND b.availableNumberOfCopies > 0")
//    List<Book> findAllByCategory(Category category);

    @Query("SELECT b FROM books b WHERE b.id=:bookId")
    Optional<Book> getBookById(BigInteger bookId);

    @Query("SELECT b FROM books b WHERE b.availableNumberOfCopies > 0 ORDER BY b.title")
    List<Book> findAllAvailableBooks(Sort title);
    @Query("SELECT b FROM books b WHERE b.category=:category")
    List<Book> findAllAvailableBooksByCategory(Category category, Sort title);

//    @Modifying
//    @Query("UPDATE books b SET b.deleted=:deleted WHERE b.id=:book")
//    void updateBooksById(BigInteger bookId, boolean deleted);


}
