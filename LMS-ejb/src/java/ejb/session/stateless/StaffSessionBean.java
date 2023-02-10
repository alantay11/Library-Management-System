/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.Staff;
import exception.EntityManagerException;
import exception.InvalidLoginException;
import exception.StaffNotFoundException;
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
public class StaffSessionBean implements StaffSessionBeanRemote, StaffSessionBeanLocal {

    @PersistenceContext(unitName = "LMS-ejbPU")
    private EntityManager em;

    @Override
    public Staff createStaff(Staff staff) throws EntityManagerException {
        try {
            em.persist(staff);
            em.flush();
            return staff;
        } catch (PersistenceException ex) {
            throw new EntityManagerException(ex.getMessage());
        }
    }

    
    //Use Case 1
    @Override
    public Staff loginStaff(String username, String password) throws InvalidLoginException {
        try {
            Staff staff = retrieveStaffByUsername(username);

            if (staff.getPassword().equals(password)) {
                return staff;
            } else {
                throw new InvalidLoginException("Username does not exist or invalid password!");
            }
        } catch (StaffNotFoundException ex) {
            throw new InvalidLoginException("Username does not exist or invalid password!");
        }
    }

    
    public Staff retrieveStaffByUsername(String username) throws StaffNotFoundException
    {
        Query query = em.createQuery("SELECT s FROM Staff s WHERE s.username = :inUsername");
        query.setParameter("inUsername", username);
        
        try
        {
            return (Staff)query.getSingleResult();
        }
        catch(NoResultException | NonUniqueResultException ex)
        {
            throw new StaffNotFoundException("Staff Username " + username + " does not exist!");
        }
    }
}
