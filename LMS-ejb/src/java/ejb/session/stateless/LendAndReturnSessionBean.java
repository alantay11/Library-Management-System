/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.Book;
import entity.LendAndReturn;
import entity.Member;
import exception.BookNotAvailableException;
import exception.EntityManagerException;
import exception.MemberNotFoundException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.ejb.EJB;
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
public class LendAndReturnSessionBean implements LendAndReturnSessionBeanLocal {

    @EJB
    private MemberSessionBeanLocal memberSessionBean;

    @PersistenceContext(unitName = "LMS-ejbPU")
    private EntityManager em;

    // Use Case 3
    @Override
    public LendAndReturn lendBook(String memberIDNum, long bookId) throws EntityManagerException, BookNotAvailableException {
        try {
            Member member = memberSessionBean.retrieveMemberwithID(memberIDNum);
            Book book = em.find(Book.class, bookId);
            LendAndReturn lendAndReturn = new LendAndReturn();

            if (book.isAvailable()) {
                lendAndReturn.setMember(member);
                lendAndReturn.setMemberId(member.getMemberId());
                lendAndReturn.setBook(book);
                lendAndReturn.setBookId(book.getBookId());
                lendAndReturn.setLendDate(new Date(System.currentTimeMillis()));
                book.setAvailable(false);

                em.persist(lendAndReturn);

                member.getLending().add(lendAndReturn);
                book.getLending().add(lendAndReturn);

                em.flush();
                return lendAndReturn;
            } else {
                throw new BookNotAvailableException();
            }

        } catch (MemberNotFoundException | PersistenceException ex) {
            throw new EntityManagerException(ex.getMessage());
        }
    }

    // Use Case 4
    @Override
    public BigDecimal calculateFine(long lendAndReturnId) {
        LendAndReturn lendAndReturn = em.find(LendAndReturn.class, lendAndReturnId);
        long diff = (new Date(System.currentTimeMillis())).getTime() - lendAndReturn.getLendDate().getTime();

        long daysDiff = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        System.out.println("Days= " + daysDiff);

        double fineAmt = (daysDiff > 14) ? (daysDiff - 14) * 0.50 : 0;
        BigDecimal fine = new BigDecimal(fineAmt);
        System.out.println(fine);
        return fine.setScale(2);
    }

    // Use Case 5
    @Override
    public LendAndReturn returnBook(long lendAndReturnId) {
        LendAndReturn lendAndReturn = em.find(LendAndReturn.class, lendAndReturnId);
        BigDecimal fine = calculateFine(lendAndReturnId);

        lendAndReturn.setReturnDate(new Date(System.currentTimeMillis()));
        lendAndReturn.setFineAmount(fine);
        lendAndReturn.getBook().setAvailable(true);
        em.flush();
        return lendAndReturn;
    }

    @Override
    public List<LendAndReturn> retrieveAllLendAndReturnsOfMember(String identityNo) throws MemberNotFoundException {
        try {
            Query query = em.createQuery("SELECT l FROM LendAndReturn l WHERE l.memberId = :identityNo");
            return query.setParameter("identityNo", identityNo).getResultList();
        } catch (NoResultException | NonUniqueResultException ex) {
            throw new MemberNotFoundException(ex.getMessage());
        }
    }

}
