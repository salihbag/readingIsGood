package com.caseStudy.readingIsGood.domain.repositories;

import com.caseStudy.readingIsGood.common.enums.CustomerStates;
import com.caseStudy.readingIsGood.domain.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    List<Customer> findByState(CustomerStates state);
    Customer findByEmail(String email);
    Customer findById(int id);
}
