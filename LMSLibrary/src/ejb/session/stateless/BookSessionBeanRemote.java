/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.Book;
import exception.BookNotFoundException;
import javax.ejb.Remote;

/**
 *
 * @author Uni
 */
@Remote
public interface BookSessionBeanRemote {

    Book retrieveBookwithISBN(String isbn) throws BookNotFoundException;

}
