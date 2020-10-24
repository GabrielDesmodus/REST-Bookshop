package com.example.restbookshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.restbookshop.entities.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
	
}
