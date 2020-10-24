package com.example.restbookshop.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restbookshop.entities.Book;
import com.example.restbookshop.entities.Client;
import com.example.restbookshop.entities.Sale;
import com.example.restbookshop.exceptions.BookNotFoundException;
import com.example.restbookshop.exceptions.ClientNotFoundException;
import com.example.restbookshop.exceptions.SaleNotFoundException;
import com.example.restbookshop.repositories.BookRepository;
import com.example.restbookshop.repositories.ClientRepository;
import com.example.restbookshop.repositories.SalesRepository;

@RestController
@RequestMapping({"/bookshop"})
public class BookShopController {
	
	private BookRepository bookrepo;
	private ClientRepository clientrepo;
	private SalesRepository salesrepo;
	
	BookShopController(BookRepository bookrepository, ClientRepository clientrepository, SalesRepository salesrepository){
		this.bookrepo = bookrepository;
		this.clientrepo = clientrepository;
		this.salesrepo = salesrepository;
		
	}
	
	@GetMapping("/clients")
	List<Client> allClients() {
	    return clientrepo.findAll();
	}
	
	@PostMapping("/clients")
	Client newClient(@RequestBody Client newClient) {
		return clientrepo.save(newClient);
	}
	
	@GetMapping("/sales")
	List<Sale> allSales(){
		return salesrepo.findAll();
	}
	
	@PostMapping("/sales")
	Sale newSale(@RequestBody Sale newSale) {
		return salesrepo.save(newSale);
	}
	
	@GetMapping("/books")
	List<Book> allBooks(){
		return bookrepo.findAll();
	}
	
	@PostMapping("/books")
	Book newBook(@RequestBody Book newBook) {
		return bookrepo.save(newBook);
	}
	
	@GetMapping("/clients/{id}")
	Client clientById(@PathVariable Long id){
		return clientrepo.findById(id).orElseThrow(() -> new ClientNotFoundException(id));
	}
	
	@PutMapping("/clients/{id}")
	Client replaceClient(@RequestBody Client newClient, @PathVariable Long id) {
		return clientrepo.findById(id).map(client -> {
			client.setName(newClient.getName());
			client.setCpf(newClient.getCpf());
			client.setDateOfBirth(newClient.getDateOfBirth());
			return clientrepo.save(client);
		})
		.orElseGet(()-> {
			newClient.setId(id);
			return clientrepo.save(newClient);
		});
	}
	
	@DeleteMapping("/clients/{id}")
	void deleteClient(@PathVariable Long id) {
		clientrepo.deleteById(id);
	}
	
	@DeleteMapping("/clients/deleteall")
	void deleteAllClients() {
		clientrepo.deleteAll();
	}
	
	@GetMapping("/sales/{id}")
	Sale saleById(@PathVariable Long id) {
		return salesrepo.findById(id).orElseThrow(()->new SaleNotFoundException(id));
	}
	
	@PutMapping("/sales/{id}")
	Sale replaceSale(@RequestBody Sale newSale, @PathVariable Long id) {
		return salesrepo.findById(id).map(sale -> {
			sale.setBook(newSale.getBook());
			sale.setClient(newSale.getClient());
			sale.setSaleDate(newSale.getSaleDate());
			return salesrepo.save(sale);
		})
		.orElseGet(()-> {
			newSale.setId(id);
			return salesrepo.save(newSale);
		});
	}
	
	@DeleteMapping("/sales/{id}")
	void deleteSale(@PathVariable Long id) {
		salesrepo.deleteById(id);
	}
	
	@DeleteMapping("/sales/deleteall")
	void deleteAllSales() {
		salesrepo.deleteAll();
	}
	
	@GetMapping("/books/{id}")
	Book bookById(@PathVariable Long id) {
		return bookrepo.findById(id).orElseThrow(()->new BookNotFoundException(id));
	}
	
	@PutMapping("/books/{id}")
	Book replaceBook(@RequestBody Book newBook, @PathVariable Long id) {
		return bookrepo.findById(id).map(book -> {
			book.setAuthor(newBook.getAuthor());
			book.setGenre(newBook.getGenre());
			book.setName(newBook.getName());
			book.setYear(newBook.getYear());
			return bookrepo.save(book);
		}).orElseGet(()->{
			newBook.setId(id);
			return bookrepo.save(newBook);
		});
	}
	
	@DeleteMapping("/books/{id}")
	void deleteBook(@PathVariable Long id) {
		bookrepo.deleteById(id);
	}
	
	@DeleteMapping("/books/deleteall")
	void deleteAllBooks() {
		bookrepo.deleteAll();
	}
}
