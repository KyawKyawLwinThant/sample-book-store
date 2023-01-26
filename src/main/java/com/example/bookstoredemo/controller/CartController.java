package com.example.bookstoredemo.controller;

import com.example.bookstoredemo.ds.BookDto;
import com.example.bookstoredemo.entity.Book;
import com.example.bookstoredemo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/add-to-cart")
    public String addToCart(@RequestParam("id") int id) {
        cartService.addToCart(id);
        return "redirect:/user/book?id=" + id;
    }

    @GetMapping("/clear")
    public String clearCart(){
        cartService.clearCart();
        return "redirect:/cart/view";
    }

    @GetMapping("/delete")
    public String removeFromCart(@RequestParam("id") int id) {
        cartService.removeFromCart(findBookDtoById(id));
        return "redirect:/cart/view";
    }

    private BookDto findBookDtoById(int id) {
        return cartService.listCart()
                .stream()
                .filter(b -> b.getId() == id)
                .findFirst()
                .get();
    }


    @GetMapping("/view")
    public String viewCart(Model model) {
        model.addAttribute("carts", cartService.listCart());
        return "cart-view";
    }

}
