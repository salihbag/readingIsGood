package com.caseStudy.readingIsGood.services;

import com.caseStudy.readingIsGood.common.enums.BookStates;
import com.caseStudy.readingIsGood.common.exceptions.BusinessException;
import com.caseStudy.readingIsGood.common.utilities.mapping.ModelMapperServiceImpl;
import com.caseStudy.readingIsGood.common.utilities.results.Result;
import com.caseStudy.readingIsGood.core.requests.create.CreateBookRequest;
import com.caseStudy.readingIsGood.core.requests.update.UpdateBookStockRequest;
import com.caseStudy.readingIsGood.core.services.concretes.BookServiceImpl;
import com.caseStudy.readingIsGood.domain.entities.Book;
import com.caseStudy.readingIsGood.domain.repositories.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookServiceImpl;

    private static Book BOOK;

    private static List<Book> BOOK_LIST;

    private static final String NAME="name";

    @BeforeEach
    public void setUp() {
        bookRepository = mock(BookRepository.class);
        bookServiceImpl = new BookServiceImpl(new ModelMapperServiceImpl(new ModelMapper()), bookRepository);
    }
    @BeforeAll
    public static void initialize(){
        BOOK = new Book();
        BOOK.setId(1);
        BOOK.setStock(10);
        BOOK.setName("name");
        BOOK.setState(BookStates.Active);

        BOOK_LIST = new ArrayList<>();
        BOOK_LIST.add(BOOK);
    }

    @Test
    public void addBook_shouldBeSuccess() {

        when(bookRepository.findByName(NAME)).thenReturn(null);
        CreateBookRequest createBookRequest = new CreateBookRequest();
        createBookRequest.setName(NAME);

        Result result = bookServiceImpl.add(createBookRequest);
        Assertions.assertEquals(true, result.isSuccess());
    }

    @Test
    public void addBook_shouldThrowBusinessException() {

        when(bookRepository.findByName(NAME)).thenReturn(BOOK);
        CreateBookRequest createBookRequest = new CreateBookRequest();
        createBookRequest.setName(NAME);

        Executable executable = () -> bookServiceImpl.add(createBookRequest);
        Assertions.assertThrows(BusinessException.class, executable);
    }

    @Test
    public void getActiveBooks_shouldBeSuccess() {
        when(bookRepository.findByState(BookStates.Active)).thenReturn(BOOK_LIST);
        Result result = bookServiceImpl.getActiveBooks();
        Assertions.assertEquals(true, result.isSuccess());
    }

    @Test
    public void getBookById_shouldBeSuccess() {
        when(bookRepository.findById(1)).thenReturn(BOOK);
        Result result = bookServiceImpl.getBookById(1);
        Assertions.assertEquals(true, result.isSuccess());
    }

    @Test
    public void updateStock_shouldBeSuccess() {
        when(bookRepository.findById(1)).thenReturn(BOOK);
        UpdateBookStockRequest updateBookStockRequest = new UpdateBookStockRequest();
        updateBookStockRequest.setId(1);

        Result result = bookServiceImpl.updateStock(updateBookStockRequest);
        Assertions.assertEquals(true, result.isSuccess());
    }

    @Test
    public void updateStock_shouldThrowBusinessException() {
        when(bookRepository.findById(1)).thenReturn(BOOK);
        UpdateBookStockRequest updateBookStockRequest = new UpdateBookStockRequest();
        updateBookStockRequest.setId(2);

        Executable executable = () -> bookServiceImpl.updateStock(updateBookStockRequest);
        Assertions.assertThrows(BusinessException.class, executable);
    }

}
