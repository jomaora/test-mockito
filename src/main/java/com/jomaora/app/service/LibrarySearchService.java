package com.jomaora.app.service;

import com.jomaora.app.model.Book;

/**
 *
 * @author joan
 */
public interface LibrarySearchService {
    
    public void addBook(Book book) throws IllegalStateException;
    
}
