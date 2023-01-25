package com.example.bookstoredemo.controller;

import com.example.bookstoredemo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("cart")
public class CartController {

    @Autowired
    private CartService cartService;
    @GetMapping("/add-to-cart")
    public String addToCart(@RequestParam("id") int id){
        cartService.addToCart(id);
        return "redirect:/user/book?id="+id;
    }

}
