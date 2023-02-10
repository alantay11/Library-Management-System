/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.LendAndReturn;
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

    BigDecimal calculateFine(LendAndReturn lendAndReturn);

    LendAndReturn returnBook(LendAndReturn lendAndReturn);

    List<LendAndReturn> retrieveAllLendAndReturnsOfMember(String identityNo)  throws MemberNotFoundException;
    
}
