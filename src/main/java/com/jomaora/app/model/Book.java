package com.jomaora.app.model;

import com.google.common.collect.Lists;
import com.jomaora.app.utils.AuthorConverter;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author joan
 */
public class Book {
    
    private String title;
    private List<Author> authors;

    public Book(){}
    
    public Book(String title, String ... authorNames){
        this.title = title;
        this.authors = AuthorConverter.createAuthor(authorNames);
    }
    
    public Book(String title, Author ... authors){
        this.title = title;
        if ( authors != null ){
            this.authors = Lists.newArrayList();
            this.authors.addAll(Arrays.asList(authors));
        }
    }
    
    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the authors
     */
    public List<Author> getAuthors() {
        return authors;
    }

    /**
     * @param authors the authors to set
     */
    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
    
}
