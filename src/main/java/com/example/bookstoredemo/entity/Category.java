package com.example.bookstoredemo.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Category extends IdClass{
    @Column(name = "category_name")
    @NotBlank(message = "Name cannot be blank!")
    @NotEmpty(message = "Name cannot be empty!")
    private String categoryName;

    @OneToMany(mappedBy = "category")
    private List<Book> bookList=
            new ArrayList<>();

    public void addBook(Book book){
        book.setCategory(this);
        bookList.add(book);
    }
}









