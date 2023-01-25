package com.example.bookstoredemo.dao;

import com.example.bookstoredemo.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorDao extends JpaRepository<Author,Integer> {
}
