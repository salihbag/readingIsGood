package com.caseStudy.readingIsGood.core.services.concretes;

import com.caseStudy.readingIsGood.common.enums.OrderStates;
import com.caseStudy.readingIsGood.common.enums.ResultCodes;
import com.caseStudy.readingIsGood.common.exceptions.BusinessException;
import com.caseStudy.readingIsGood.common.utilities.AppUtils;
import com.caseStudy.readingIsGood.common.utilities.mapping.ModelMapperService;
import com.caseStudy.readingIsGood.common.utilities.results.DataResult;
import com.caseStudy.readingIsGood.common.utilities.results.Result;
import com.caseStudy.readingIsGood.common.utilities.results.SuccessDataResult;
import com.caseStudy.readingIsGood.common.utilities.results.SuccessResult;
import com.caseStudy.readingIsGood.core.requests.create.CreateOrderRequest;
import com.caseStudy.readingIsGood.core.requests.update.UpdateBookStockRequest;
import com.caseStudy.readingIsGood.core.responses.GetAllOrdersResponse;
import com.caseStudy.readingIsGood.core.services.abstracts.BaseService;
import com.caseStudy.readingIsGood.core.services.abstracts.BookService;
import com.caseStudy.readingIsGood.core.services.abstracts.CustomerService;
import com.caseStudy.readingIsGood.core.services.abstracts.OrderService;
import com.caseStudy.readingIsGood.domain.entities.Book;
import com.caseStudy.readingIsGood.domain.entities.BookOrderRelation;
import com.caseStudy.readingIsGood.domain.entities.Customer;
import com.caseStudy.readingIsGood.domain.entities.Order;
import com.caseStudy.readingIsGood.domain.repositories.BookOrderRelationRepository;
import com.caseStudy.readingIsGood.domain.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl extends BaseService implements OrderService {

    private final OrderRepository orderRepository;
    private final BookOrderRelationRepository bookOrderRelationRepository;
    private final BookService bookService;
    private final CustomerService customerService;

    @Autowired
    public OrderServiceImpl(ModelMapperService mapperService, OrderRepository orderRepository, BookOrderRelationRepository bookOrderRelationRepository, BookService bookService, CustomerService customerService) {
        super(mapperService);
        this.orderRepository = orderRepository;
        this.bookOrderRelationRepository = bookOrderRelationRepository;
        this.bookService = bookService;
        this.customerService = customerService;
    }



    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result add(CreateOrderRequest createOrderRequest) {
        Order order = new Order();
        order.setCustomer(getCustomer(createOrderRequest.getCustomerId()));
        order.setState(OrderStates.Ordered);
        order.setCreatedDate(AppUtils.now());
        order.setTotalBookCount(createOrderRequest.getBookIdList().size());
        order.setTotalPrice(calculateTotalPrice(createOrderRequest));
        this.orderRepository.save(order);

        for (int bookId : createOrderRequest.getBookIdList()) {
            BookOrderRelation bookOrderRelation = new BookOrderRelation();
            bookOrderRelation.setOrder(order);
            bookOrderRelation.setBook(bookStockDown(bookId, createOrderRequest.getBookIdList().size()));
            this.bookOrderRelationRepository.save(bookOrderRelation);
        }
        return new SuccessResult(ResultCodes.ORDER_CREATED.getCode());
    }

    @Override
    public List<Order> getOrdersByCustomerId(int customerId, Pageable pageable) {

        List<Order> orderList = this.orderRepository.findByCustomerId(customerId, pageable);

        if(orderList.isEmpty()){
            throw new BusinessException(ResultCodes.ORDER_NOT_EXISTS.getCode());
        }
        return orderList;
    }

    @Override
    public DataResult<List<GetAllOrdersResponse>> getAllOrders(int id) {
        List<GetAllOrdersResponse> orderList = forResponseMapList(this.orderRepository.findById(id), GetAllOrdersResponse.class);
        return new SuccessDataResult<>(orderList);
    }

    @Override
    public DataResult<List<GetAllOrdersResponse>> getAllOrdersBetweenDates(LocalDateTime startDate, LocalDateTime endDate) {
        List<GetAllOrdersResponse> orderList = forResponseMapList(this.orderRepository.
                findByCreatedDateBetween(startDate,endDate), GetAllOrdersResponse.class);
        return new SuccessDataResult<>(orderList);
    }

    private double calculateTotalPrice(CreateOrderRequest createOrderRequest) {
        double totalPrice = 0;
        for (int bookId : createOrderRequest.getBookIdList()) {
            Book book = getBook(bookId);
            totalPrice = totalPrice + book.getPrice();
        }
        return totalPrice;
    }

    private Book bookStockDown(int id, int orderCount){
        Book book = getBook(id);
        if(book.getStock() < orderCount){
            throw new BusinessException(ResultCodes.BOOK_STOCK_ERROR.getCode());
        }else{
            bookService.updateStock(new UpdateBookStockRequest(book.getId(), book.getStock()-1));
        }
        return book;
    }

    private Book getBook(int id) {
        Book existedBook = this.modelMapperService.forResponseMap(bookService.getBookById(id).getData(), Book.class);
        if(existedBook == null){
            throw new BusinessException(ResultCodes.BOOK_NOT_EXISTS.getCode());
        }
        return existedBook;
    }

    private Customer getCustomer(int id) {
        Customer existedCustomer =  this.modelMapperService.forResponseMap(customerService.getCustomerById(id).getData(), Customer.class);
        if(existedCustomer == null){
            throw new BusinessException(ResultCodes.CUSTOMER_NOT_EXISTS.getCode());
        }
        return existedCustomer;
    }

}
