package com.example.bookstoredemo.admin.controller;

import com.example.bookstoredemo.dao.AuthorDao;
import com.example.bookstoredemo.entity.Author;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AuthorController {
    @Autowired
    private AuthorDao authorDao;

    @GetMapping("/author-form")
    public ModelAndView authorForm(){
        return new ModelAndView(
                "admin/author-form",
                "author",
                new Author()
        );
    }
    @PostMapping("/author-form")
    public String saveAuthor(@Valid Author author, BindingResult result){
        if(result.hasErrors()){
            return "admin/author-form";
        }
        authorDao.save(author);
        return "redirect:/admin/author-list";
    }
    @GetMapping("/author-list")
    public String listAllAuthor(Model model){
        model.addAttribute("authors",authorDao.findAll());
        return "admin/author-list";
    }



}
