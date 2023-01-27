package com.example.bookstoredemo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OrderBook extends IdClass{

    private int quantity;
    private double price;
    private String title;
    private String authorName;
    public OrderBook(){

    }

    public OrderBook(int quantity, double price, String title, String authorName) {
        this.quantity = quantity;
        this.price = price;
        this.title = title;
        this.authorName = authorName;
    }

    @ManyToOne
    private CustomerOrderBook customerOrderBook;

}
