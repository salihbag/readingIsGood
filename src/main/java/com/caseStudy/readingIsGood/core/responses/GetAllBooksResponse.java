package com.caseStudy.readingIsGood.core.responses;

import com.caseStudy.readingIsGood.common.enums.BookStates;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllBooksResponse {

    private int id;

    private String name;

    private String author;

    private double price;

    private int publishedYear;

    private String description;

    private BookStates state;

    private int stock;
}
