/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import ejb.session.stateless.MemberSessionBeanLocal;
import entity.Member;
import enumeration.GenderEnumeration;
import exception.EntityManagerException;
import exception.MemberNotFoundException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Uni
 */
@Named(value = "memberManagedBean")
@RequestScoped
public class MemberManagedBean {

    @EJB
    private MemberSessionBeanLocal memberSessionBeanLocal;

    private String firstName;
    private String lastName;
    private GenderEnumeration gender;
    private GenderEnumeration[] genderList;
    private Integer age;
    private String identityNo;
    private String phone;
    private String address;

    private List<Member> members;

    @PostConstruct
    public void init() {
        this.members = memberSessionBeanLocal.retrieveAllMembers();
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public GenderEnumeration[] getGenderList() {
        return GenderEnumeration.values();
    }

    public void registerMember(ActionEvent evt) {
        try {
        memberSessionBeanLocal.registerMember(firstName, lastName, gender, age, identityNo, phone, address);
        this.saveMessage(firstName + " " + lastName + " registered");
        this.clearFields();
        } catch (EntityManagerException ex) {
            this.saveMessage("A member with the same identity no. already exists!");
        }
    }
    
    public void saveMessage(String message) {
        FacesContext context = FacesContext.getCurrentInstance();

        context.addMessage(null, new FacesMessage(message));
    }

    private void clearFields() {
        this.firstName = null;
        this.lastName = null;
        this.gender = null;
        this.age = null;
        this.identityNo = null;
        this.phone = null;
        this.address = null;
    }

    private Member retrieveMemberwithID(ActionEvent evt) throws MemberNotFoundException {
        return memberSessionBeanLocal.retrieveMemberwithID(identityNo);
    }

    public MemberSessionBeanLocal getMemberSessionBeanLocal() {
        return memberSessionBeanLocal;
    }

    public void setMemberSessionBeanLocal(MemberSessionBeanLocal memberSessionBeanLocal) {
        this.memberSessionBeanLocal = memberSessionBeanLocal;
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

    public GenderEnumeration getGender() {
        return gender;
    }

    public void setGender(GenderEnumeration gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getIdentityNo() {
        return identityNo;
    }

    public void setIdentityNo(String identityNo) {
        this.identityNo = identityNo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
