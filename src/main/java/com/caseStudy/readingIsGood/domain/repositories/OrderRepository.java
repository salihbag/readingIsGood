package com.caseStudy.readingIsGood.domain.repositories;

import com.caseStudy.readingIsGood.domain.entities.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByCustomerId(int id, Pageable pageable);
    List<Order> findById(int id);
    List<Order> findByCreatedDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
