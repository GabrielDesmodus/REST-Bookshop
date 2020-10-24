package com.example.restbookshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.restbookshop.entities.Sale;

public interface SalesRepository extends JpaRepository<Sale, Long> {
	
}
