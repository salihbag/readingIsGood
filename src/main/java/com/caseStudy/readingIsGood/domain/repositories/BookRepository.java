package com.caseStudy.readingIsGood.domain.repositories;

import com.caseStudy.readingIsGood.common.enums.BookStates;
import com.caseStudy.readingIsGood.domain.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    Book findById(int id);
    List<Book> findByState(BookStates state);
    Book findByName(String name);
}
