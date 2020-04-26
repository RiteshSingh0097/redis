package com.example.demo.service;

import com.example.demo.model.Book;

public interface BookService {

    Book addBook(Book book);

    Book updateBook(Book book);

    Book getBook(String name);

    String deleteBook(String name, Long id);

}
