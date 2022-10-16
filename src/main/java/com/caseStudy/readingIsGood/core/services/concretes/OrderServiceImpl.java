package com.caseStudy.readingIsGood.core.services.concretes;

import com.caseStudy.readingIsGood.common.enums.ResultCodes;
import com.caseStudy.readingIsGood.common.exceptions.BusinessException;
import com.caseStudy.readingIsGood.common.utilities.mapping.ModelMapperService;
import com.caseStudy.readingIsGood.common.utilities.results.Result;
import com.caseStudy.readingIsGood.common.utilities.results.SuccessResult;
import com.caseStudy.readingIsGood.core.requests.create.CreateOrderRequest;
import com.caseStudy.readingIsGood.core.requests.update.UpdateBookStockRequest;
import com.caseStudy.readingIsGood.core.services.abstracts.BaseService;
import com.caseStudy.readingIsGood.core.services.abstracts.BookService;
import com.caseStudy.readingIsGood.core.services.abstracts.OrderService;
import com.caseStudy.readingIsGood.domain.entities.Book;
import com.caseStudy.readingIsGood.domain.entities.BookOrderRelation;
import com.caseStudy.readingIsGood.domain.entities.Order;
import com.caseStudy.readingIsGood.domain.repositories.BookOrderRelationRepository;
import com.caseStudy.readingIsGood.domain.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl extends BaseService implements OrderService {

    private final OrderRepository orderRepository;
    private final BookOrderRelationRepository bookOrderRelationRepository;
    private final BookService bookService;

    @Autowired
    public OrderServiceImpl(ModelMapperService mapperService, OrderRepository orderRepository, BookOrderRelationRepository bookOrderRelationRepository, BookService bookService) {
        super(mapperService);
        this.orderRepository = orderRepository;
        this.bookOrderRelationRepository = bookOrderRelationRepository;
        this.bookService = bookService;
    }


    @Override
    public Result add(CreateOrderRequest createOrderRequest) {
        Order order = this.modelMapperService.forRequestMap(createOrderRequest, Order.class);
        this.orderRepository.save(order);

        for (int bookId : createOrderRequest.getBookIdList()) {
            BookOrderRelation bookOrderRelation = new BookOrderRelation();
            bookOrderRelation.setOrder(order);
            bookOrderRelation.setBook(bookStockDown(bookId, createOrderRequest.getBookIdList().size()));
            this.bookOrderRelationRepository.save(bookOrderRelation);
        }
        return new SuccessResult(ResultCodes.ORDER_CREATED.getCode());
    }

    private Book bookStockDown(int id, int orderCount){
        Book book = this.modelMapperService.forResponseMap(bookService.getBookById(id).getData(), Book.class);
        if(book.getStock() < orderCount){
            throw new BusinessException(ResultCodes.BOOK_STOCK_ERROR.getCode());
        }else{
            bookService.updateStock(new UpdateBookStockRequest(book.getId(), book.getStock()-1));
        }
        return book;
    }

}
