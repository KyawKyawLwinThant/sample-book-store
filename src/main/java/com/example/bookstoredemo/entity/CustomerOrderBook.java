package com.example.bookstoredemo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;

import java.time.LocalDate;
import java.util.*;

@Entity
@Getter
@Setter
public class CustomerOrderBook extends IdClass{

    private String orderCode;
    @ManyToOne
    private Customer customer;

    private LocalDate orderDate;

    @OneToMany(mappedBy = "customerOrderBook",cascade = CascadeType.PERSIST)
    private List<OrderBook> orderBooks=
            new ArrayList<>();

    public void addOrderBook(OrderBook orderBook){
        orderBook.setCustomerOrderBook(this);
        orderBooks.add(orderBook);
    }

}
