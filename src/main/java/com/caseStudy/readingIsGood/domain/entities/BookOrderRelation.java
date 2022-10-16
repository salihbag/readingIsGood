package com.caseStudy.readingIsGood.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="bookOrderRelations")
public class BookOrderRelation {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private int id;

        @ManyToOne
        @JoinColumn(name="book_id")
        private Book book;

        @ManyToOne
        @JoinColumn(name="order_id")
        private Order order;


}
