/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.Staff;
import exception.EntityManagerException;
import exception.InvalidLoginException;
import javax.ejb.Local;

/**
 *
 * @author Uni
 */
@Local
public interface StaffSessionBeanLocal {

    public Staff createStaff(Staff staff) throws EntityManagerException;

    Staff loginStaff(String username, String password)throws InvalidLoginException;
    
}
