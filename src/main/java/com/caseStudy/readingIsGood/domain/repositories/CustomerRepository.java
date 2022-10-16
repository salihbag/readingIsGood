package com.caseStudy.readingIsGood.domain.repositories;

import com.caseStudy.readingIsGood.domain.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Customer findByEmail(String email);
    Customer findById(int id);
}
