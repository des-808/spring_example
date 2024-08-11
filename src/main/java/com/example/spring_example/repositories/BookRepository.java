package com.example.spring_example.repositories;

import com.example.spring_example.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}

