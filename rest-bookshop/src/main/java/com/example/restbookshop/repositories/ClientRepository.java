package com.example.restbookshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.restbookshop.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{
	
}
