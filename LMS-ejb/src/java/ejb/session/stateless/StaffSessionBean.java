/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.Staff;
import exception.EntityManagerException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

/**
 *
 * @author Uni
 */
@Stateless
public class StaffSessionBean implements StaffSessionBeanRemote, StaffSessionBeanLocal {

    @PersistenceContext(unitName = "LMS-ejbPU")
    private EntityManager em;

    public Staff createStaff(Staff staff) throws EntityManagerException {
        try {
            em.persist(staff);
            em.flush();
            return staff;
        } catch (PersistenceException ex) {
            throw new EntityManagerException(ex.getMessage());
        }
    }
}
