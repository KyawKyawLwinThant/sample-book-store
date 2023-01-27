package com.example.bookstoredemo.service;

import com.example.bookstoredemo.dao.CustomerDao;
import com.example.bookstoredemo.ds.SecurityCustomer;
import com.example.bookstoredemo.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerUserDetailsService implements UserDetailsService {
    @Autowired
    private CustomerDao customerDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return customerDao.findCustomerByUsername(username)
                .map(SecurityCustomer::new)
                .orElseThrow(()->new UsernameNotFoundException("Error!"));

    }
}
