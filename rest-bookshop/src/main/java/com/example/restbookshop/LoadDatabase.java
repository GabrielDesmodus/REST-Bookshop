package com.example.restbookshop;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.restbookshop.entities.Book;
import com.example.restbookshop.entities.Client;
import com.example.restbookshop.entities.Sale;
import com.example.restbookshop.repositories.BookRepository;
import com.example.restbookshop.repositories.ClientRepository;
import com.example.restbookshop.repositories.SalesRepository;

@Configuration
public class LoadDatabase {
	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
	
	@Bean
	CommandLineRunner initDatabase(ClientRepository clientrepo, BookRepository bookrepo, SalesRepository salesrepo) {
		return args -> {
			log.info("Preloading" + clientrepo.save(new Client("569.888.698-03","John Johnatan",LocalDate.of(1986, 10, 3))));
			log.info("Preloading" + bookrepo.save(new Book("Call of Cthulu",1432,"Horror","H. P. Lovecraft")));
			log.info("Preloading" + salesrepo.save
						(new Sale(
								bookrepo.save(new Book("Whispers in the Dark",1432,"Horror","H. P. Lovecraft")), 
								clientrepo.save(new Client("509.688.698-04","Maria Hill",LocalDate.of(1990, 10, 3)))
								)
						)
					); //Inserting a sale relationship in the database and at the same time inserting a book and a client to form the relationship
		};
	}
}
