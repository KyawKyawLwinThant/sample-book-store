package com.example.bookstoredemo.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Address {

    private String phoneNumber;
    private String streetName;
    private String country;
}


