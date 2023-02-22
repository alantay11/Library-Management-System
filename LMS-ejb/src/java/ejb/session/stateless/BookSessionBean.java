/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.Book;
import exception.BookNotFoundException;
import exception.EntityManagerException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

/**
 *
 * @author Uni
 */
@Stateless
public class BookSessionBean implements BookSessionBeanLocal {

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

    @Override
    public Book retrieveBookwithISBN(String isbn) throws BookNotFoundException {
        Query query = em.createQuery("SELECT b FROM Book b WHERE b.isbn = :isbn");
        query.setParameter("isbn", isbn);

        try {
            Book book = (Book) query.getSingleResult();
            book.getLending().size();
            return book;
        } catch (NoResultException | NonUniqueResultException ex) {
            throw new BookNotFoundException("Book with ISBN: " + isbn + " does not exist!");
        }
    }

    @Override
    public List<Book> retrieveAllBooks() {
        return em.createQuery("SELECT b FROM Book b").getResultList();
    }

}
