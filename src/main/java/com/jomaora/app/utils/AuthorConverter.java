package com.jomaora.app.utils;

import com.google.common.collect.Lists;
import com.jomaora.app.model.Author;
import java.util.List;

/**
 *
 * @author joan
 */
public class AuthorConverter {
    
    public static Author createAuthor(String authorName){
        if ( !authorName.isEmpty() ) {
            return new Author(authorName);
        }
        return null;
    }
    
    public static List<Author> createAuthor(String ... authorNames){
        if( authorNames != null ) {
            List<Author> authors = Lists.newArrayList();
            for ( String authorName : authorNames ) {
                authors.add( new Author(authorName) );
            }
            return authors;
        }
        return null;
    }
    
}
