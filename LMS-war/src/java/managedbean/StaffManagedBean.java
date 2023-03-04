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
import java.io.IOException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
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

    private String message;

    public Staff createStaff(ActionEvent evt) throws InvalidLoginException, EntityManagerException {
        Staff staff = new Staff();
        staff.setFirstName(firstName);
        staff.setLastName(lastName);
        staff.setUsername(username);
        staff.setPassword(password);
        return staffSessionBeanLocal.createStaff(staff);
    }

    public void saveMessage() {
        FacesContext context = FacesContext.getCurrentInstance();

        context.addMessage(null, new FacesMessage(this.message));
        context.getExternalContext().getFlash().setKeepMessages(true);
    }

    public void loginStaff(ActionEvent evt) throws IOException {
        try {
            Staff staff = retrieveStaffByUsername(evt);
            this.message = "Welcome " + staff.getFirstName() + " " + staff.getLastName();
            staffSessionBeanLocal.loginStaff(username, password);
            FacesContext.getCurrentInstance().getExternalContext().redirect("viewAllMembers.xhtml");
        } catch (StaffNotFoundException ex) {
            this.message = "This staff member doesn't exist!";
        } catch (InvalidLoginException ex) {
            this.message = "Your username or password is wrong";
        } finally {
            this.saveMessage();
        }
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
