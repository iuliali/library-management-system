package com.labs.librarymanagementsystemapi.repositories;

import com.labs.librarymanagementsystemapi.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, BigInteger> {

    @Query(value = "SELECT a FROM authors a WHERE a.firstName=:firstName AND a.lastName=:lastName")
    List<Author> findAuthorByFirstNameAndLastName(String firstName, String lastName);


}
