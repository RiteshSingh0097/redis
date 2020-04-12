package com.example.redisexample.service;

import com.example.redisexample.model.Book;

public interface BookService {

    Book addBook(Book book);

    Book updateBook(Book book);

    Book getBook(String name);

    String deleteBook(String name, Long id);
}
