package com.caseStudy.readingIsGood.domain.entities;

import com.caseStudy.readingIsGood.common.enums.BookStates;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="books")
public class Book {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private int id;

        @Column(name = "name")
        private String name;

        @Column(name = "price")
        private double price;

        @Column(name = "publishedYear")
        private int publishedYear;

        @Column(name = "description")
        private String description;

        @Column(name= "state")
        private BookStates state;

        @Column(name= "stock")
        private int stock;

        @Column(name= "author")
        private String author;

        @OneToMany(mappedBy = "book")
        private List<Order> orders;


}
