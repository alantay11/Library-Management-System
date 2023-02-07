/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.Member;
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
public class MemberSessionBean implements MemberSessionBeanRemote, MemberSessionBeanLocal {

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
}
