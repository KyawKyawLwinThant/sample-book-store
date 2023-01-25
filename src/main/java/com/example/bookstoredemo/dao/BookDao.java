package com.example.bookstoredemo.dao;

import com.example.bookstoredemo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookDao extends JpaRepository<Book,Integer> {
}
