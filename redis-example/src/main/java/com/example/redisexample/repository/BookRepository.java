package com.example.redisexample.repository;

import com.example.redisexample.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface BookRepository extends JpaRepository<Book, Long> {
    
    @Query("SELECT c FROM Book AS c WHERE c.name=?1")
    Book findByName(String name);

    @Query("delete from Book b where b.name=:name")
    void deleteByName(@Param("name") String name);
}
