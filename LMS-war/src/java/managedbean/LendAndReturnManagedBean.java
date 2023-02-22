/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import ejb.session.stateless.LendAndReturnSessionBeanLocal;
import entity.LendAndReturn;
import exception.BookNotAvailableException;
import exception.EntityManagerException;
import exception.MemberNotFoundException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Uni
 */
@Named(value = "lendAndReturnManagedBean")
@SessionScoped
public class LendAndReturnManagedBean implements Serializable {

    @EJB
    private LendAndReturnSessionBeanLocal lendAndReturnSessionBeanLocal;

    private Long lendId;
    private Date lendDate;
    private Date returnDate;
    private BigDecimal fineAmount;

    private Long memberId;
    private String identityNo;

    private Long bookId;
    private String isbn;

    public LendAndReturnManagedBean() {
    }

    public Long getLendId() {
        return lendId;
    }

    public void setLendId(Long lendId) {
        this.lendId = lendId;
    }

    public String getIdentityNo() {
        return identityNo;
    }

    public void setIdentityNo(String identityNo) {
        this.identityNo = identityNo;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public LendAndReturn lendBook(ActionEvent evt) throws EntityManagerException, BookNotAvailableException {
        return lendAndReturnSessionBeanLocal.lendBook(identityNo, isbn);
    }

    public BigDecimal calculateFine(ActionEvent evt) {
        return lendAndReturnSessionBeanLocal.calculateFine(lendId);
    }

    public LendAndReturn returnBook(ActionEvent evt) {
        return lendAndReturnSessionBeanLocal.returnBook(lendId);
    }

    public List<LendAndReturn> retrieveAllLendAndReturnsOfMember(ActionEvent evt) throws MemberNotFoundException {
        return lendAndReturnSessionBeanLocal.retrieveAllLendAndReturnsOfMember(identityNo);
    }

    public LendAndReturnSessionBeanLocal getLendAndReturnSessionBeanLocal() {
        return lendAndReturnSessionBeanLocal;
    }

    public void setLendAndReturnSessionBeanLocal(LendAndReturnSessionBeanLocal lendAndReturnSessionBeanLocal) {
        this.lendAndReturnSessionBeanLocal = lendAndReturnSessionBeanLocal;
    }

    public Date getLendDate() {
        return lendDate;
    }

    public void setLendDate(Date lendDate) {
        this.lendDate = lendDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public BigDecimal getFineAmount() {
        return fineAmount;
    }

    public void setFineAmount(BigDecimal fineAmount) {
        this.fineAmount = fineAmount;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

}
