package com.jomaora.app.service;

import com.jomaora.app.dao.BookDAO;
import com.jomaora.app.model.Book;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import com.google.common.collect.Lists;
import com.jomaora.app.model.Author;
import java.util.ArrayList;

import org.testng.annotations.*;

/**
 * We're gonna test the LibrarySearchService methods, mocking the BookDAO object
 * because there's no implementation of this class and we unknown how does it work
 * but we know the results that we have to see.
 * @author joan
 */
public class LibrarySearchServiceTest {
    
    LibrarySearchServiceImpl service = new LibrarySearchServiceImpl();
    
    @Mock
    private BookDAO bookDAO;
    
    private Book existingBook;
    
    private Author author1;
    
    private Book newBook;
    
    private Author author2;
    
    @BeforeClass
    public void init_Mockito_mocks(){
        MockitoAnnotations.initMocks(this);
        service.setBookDAO(bookDAO);
    }
    
    @BeforeMethod
    public void initDataSet(){
        author1 = new Author("Maximo Gorki");
        author1 = new Author("Pearl S. Buck");
        existingBook = new Book("La madre", author1);
        newBook = new Book("La madre", author2);
    }
    
    @AfterMethod
    public void reset_Mockito_mocks() {
        reset(bookDAO);
    }
    
    /**
     * Test default Scenario for addBook method. A new Book must be added with sucess.
     * 
     * Scenario:
     *  newBook is added with sucess
     * 
     * Given:
     *  - newBook : Book to save
     *  - bookDAO mocked to:
     *      return null when findBooksByTitle method call with "La madre" as parameter. There must be no book with this title.
     * 
     * Test:
     *  - service must call bookDAO's store method once.
     */
    @Test
    public void test_addBook_with_success() {
        //given
        when(bookDAO.findBooksByTitle(anyString())).thenReturn( new ArrayList<Book>() );
        
        //when
        service.addBook(newBook);
        
        //test
        verify(bookDAO, times(1)).store(newBook);
    }
    
    /**
     * Test scenario for addBook method when a new Book with an already existing Title but different author
     * is added with sucess.
     * 
     * Scenario:
     *  The newBook object is tried to be added. We get the list of Books with the same title and existingBook is 
     *  returned. Having that the authors are differents, newBook is saved with sucess.
     * 
     * Given:
     *  - existingBook : Already existing book
     * 
     *  - bookDAO mocked to:
     *      return null when findBooksByTitle method call with "La madre" as parameter. existingBook is returned.
     * 
     * Test:
     *  - service must call bookDAO's store method once.
     */
    @Test
    public void test_addBook_when_newBook_has_already_existing_title_but_different_Author_with_success() {
        //given
        when(bookDAO.findBooksByTitle("La", "madre")).thenReturn( Lists.newArrayList( existingBook ) );
 
        //when
        service.addBook(newBook);
        
        //test
        verify(bookDAO, times(1)).store(newBook);
    }
    
    /**
     * Test scenario for addBook method when an already existing Book tried to be added. 
     * An IllegalStateException must be caught.
     * 
     * Scenario:
     *  The alreadyExistingBook object is tried to be added. We get the list of Books with the same title 
     *  are returned. alreadyExistingBook has the same title and author as existingBook, so the
     *  exception is thrown.
     * 
     * Given:
     *  - existingBook : Already existing book
     * 
     *  - bookDAO mocked to:
     *      return null when findBooksByTitle method call with "La madre" as parameter. existingBook
     *      and newBook are returned.
     * 
     * Test:
     *  - IllegalStateException is caught.
     */
    @Test ( expectedExceptions= IllegalStateException.class )
    public void test_addBook_should_throw_IllegalStateException_when_newBook_has_already_exists() {
        //given
        when(bookDAO.findBooksByTitle("La", "madre")).thenReturn( Lists.newArrayList( existingBook, newBook ) );
 
        //when
        Book alreadyExistingBook = new Book("La madre", author1);
        service.addBook(alreadyExistingBook);
    }
}

