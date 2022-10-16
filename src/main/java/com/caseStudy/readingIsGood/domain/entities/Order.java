package com.caseStudy.readingIsGood.domain.entities;

import com.caseStudy.readingIsGood.common.enums.BookStates;
import com.caseStudy.readingIsGood.common.enums.OrderStates;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="orders")
public class Order {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private int id;

        @Column(name = "createdDate")
        private LocalDateTime createdDate;

        @Column(name = "totalPrice")
        private double price;

        @Column(name = "totalBookCount")
        private int totalBookCount;

        @Column(name= "state")
        private OrderStates state;

        @Column(name= "stock")
        private int stock;

        @Column(name= "author")
        private String author;

        @ManyToOne
        @JoinColumn(name="customer_id")
        private Customer customer;

        @ManyToOne
        @JoinColumn(name="book_id")
        private Book book;


}
