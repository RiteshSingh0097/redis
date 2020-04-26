package com.example.demo.service.impl;

import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.BookService;
import com.example.demo.util.RedisUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public Book addBook(Book book) {
        try {
            Book book1 = redisUtil.storeInCache(book.getName(), book, true);
            if (Objects.nonNull(book1)) {
                log.warn("redis not working");
                book = bookRepository.save(book);
            }
        } catch (Exception e) {
            log.warn("redis not working");
            book = bookRepository.save(book);
        }
        return book;
    }

    @Override
    public Book updateBook(Book book) {
        log.info("Updating the details");
        try {
            String redisStore = redisUtil.storeInCache(book.getName(), book.toString(), true);
            if (Objects.nonNull(redisStore)) {
                log.warn("redis not working");
                book = bookRepository.save(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.warn("redis not working");
            book = bookRepository.save(book);
        }
        return book;
    }

    @Override
    public Book getBook(String name) {
        log.info("fetching from database :: {} ", name);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Book book = null;
        try {
            Object string = redisUtil.getValueFromCache(name, true, true);
            String str = gson.toJson(string);
            book = gson.fromJson(str, Book.class);
        } catch (Exception e) {
            log.warn("REDIS_NOT_WORKING", e);
            return bookRepository.findByName(name);
        }
        return book;
    }

    @Override
    public String deleteBook(String name, Long id) {
        log.info("Deleted book :: " + name);
        bookRepository.deleteById(id);
        return "Deleted";
    }
}
