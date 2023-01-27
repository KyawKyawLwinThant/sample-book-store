package com.example.bookstoredemo.service;

import com.example.bookstoredemo.dao.BookDao;
import com.example.bookstoredemo.dao.CustomerDao;
import com.example.bookstoredemo.dao.CustomerOrderBookDao;
import com.example.bookstoredemo.dao.RoleDao;
import com.example.bookstoredemo.ds.BookDto;
import com.example.bookstoredemo.ds.CartBean;
import com.example.bookstoredemo.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

@Service
public class CartService {
    @Autowired
    private CartBean cartBean;
    @Autowired
    private BookDao bookDao;
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private CustomerOrderBookDao customerOrderBookDao;

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

    public List<OrderBook> listOrderBookByUserName(String name){
        return customerOrderBookDao.findOrderBookByCustomerName(name);
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

    @Transactional
    public void register(Customer customer, Set<BookDto> carts) {
            Customer managedCustomer = getCustomer(customer);

            CustomerOrderBook customerOrderBook =
                    new CustomerOrderBook();
            managedCustomer.addCustomerOrderBook(customerOrderBook);

            customerOrderBook.setOrderDate(LocalDate.now());
            customerOrderBook.setOrderCode(generateCode());

            for(BookDto bookDto:carts){
                customerOrderBook.addOrderBook(toOrderBook(bookDto));
            }

            customerOrderBookDao.save(customerOrderBook);
            clearCart();
        }
        private OrderBook toOrderBook(BookDto bookDto){
            return  new OrderBook(
                    bookDto.getOrderBookQuantity(),
                    bookDto.getPrice(),
                    bookDto.getTitle(),
                    bookDto.getAuthor().getName()
            );
        }
        private String generateCode(){
            //99+10
            int code=new Random().nextInt(99)+ 10;
            return "UB-"+code;
        }




    private Customer getCustomer(Customer customer) {
        Optional<Customer> customer1=customerDao
                .findCustomerByUsername(customer.getUsername());
        if(!customer1.isPresent()) {
            Role customerRole = roleDao.findRoleByRoleName("ROLE_USER")
                    .get();
            customer.addRole(customerRole);
            customer.setPassword(passwordEncoder
                    .encode(customer.getPassword()));
            Customer managedCustomer = customerDao.saveAndFlush(customer);
            return managedCustomer;
        }
        else {
            return customer1.get();
        }

    }

    //Book(int id,String title, LocalDate yearPublished, String publisher, double price, int quantity, String genre, String imgUrl, String description, Category category, Author author) {
    public Book toEntity(BookDto bookDto){
        return new Book(
                bookDto.getId(),
                bookDto.getTitle(),
                bookDto.getYearPublished(),
                bookDto.getPublisher(),
                bookDto.getPrice(),
                bookDto.getQuantity(),
                bookDto.getGenre(),
                bookDto.getImgUrl(),
                bookDto.getDescription(),
                bookDto.getCategory(),
                bookDto.getAuthor()
        );
    }
}
