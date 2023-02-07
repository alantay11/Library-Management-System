/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.Book;
import entity.LendAndReturn;
import entity.Member;
import exception.BookNotFoundException;
import exception.EntityManagerException;
import exception.MemberNotFoundException;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

/**
 *
 * @author Uni
 */
@Stateless
public class LendAndReturnSessionBean implements LendAndReturnSessionBeanRemote, LendAndReturnSessionBeanLocal {

    @EJB
    private BookSessionBeanLocal bookSessionBean;

    @EJB
    private MemberSessionBeanLocal memberSessionBean;

    @PersistenceContext(unitName = "LMS-ejbPU")
    private EntityManager em;

    // Use Case 3
    @Override
    public LendAndReturn lendBook(LendAndReturn lendAndReturn, String memberIDNum, String isbn) throws EntityManagerException {
        try {
            Member member = memberSessionBean.retrieveMemberwithID(memberIDNum);
            Book book = bookSessionBean.retrieveBookwithISBN(isbn);

            lendAndReturn.setMember(member);
            lendAndReturn.setBook(book);

            member.getLending().add(lendAndReturn);
            book.getLending().add(lendAndReturn);
            em.persist(lendAndReturn);
            em.flush();
            return lendAndReturn;
        } catch (MemberNotFoundException | BookNotFoundException | PersistenceException ex) {
            throw new EntityManagerException(ex.getMessage());
        }
    }
}
