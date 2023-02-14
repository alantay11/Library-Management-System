/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.LendAndReturn;
import exception.BookNotAvailableException;
import exception.EntityManagerException;
import exception.MemberNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Uni
 */
@Local
public interface LendAndReturnSessionBeanLocal {

    public LendAndReturn lendBook(String memberIDNum, String isbn) throws EntityManagerException, BookNotAvailableException;

    BigDecimal calculateFine(long lendAndReturnId);

    LendAndReturn returnBook(long lendAndReturnId);

    List<LendAndReturn> retrieveAllLendAndReturnsOfMember(String identityNo) throws MemberNotFoundException;

}
