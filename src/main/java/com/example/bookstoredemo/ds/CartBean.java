package com.example.bookstoredemo.ds;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SessionScope
@Component
public class CartBean {

    private Set<BookDto> bookDtoList=
            new HashSet<>();

    public void addToCart(BookDto bookDto){
        bookDtoList.add(bookDto);
    }
    public Set<BookDto> listAllCarts(){
        return this.bookDtoList;
    }
    public void clearCart(){
        this.bookDtoList.clear();
    }
    public int cartSize(){
        return bookDtoList.size();
    }

    public void removeBook(BookDto bookDto){
        bookDtoList.remove(bookDto);
    }
}
