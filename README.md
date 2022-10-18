# ReadingIsGood Book Retail Service
ReadingIsGood is an online books retail firm which operates only on the Internet. Main
target of ReadingIsGood is to deliver books from its one centralized warehouse to their
customers within the same day. That is why stock consistency is the first priority for their
vision operations.

-------------------------------------

### **Tech Stack**

* Java 17
* Spring-Boot
* PostgreSQL
* Maven
* Docker
* Lombok
* Mockito

-------------------------------------

### **File Structure **

```
readingIsGood/
 │
 ├── src/main/java/
 │   └── readingIsGood
 │       ├── api.controllers
 │       │   ├── BooksController.java
 │       │   ├── CustomersController.java
 │       │   ├── JwtAuthenticationController.java
 │       │   ├── OrdersController.java
 │       │   └── StatisticsController.java
 │       │
 │       ├── common
 │       │   ├── enums
 │       │   │    ├── BookStates.java
 │       │   │    ├── CustomerStates.java
 │       │   │    ├── OrderStates.java
 │       │   │    └── ResultCodes.java
 │       │   │ 
 │       │   ├── exceptions
 │       │   │    ├── BusinessException.java
 │       │   │    └── GlobalExceptionHandler.java
 │       │   │ 
 │       │   └── utilities
 │       │        ├── mapping
 │       │        │    ├── ModelMapperService.java
 │       │        │    └── ModelMapperServiceImpl.java
 │       │        │
 │       │        ├── results
 │       │        │    ├── DataResult.java
 │       │        │    ├── ErrorDataResult.java
 │       │        │    ├── ErrorResult.java
 │       │        │    ├── Result.java
 │       │        │    ├── SuccessDataResult.java
 │       │        │    └── SuccessResult.java
 │       │        │
 │       │        └── AppUtils.java
 │       │        
 │       ├── config
 │       │   ├── JwtAuthenticationEntryPoint.java
 │       │   ├── JwtRequestFilter.java
 │       │   ├── JwtTokenUtil.java
 │       │   └── WebSecurityConfig.java
 │       │
 │       ├── core
 │       │   ├── requests
 │       │   │    ├── create
 │       │   │    │    ├── CreateBookRequest.java
 │       │   │    │    ├── CreateCustomerRequest.java
 │       │   │    │    └── CreateOrderRequest.java
 │       │   │    │
 │       │   │    ├── update
 │       │   │    │    └── UpdateBookStockRequest.java
 │       │   │    │
 │       │   │    └── JwtRequest.java
 │       │   │    
 │       │   ├── responses
 │       │   │    ├── GetAllBooksResponse.java
 │       │   │    ├── GetAllCustomersResponse.java
 │       │   │    ├── GetAllOrdersResponse.java
 │       │   │    ├── GetAllStatisticsResponse.java
 │       │   │    └── JwtResponse.java
 │       │   │ 
 │       │   └── services
 │       │        ├── abstracts
 │       │        │    ├── BaseService.java
 │       │        │    ├── BookService.java
 │       │        │    ├── CustomerService.java
 │       │        │    ├── OrderService.java
 │       │        │    └── StatisticService.java
 │       │        │
 │       │        └── concretes
 │       │             ├── BookServiceImpl.java
 │       │             ├── CustomerServiceImpl.java
 │       │             ├── JwtUserDetailsService.java
 │       │             ├── OrderServiceImpl.java
 │       │             └── StatisticServiceImpl.java 
 │       │        
 │       ├── domain
 │       │   ├── entities
 │       │   │    ├── Book.java
 │       │   │    ├── BookOrderRelation.java
 │       │   │    ├── Customer.java
 │       │   │    └── Order.java
 │       │   │ 
 │       │   └── repositories
 │       │        ├── BookOrderRelationRepository.java
 │       │        ├── BookRepository.java
 │       │        ├── CustomerRepository.java
 │       │        └── OrderRepository.java
 │       │
 │       └── ReadingIsGoodApplication.java
 │
 ├── src/main/resources/
 │   └── application.properties
 │
 └── pom.xml
```
-------------------------------------

### **How To Set Up**

#### **Via IDE**

* Create a Database named `casestudydb` in pgAdmin.

* Import code in any IDE of choice.

* Go to `application.properties` and change `spring.datasource.username` and `spring.datasource.password` with yours.

* Go to `ReadingIsGoodApplication.java` and run main method.

* Open Postman and Import collection.

* Send request to `localhost:8080/api/authentication` body with `{"username":"salih", "password":"password"}`.

* Set authorize of requests with that "jwtToken", tokens are valid for 5 days.

![alt text](https://github.com/salihbag/readingIsGood/blob/main/swagger.png?raw=true)
