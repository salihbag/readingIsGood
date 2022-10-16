package com.caseStudy.readingIsGood.domain.entities;

import com.caseStudy.readingIsGood.common.enums.CustomerStates;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="customers")
public class Customer {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private int id;

        @Column(name= "state")
        private CustomerStates state;

        @Column(name = "email")
        private String email;

        @Column(name = "password")
        private String password;

        @Column(name = "firstName")
        private String firstName;

        @Column(name = "lastName")
        private String lastName;

        @Column(name = "phone")
        private String phone;

        @Column(name = "address")
        private String address;

        @OneToMany(mappedBy = "customer")
        private List<Order> orders;


}
