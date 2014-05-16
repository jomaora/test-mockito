package com.jomaora.app.utils;

import com.jomaora.app.model.Author;
import com.jomaora.app.model.Book;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MyMapsTest {

    private Book book1;
    private Author author1;
    private Book book2;
    private Author author2;

    @BeforeMethod
    public void initDataSet(){
        author1 = new Author("Antoine de Saint-Exupéry.");
        author2 = new Author("Gabirel Garcia Marquez");
        book1 = new Book("Le Petit Prince", author1);
        book2 = new Book("El amor en los tiempos del colera", author2);
    }

    @Test
    public void testMyMap() throws Exception {

        Map hashMap = new HashMap<Author, Book>();
        hashMap.put(author1, book1);
        hashMap.put(author2, book2);

        MyMaps<Author, Book> createdMap = MyMaps.create(hashMap);

        MyMaps<Author, Book> privateConstructorMap = org.powermock.reflect.Whitebox.invokeConstructor(MyMaps.class,
                new Class<?>[]{Set.class, Collection.class},
                new Object[]{hashMap.keySet(), hashMap.values()});

        Assert.assertEquals( createdMap.getSize(), privateConstructorMap.getSize() );
        Assert.assertEquals( createdMap.getKey(book1), author1 );
        Assert.assertEquals( createdMap.getKey(book1), privateConstructorMap.getKey(book1));
        Assert.assertEquals( privateConstructorMap.getValue(author2), book2 );
        Assert.assertEquals( privateConstructorMap.getValue(author2), createdMap.getValue(author2));
    }


}
