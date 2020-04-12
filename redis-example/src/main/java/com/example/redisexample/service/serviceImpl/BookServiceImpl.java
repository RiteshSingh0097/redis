package com.example.redisexample.service.serviceImpl;

import com.example.redisexample.model.Book;
import com.example.redisexample.repository.BookRepository;
import com.example.redisexample.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book addBook(Book book) {
        book = bookRepository.save(book);
        log.info("adding book with id - {}", book.getId());
        return book;
    }

    @Override
    @CachePut(cacheNames = "books", key = "#book.name")
    public Book updateBook(Book book) {
        log.info("book update with id - {} ", book.getId());
        return bookRepository.save(book);
    }

    @Override
    @Cacheable(cacheNames = "books", key = "#name")
    public Book getBook(String name) {
        log.info("fetching from database :: {} ", name);
        return bookRepository.findByName(name);
    }

    @Override
    @CacheEvict(cacheNames = "books", key = "#name")
    public String deleteBook(String name, Long id) {
        log.info("Deleted book :: " + name);
        bookRepository.deleteById(id);
        return "Deleted";
    }
}
