/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.Member;
import exception.EntityManagerException;
import exception.MemberNotFoundException;
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
public class MemberSessionBean implements MemberSessionBeanLocal {

    @PersistenceContext(unitName = "LMS-ejbPU")
    private EntityManager em;

    @Override
    public Member createMember(Member member) throws EntityManagerException {
        try {
            em.persist(member);
            em.flush();
            return member;
        } catch (PersistenceException ex) {
            throw new EntityManagerException(ex.getMessage());
        }
    }

    
    // Use Case 2, don't know the input format yet
    @Override
    public Member registerMember(String firstName, String lastName, Character gender,
            Integer age, String identityNo, String phone, String address) throws EntityManagerException {
        try {
            Member member = new Member();
            member.setFirstName(firstName);
            member.setLastName(lastName);
            member.setGender(gender);
            member.setAge(age);
            member.setIdentityNo(identityNo);
            member.setPhone(phone);
            member.setAddress(address);
            return createMember(member);
        } catch (EntityManagerException ex) {
            throw ex; 
        }
    }

    @Override
    public Member retrieveMemberwithID(String id) throws MemberNotFoundException {
        Query query = em.createQuery("SELECT m FROM Member m WHERE m.identityNo = :id");
        query.setParameter("id", id);
        
         try
        {
            Member member = (Member)query.getSingleResult();
            member.getLending().size();
            return member;
        }
        catch(NoResultException | NonUniqueResultException ex)
        {
            throw new MemberNotFoundException("Member with ID: " + id + " does not exist!");
        }
    }

}
