package com.jomaora.app.dao;

import com.jomaora.app.model.Book;
import java.util.List;

/**
 * Unexisting implementation of this class, so we're gonna mock
 * @author joan
 */
public interface BookDAO {

    public void store(Book book);
    
    public List<Book> findBooksByTitle(String ... keywords);
    
}
