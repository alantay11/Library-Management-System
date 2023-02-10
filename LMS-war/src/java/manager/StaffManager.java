/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import ejb.session.stateless.StaffSessionBeanLocal;
import entity.Staff;
import exception.EntityManagerException;
import exception.InvalidLoginException;
import exception.StaffNotFoundException;

/**
 *
 * @author Uni
 */
public class StaffManager {

    private StaffSessionBeanLocal staffSessionBeanLocal;

    public StaffManager(StaffSessionBeanLocal staffSessionBeanLocal) {
        this.staffSessionBeanLocal = staffSessionBeanLocal;
    }

    public StaffManager() {
    }

    public Staff createStaff(String firstName, String lastName, String userName, String password) throws InvalidLoginException, EntityManagerException {
        Staff staff = new Staff();
        staff.setFirstName(firstName);
        staff.setLastName(lastName);
        staff.setUserName(userName);
        staff.setPassword(password);
        return staffSessionBeanLocal.createStaff(staff);
    }

    public Staff loginStaff(String username, String password) throws InvalidLoginException {
        return staffSessionBeanLocal.loginStaff(username, password);
    }

    private Staff retrieveStaffByUsername(String username) throws StaffNotFoundException {
        return staffSessionBeanLocal.retrieveStaffByUsername(username);
    }

}
