package com.caseStudy.readingIsGood.api.controllers;

import com.caseStudy.readingIsGood.common.utilities.results.DataResult;
import com.caseStudy.readingIsGood.common.utilities.results.Result;
import com.caseStudy.readingIsGood.core.requests.create.CreateBookRequest;
import com.caseStudy.readingIsGood.core.requests.update.UpdateBookStockRequest;
import com.caseStudy.readingIsGood.core.responses.GetAllBooksResponse;
import com.caseStudy.readingIsGood.core.services.abstracts.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BooksController {

    @Autowired
    private BookService bookService;

    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/getActiveBooks")
    public ResponseEntity<DataResult<List<GetAllBooksResponse>>> getActiveBooks(){
        return ResponseEntity.ok(this.bookService.getActiveBooks());
    }

    @PostMapping("/add")
    public ResponseEntity<Result> add(@RequestBody @Validated CreateBookRequest createBookRequest){
        return ResponseEntity.ok(this.bookService.add(createBookRequest));
    }

    @PutMapping("/updateBookStock")
    public ResponseEntity<Result> update(@RequestBody UpdateBookStockRequest updateBookStockRequest){
        return ResponseEntity.ok(this.bookService.updateStock(updateBookStockRequest));
    }

}
