package com.caseStudy.readingIsGood.core.services.abstracts;

import com.caseStudy.readingIsGood.common.utilities.results.DataResult;
import com.caseStudy.readingIsGood.common.utilities.results.Result;
import com.caseStudy.readingIsGood.core.requests.create.CreateBookRequest;
import com.caseStudy.readingIsGood.core.requests.update.UpdateBookStockRequest;
import com.caseStudy.readingIsGood.core.responses.GetAllBooksResponse;

import java.util.List;

public interface BookService {

    DataResult<List<GetAllBooksResponse>> getActiveBooks();
    Result add(CreateBookRequest createBookRequest);
    Result updateStock(UpdateBookStockRequest updateBookStockRequest);
}
