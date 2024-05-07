package com.edig.productsapi.exceptions;

public class CategoryNotExistsException extends RuntimeException{
    public CategoryNotExistsException(String message) {
        super(message);
    }
}
