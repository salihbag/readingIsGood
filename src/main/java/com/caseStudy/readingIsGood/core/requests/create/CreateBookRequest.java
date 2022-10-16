package com.caseStudy.readingIsGood.core.requests.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookRequest {

    @NotNull
    @NotBlank
    @Size(min = 1, max = 30, message = "Name length can be from 1 to 30")
    private String name;

    private String author;

    @Min(value = 0, message = "The price must be a positive value")
    private double price;

    @Range(min = 1700, max = 2100, message = "Year can be 4 digits")
    private int publishedYear;

    private String description;

    private int stock;
}
