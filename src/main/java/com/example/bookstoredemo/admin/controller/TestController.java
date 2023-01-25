package com.example.bookstoredemo.admin.controller;

import com.example.bookstoredemo.dao.BookDao;
import com.example.bookstoredemo.entity.Book;
import jakarta.validation.constraints.Digits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class TestController {
    @Autowired
    private BookDao bookDao;
    @GetMapping(value = {"/admin",})
    public String layoutTest(){
        return "forward:/admin/book/all";
    }
    @GetMapping(value = {"/","/home"})
    public String index(){
        return "index";
    }
    @ModelAttribute("books")
    public List<Book> listBooks(){
        return bookDao.findAll();
    }


}
