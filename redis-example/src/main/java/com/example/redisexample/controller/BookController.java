package com.example.redisexample.controller;

import com.example.redisexample.model.Book;
import com.example.redisexample.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/save")
    public Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @PutMapping("/update")
    public Book updateBook(@RequestBody Book book) {
        return bookService.updateBook(book);
    }

    @GetMapping("/{name}")
    public Book getBook(@PathVariable String name) {
        return bookService.getBook(name);
    }

    @DeleteMapping("/{name}/{id}")
    public String deleteBook(@PathVariable String name, @PathVariable Long id) {
        return bookService.deleteBook(name, id);
    }
}
