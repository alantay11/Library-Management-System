/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import ejb.session.stateless.StaffSessionBeanLocal;
import entity.Staff;
import exception.EntityManagerException;
import exception.InvalidLoginException;
import exception.StaffNotFoundException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Uni
 */
@Named(value = "staffManagedBean")
@SessionScoped
public class StaffManagedBean implements Serializable {

    @EJB
    private StaffSessionBeanLocal staffSessionBeanLocal;
    private String firstName;
    private String lastName;
    private String username;
    private String password;

    public StaffManagedBean() {
    }

    public Staff createStaff(ActionEvent evt) throws InvalidLoginException, EntityManagerException {
        Staff staff = new Staff();
        staff.setFirstName(firstName);
        staff.setLastName(lastName);
        staff.setUsername(username);
        staff.setPassword(password);
        return staffSessionBeanLocal.createStaff(staff);
    }

    public Staff loginStaff(ActionEvent evt) throws InvalidLoginException {
        return staffSessionBeanLocal.loginStaff(username, password);
    }

    private Staff retrieveStaffByUsername(ActionEvent evt) throws StaffNotFoundException {
        return staffSessionBeanLocal.retrieveStaffByUsername(username);
    }

    public StaffSessionBeanLocal getStaffSessionBeanLocal() {
        return staffSessionBeanLocal;
    }

    public void setStaffSessionBeanLocal(StaffSessionBeanLocal staffSessionBeanLocal) {
        this.staffSessionBeanLocal = staffSessionBeanLocal;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
