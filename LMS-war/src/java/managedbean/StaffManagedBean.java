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
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.validator.ValidatorException;

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
    private Staff staff;

    private String oldPassword;
    private String newPassword;
    private String confirmPassword;
    private String message;

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public void validateOldPassword(FacesContext context, UIComponent component, Object value) {
        password = this.staff.getPassword();
        this.oldPassword = (String) value;

        if (!password.equals(oldPassword)) {
            FacesMessage error = new FacesMessage(FacesMessage.SEVERITY_ERROR, "You entered the wrong current password", null);
            throw new ValidatorException(error);
        }
    }

    public void resetStaffPassword(ActionEvent evt) throws StaffNotFoundException {
        this.staffSessionBeanLocal.changeStaffPassword(this.staff.getUserName(), this.confirmPassword);
        this.staff = retrieveStaffByUsername(evt);
        this.message = "Password successfully changed";
        this.saveMessage(FacesMessage.SEVERITY_INFO);
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public Staff createStaff(ActionEvent evt) throws InvalidLoginException, EntityManagerException {
        this.staff = new Staff();
        staff.setFirstName(firstName);
        staff.setLastName(lastName);
        staff.setUserName(username);
        staff.setPassword(password);
        return staffSessionBeanLocal.createStaff(staff);
    }

    public void saveMessage(FacesMessage.Severity severity) {
        FacesContext context = FacesContext.getCurrentInstance();

        context.addMessage(null, new FacesMessage(severity, this.message, null));
        context.getExternalContext().getFlash().setKeepMessages(true);
    }

    public String loginStaff(ActionEvent evt) throws IOException {
        try {
            this.staff = retrieveStaffByUsername(evt);
            staffSessionBeanLocal.loginStaff(username, password);
            FacesContext.getCurrentInstance().getExternalContext().redirect("secret/index.xhtml");
            return "secret/index.xhtml";
        } catch (StaffNotFoundException ex) {
            this.message = "This staff member doesn't exist!";
            this.saveMessage(FacesMessage.SEVERITY_ERROR);
            return "login.xhtml";
        } catch (InvalidLoginException ex) {
            this.message = "Your username or password is wrong";
            this.saveMessage(FacesMessage.SEVERITY_ERROR);
            return "login.xhtml";
        }
    }
    
    public String logoutStaff() {
        this.clearFields();
        return "/login.xhtml?faces-redirect=true";
    }
    
    private void clearFields() {
        this.staff = null;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
