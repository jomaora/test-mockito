package com.jomaora.app.service;

import com.jomaora.app.dao.BookDAO;
import com.jomaora.app.model.Book;
import java.util.List;

/**
 * Service to be tested
 * @author joan
 */
public class LibrarySearchServiceImpl implements LibrarySearchService{

    private BookDAO bookDAO;
    
    @Override
    public void addBook(Book book) throws IllegalStateException {
        List<Book> existingBooks = bookDAO.findBooksByTitle(book.getTitle().split(" "));
        
        if( existingBooks.isEmpty() ){
            bookDAO.store(book);
        } else {
            for( Book tempBook : existingBooks ) {
                if( tempBook.getAuthors().containsAll(book.getAuthors()) ) {
                    throw new IllegalStateException("Already existing book");
                }
            }
            bookDAO.store(book);
        }
    }

    public void setBookDAO(BookDAO bookDAO){
        this.bookDAO = bookDAO;
    }

}
