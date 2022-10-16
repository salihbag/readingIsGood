package com.caseStudy.readingIsGood.domain.repositories;

import com.caseStudy.readingIsGood.domain.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByCustomerId(int id);
    List<Order> findById(int id);
}
