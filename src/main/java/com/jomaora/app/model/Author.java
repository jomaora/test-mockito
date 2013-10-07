package com.jomaora.app.model;

/**
 *
 * @author joan
 */
public class Author {
    
    private String name;
    
    public Author() {}
    
    public Author(String name){
        this.name = name;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
}
