package com.caseStudy.readingIsGood.core.services.concretes;

import com.caseStudy.readingIsGood.common.enums.BookStates;
import com.caseStudy.readingIsGood.common.enums.ResultCodes;
import com.caseStudy.readingIsGood.common.exceptions.BusinessException;
import com.caseStudy.readingIsGood.common.utilities.mapping.ModelMapperService;
import com.caseStudy.readingIsGood.common.utilities.results.DataResult;
import com.caseStudy.readingIsGood.common.utilities.results.Result;
import com.caseStudy.readingIsGood.common.utilities.results.SuccessDataResult;
import com.caseStudy.readingIsGood.common.utilities.results.SuccessResult;
import com.caseStudy.readingIsGood.core.requests.create.CreateBookRequest;
import com.caseStudy.readingIsGood.core.requests.update.UpdateBookStockRequest;
import com.caseStudy.readingIsGood.core.responses.GetAllBooksResponse;
import com.caseStudy.readingIsGood.core.services.abstracts.BaseService;
import com.caseStudy.readingIsGood.core.services.abstracts.BookService;
import com.caseStudy.readingIsGood.domain.entities.Book;
import com.caseStudy.readingIsGood.domain.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl extends BaseService implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(ModelMapperService mapperService, BookRepository bookRepository) {
        super(mapperService);
        this.bookRepository = bookRepository;
    }

    @Override
    public DataResult<List<GetAllBooksResponse>> getActiveBooks() {
        List<GetAllBooksResponse> booksResponseList = forResponseMapList(bookRepository.findByState(BookStates.Active), GetAllBooksResponse.class);
        return new SuccessDataResult<>(booksResponseList);
    }


    @Override
    public DataResult<GetAllBooksResponse> getBookById(int id) {
        GetAllBooksResponse book = forResponseMap(bookRepository.findById(id), GetAllBooksResponse.class);
        return new SuccessDataResult<>(book);
    }

    @Override
    public Result add(CreateBookRequest createBookRequest) {
        checkIfBookExistsByName(createBookRequest.getName());
        Book book = this.modelMapperService.forRequestMap(createBookRequest, Book.class);
        book.setState(BookStates.Active);
        this.bookRepository.save(book);
        return new SuccessResult(ResultCodes.BOOK_ADDED.getCode());
    }

    @Override
    public Result updateStock(UpdateBookStockRequest updateBookStockRequest) {
        Book updatedBook = checkIfBookNotExistsById(updateBookStockRequest.getId());
        updatedBook.setStock(updateBookStockRequest.getStock());
        this.bookRepository.save(updatedBook);
        return new SuccessResult(ResultCodes.BOOK_STOCK_UPDATED.getCode());
    }

    private Book checkIfBookNotExistsById(int id) {
        Book existedBook = this.bookRepository.findById(id);
        if(existedBook == null){
            throw new BusinessException(ResultCodes.BOOK_NOT_EXISTS.getCode());
        }
        return existedBook;
    }

    private void checkIfBookExistsByName(String name) {
        Book existedBook = this.bookRepository.findByName(name);
        if(existedBook != null){
            throw new BusinessException(ResultCodes.BOOK_EXISTS.getCode());
        }
    }
}
