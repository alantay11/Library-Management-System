/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.LendAndReturn;
import exception.EntityManagerException;
import javax.ejb.Remote;

/**
 *
 * @author Uni
 */
@Remote
public interface LendAndReturnSessionBeanRemote {

    public LendAndReturn lendBook(LendAndReturn lendAndReturn, String memberIDNum, String isbn) throws EntityManagerException;
    
}
