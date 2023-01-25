package com.example.bookstoredemo.admin.controller;

import com.example.bookstoredemo.dao.CategoryDao;
import com.example.bookstoredemo.entity.Category;
import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class CategoryController {
    @Autowired
    private CategoryDao categoryDao;

    @GetMapping("category-form")
    public String categoryForm(Model model){
        model.addAttribute("category",new Category());
        return "admin/category-form";
    }
    @PostMapping("/category-form")
    public String saveCategory(@Valid Category category, BindingResult result){
        if(result.hasErrors()){
            return "admin/category-form";
        }
        categoryDao.save(category);

        return "redirect:/admin/category/all";

    }
    @GetMapping("/category/all")
    public String listAllCategory(Model model){
        model.addAttribute("categories",categoryDao.findAll());
        return "admin/category-list";
    }
}
