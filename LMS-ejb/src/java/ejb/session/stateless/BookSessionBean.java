/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.Book;
import exception.EntityManagerException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

/**
 *
 * @author Uni
 */
@Stateless
public class BookSessionBean implements BookSessionBeanRemote, BookSessionBeanLocal {

    @PersistenceContext(unitName = "LMS-ejbPU")
    private EntityManager em;

    @Override
    public Book createBook(Book book) throws EntityManagerException {
        try {
            em.persist(book);
            em.flush();
            return book;
        } catch (PersistenceException ex) {
            throw new EntityManagerException(ex.getMessage());
        }
    }

}
