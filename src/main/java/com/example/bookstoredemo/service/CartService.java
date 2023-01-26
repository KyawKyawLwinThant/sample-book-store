package com.example.bookstoredemo.service;

import com.example.bookstoredemo.dao.BookDao;
import com.example.bookstoredemo.ds.BookDto;
import com.example.bookstoredemo.ds.CartBean;
import com.example.bookstoredemo.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CartService {
    @Autowired
    private CartBean cartBean;
    @Autowired
    private BookDao bookDao;

    public void addToCart(int id) {
        cartBean.addToCart(toDto(bookDao.findById(id).get()));
    }
    public void removeFromCart(BookDto bookDto){
        cartBean.removeBook(bookDto);
    }

    public void clearCart(){
        cartBean.clearCart();
    }

    public Set<BookDto> listCart(){
        return cartBean.listAllCarts();
    }

    public BookDto toDto(Book book){
        return new BookDto(
                book.getId(),
                book.getTitle(),
                book.getYearPublished(),
                book.getPublisher(),
                book.getPrice(),
                book.getQuantity(),
                book.getGenre(),
                book.getImgUrl(),
                book.getDescription(),
                book.getCategory(),
                book.getAuthor()
        );
    }
}
