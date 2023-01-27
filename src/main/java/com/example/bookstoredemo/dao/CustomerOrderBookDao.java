package com.example.bookstoredemo.dao;

import com.example.bookstoredemo.entity.CustomerOrderBook;
import com.example.bookstoredemo.entity.OrderBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerOrderBookDao
        extends JpaRepository<CustomerOrderBook,Integer> {


    @Query("""
    select o from OrderBook o where o.customerOrderBook.customer.username =:username
    """)
    List<OrderBook> findOrderBookByCustomerName(String username);
}
