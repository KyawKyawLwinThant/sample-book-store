package com.example.bookstoredemo.controller;

import com.example.bookstoredemo.dao.BookDao;
import com.example.bookstoredemo.ds.BookDto;
import com.example.bookstoredemo.entity.Book;
import com.example.bookstoredemo.service.CartService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller("user_book_controller")
@RequestMapping("/user")
public class BookController {
    @Autowired
    private BookDao bookDao;
    @Autowired
    private CartService cartService;



    private List<Book> books;

    @PostConstruct
    public void init() {
        books = bookDao.findAll();
    }


    @GetMapping("/own-library")
    public String showAllUserBooks(Authentication authentication,Model model){
        /*
        name
        orderbook list
         */
        if(authentication!=null) {
            String name = authentication.getName();
            model.addAttribute("name", name);
            model.addAttribute("books"
                    , cartService.listOrderBookByUserName(name));
        }
        else {
            throw new RuntimeException("Not Login Error");
        }
        return "own-library";
    }

    @GetMapping("/book/all")
    public String listAllBooks(Model model
            , @ModelAttribute("booksAll") List<Book> books) {
        model.addAttribute("books", books);
        return "show-all-books";
    }

    @GetMapping("/book")
    public String bookDetail(@RequestParam("id") int id, Model model,
                             @ModelAttribute("booksAll") List<Book> books) {
        Book book = books.stream()
                .filter(b -> b.getId() == id)
                .findFirst()
                .get();
        model.addAttribute("book"
                , book);

        return "detail-book";
    }



    @ModelAttribute("carts")
    public Set<BookDto> listAllCart() {
        return cartService.listCart();
    }

    @ModelAttribute("booksAll")
    public List<Book> listAllBooks() {
        return books;
    }

}
