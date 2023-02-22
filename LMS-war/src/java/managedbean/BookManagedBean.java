/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import ejb.session.stateless.BookSessionBeanLocal;
import entity.Book;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Uni
 */
@Named(value = "bookManagedBean")
@RequestScoped
public class BookManagedBean {

    @EJB
    private BookSessionBeanLocal bookSessionBeanLocal;

    private List<Book> allBooks;
  
    public BookManagedBean() {
    }
    
    public void retrieveAllBooks() {
        this.allBooks = bookSessionBeanLocal.retrieveAllBooks();
    }

    public BookSessionBeanLocal getBookSessionBeanLocal() {
        return bookSessionBeanLocal;
    }

    public void setBookSessionBeanLocal(BookSessionBeanLocal bookSessionBeanLocal) {
        this.bookSessionBeanLocal = bookSessionBeanLocal;
    }

    public List<Book> getAllBooks() {
        return allBooks;
    }

    public void setAllBooks(List<Book> allBooks) {
        this.allBooks = allBooks;
    }

    
}
