package com.caseStudy.readingIsGood.domain.entities;

import com.caseStudy.readingIsGood.common.enums.OrderStates;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="orders")
public class Order {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private int id;

        @Column(name = "createdDate")
        private LocalDateTime createdDate;

        @Column(name= "state")
        private OrderStates state;

        @ManyToOne
        @JoinColumn(name="customer_id")
        private Customer customer;

        @Column(name = "totalPrice")
        private double totalPrice;

        @Column(name = "totalBookCount")
        private int totalBookCount;

        @OneToMany(mappedBy = "order")
        private List<BookOrderRelation> bookOrderRelationList;


}
