package com.example.restbookshop.exceptions;

@SuppressWarnings("serial")
public class SaleNotFoundException extends RuntimeException{
	public SaleNotFoundException(Long id){
		super("Could not find the Sale" + id);
	}
}
