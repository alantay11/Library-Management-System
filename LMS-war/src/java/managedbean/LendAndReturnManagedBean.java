/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import ejb.session.stateless.LendAndReturnSessionBeanLocal;
import entity.Book;
import entity.LendAndReturn;
import entity.Member;
import exception.BookNotAvailableException;
import exception.EntityManagerException;
import exception.MemberNotFoundException;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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

    private Book selectedBook;
    private Member selectedMember;
    private List<LendAndReturn> lendAndReturns;
    private LendAndReturn selectedLendAndReturn;

    private String message;

    public void clearSelected() {
        this.selectedBook = null;
        this.selectedMember = null;
        this.lendAndReturns = null;
        this.selectedLendAndReturn = null;
    }

    public void saveMessage() {
        FacesContext context = FacesContext.getCurrentInstance();

        context.addMessage(null, new FacesMessage(this.message));
        context.getExternalContext().getFlash().setKeepMessages(true);
    }

    public LendAndReturn getSelectedLendAndReturn() {
        return selectedLendAndReturn;
    }

    public void setSelectedLendAndReturn(LendAndReturn selectedLendAndReturn) {
        this.selectedLendAndReturn = selectedLendAndReturn;
    }

    public void lendBook(ActionEvent evt) {
        try {
            lendAndReturnSessionBeanLocal.lendBook(selectedMember.getIdentityNo(), selectedBook.getBookId());
            this.message = selectedBook.getTitle() + " has been lent to " + selectedMember.getFirstName() + " " + selectedMember.getLastName();
            this.saveMessage();
        } catch (BookNotAvailableException | EntityManagerException ex) {
            this.message = selectedBook.getTitle() + " is not available.";
            this.saveMessage();
        }
    }

    public void calculateFine() {
        this.fineAmount = lendAndReturnSessionBeanLocal.calculateFine(selectedLendAndReturn.getLendId());
    }

    public void returnBook(ActionEvent evt) {
        lendAndReturnSessionBeanLocal.returnBook(selectedLendAndReturn.getLendId());
        this.message = selectedLendAndReturn.getBook().getTitle() + " has been returned";
        this.saveMessage();
    }

    public List<LendAndReturn> getLendAndReturns() {
        return lendAndReturns;
    }

    public void setLendAndReturns(List<LendAndReturn> lendAndReturns) {
        this.lendAndReturns = lendAndReturns;
    }

    public Member getSelectedMember() {
        return selectedMember;
    }

    public void setSelectedMember(Member selectedMember) {
        this.selectedMember = selectedMember;
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

    public Book getSelectedBook() {
        return selectedBook;
    }

    public void setSelectedBook(Book selectedBook) {
        this.selectedBook = selectedBook;
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
