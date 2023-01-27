package com.example.bookstoredemo.dao;

import com.example.bookstoredemo.entity.CustomerOrderBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerOrderBookDao
        extends JpaRepository<CustomerOrderBook,Integer> {
}
