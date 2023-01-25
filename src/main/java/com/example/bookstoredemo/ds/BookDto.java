package com.example.bookstoredemo.ds;

import com.example.bookstoredemo.entity.Author;
import com.example.bookstoredemo.entity.Category;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor

public class BookDto {
    private int id;
    private String title;
    private LocalDate yearPublished;
    private String publisher;
    private double price;
    private int quantity;
    private String genre;
    private String imgUrl;
    private String description;
    private Category category;
    private Author author;
    public BookDto(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookDto bookDto)) return false;
        return id == bookDto.id && title.equals(bookDto.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }
}
