package com.example.bookstoredemo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Author extends IdClass{
    @NotEmpty(message = "Name cannot be Empty!")
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Embedded
    private Address address;
    @OneToMany(mappedBy = "author")
    private List<Book> bookList=
            new ArrayList<>();

    public void addBook(Book book){
        book.setAuthor(this);
        bookList.add(book);
    }

}
