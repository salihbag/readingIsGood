package com.caseStudy.readingIsGood.domain.repositories;

import com.caseStudy.readingIsGood.domain.entities.BookOrderRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookOrderRelationRepository extends JpaRepository<BookOrderRelation, Integer> {

}
