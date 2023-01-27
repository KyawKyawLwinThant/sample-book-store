package com.example.bookstoredemo.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Book extends IdClass{

    private String title;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "year_published")
    private LocalDate yearPublished;
    private String publisher;
    private double price;
    private int quantity;
    private String genre;
    private String imgUrl;
    private String description;



    @ManyToOne
    private Category category;
    @ManyToOne
    private Author author;


    public Book(){

    }

    public Book(int id,String title, LocalDate yearPublished, String publisher, double price, int quantity, String genre, String imgUrl, String description, Category category, Author author) {
        this.title = title;
        this.yearPublished = yearPublished;
        this.publisher = publisher;
        this.price = price;
        this.quantity = quantity;
        this.genre = genre;
        this.imgUrl = imgUrl;
        this.description = description;
        this.category = category;
        this.author = author;
        setId(id);
    }


}
