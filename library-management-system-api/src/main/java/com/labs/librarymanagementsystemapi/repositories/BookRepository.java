package com.labs.librarymanagementsystemapi.repositories;

import com.labs.librarymanagementsystemapi.models.Author;
import com.labs.librarymanagementsystemapi.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, BigInteger> {
    @Query("SELECT b FROM books b WHERE b.title=:title AND b.author.id=:authorId")
    List<Book> findBooksByTitleAAndAndAuthor(String title, BigInteger authorId);


//    @Query("SELECT b FROM books b WHERE b.category=:category AND b.availableNumberOfCopies > 0")
//    List<Book> findAllByCategory(Category category);

    @Query("SELECT b FROM books b WHERE b.availableNumberOfCopies > 0")
    List<Book> findAllAvailableBooks();

//    @Modifying
//    @Query("UPDATE books b SET b.deleted=:deleted WHERE b.id=:book")
//    void updateBooksById(BigInteger bookId, boolean deleted);


}
