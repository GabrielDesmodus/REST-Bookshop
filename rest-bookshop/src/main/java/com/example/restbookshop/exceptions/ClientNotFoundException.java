package com.example.restbookshop.exceptions;

@SuppressWarnings("serial")
public class ClientNotFoundException extends RuntimeException{
	public ClientNotFoundException(Long id){
		super("Could not find the client" + id);
	}
}
