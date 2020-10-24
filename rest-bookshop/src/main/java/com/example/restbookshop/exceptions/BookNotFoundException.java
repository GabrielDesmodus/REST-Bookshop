package com.example.restbookshop.exceptions;

@SuppressWarnings("serial")
public class BookNotFoundException extends RuntimeException{
	public BookNotFoundException(Long id){
		super("Could not find Book" + id);
	}
}
