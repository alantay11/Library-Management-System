/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.Book;
import exception.BookNotFoundException;
import exception.EntityManagerException;
import javax.ejb.Local;

/**
 *
 * @author Uni
 */
@Local
public interface BookSessionBeanLocal {

    public Book createBook(Book book) throws EntityManagerException;

    public Book retrieveBookwithISBN(String isbn) throws BookNotFoundException;
    
}
