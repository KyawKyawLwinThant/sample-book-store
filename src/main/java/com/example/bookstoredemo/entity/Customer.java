package com.example.bookstoredemo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Entity
@Getter
@Setter
public class Customer extends IdClass{
    @Column(unique = true)
    private String username;
    private String password;
    @Embedded
    private Address address;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles=
            new HashSet<>();
    @OneToMany(mappedBy = "customer")
    private List<CustomerOrderBook>
        customerOrderBooks=new ArrayList<>();

    public void addRole(Role role){
        role.getCustomers().add(this);
        roles.add(role);
    }
    public void addCustomerOrderBook(CustomerOrderBook customerOrderBook){
        customerOrderBook.setCustomer(this);
        customerOrderBooks.add(customerOrderBook);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer customer)) return false;
        return username.equals(customer.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
