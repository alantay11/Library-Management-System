/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import ejb.session.stateless.LendAndReturnSessionBeanLocal;
import entity.LendAndReturn;
import exception.BookNotAvailableException;
import exception.EntityManagerException;
import exception.MemberNotFoundException;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Uni
 */
public class LendAndReturnManager {

    LendAndReturnSessionBeanLocal lendAndReturnSessionBeanLocal;

    public LendAndReturnManager() {
    }

    public LendAndReturnManager(LendAndReturnSessionBeanLocal lendAndReturnSessionBeanLocal) {
        this.lendAndReturnSessionBeanLocal = lendAndReturnSessionBeanLocal;
    }

    public LendAndReturn lendBook(String memberIDNum, String isbn) throws EntityManagerException, BookNotAvailableException {
        return lendAndReturnSessionBeanLocal.lendBook(memberIDNum, isbn);
    }

    public BigDecimal calculateFine(long lendAndReturnId) {
        return lendAndReturnSessionBeanLocal.calculateFine(lendAndReturnId);
    }
     public LendAndReturn returnBook(long lendAndReturnId) {
         return lendAndReturnSessionBeanLocal.returnBook(lendAndReturnId);
    }
    
    public List<LendAndReturn> retrieveAllLendAndReturnsOfMember(String identityNo) throws MemberNotFoundException {
        return lendAndReturnSessionBeanLocal.retrieveAllLendAndReturnsOfMember(identityNo);
    }

}
