/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.Member;
import exception.EntityManagerException;
import exception.MemberNotFoundException;
import javax.ejb.Local;

/**
 *
 * @author Uni
 */
@Local
public interface MemberSessionBeanLocal {

    public Member createMember(Member member) throws EntityManagerException;

    public Member registerMember(String firstName, String lastName, Character gender, Integer age, String identityNo, String phone, String address)  throws EntityManagerException;

    Member retrieveMemberwithID(String id) throws MemberNotFoundException;
    
}
