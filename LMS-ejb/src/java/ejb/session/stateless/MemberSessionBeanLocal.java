/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.Member;
import enumeration.GenderEnumeration;
import exception.EntityManagerException;
import exception.MemberNotFoundException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Uni
 */
@Local
public interface MemberSessionBeanLocal {

    public Member createMember(Member member) throws EntityManagerException;

    public Member registerMember(String firstName, String lastName, GenderEnumeration gender, Integer age, String identityNo, String phone, String address)  throws EntityManagerException;

    Member retrieveMemberwithID(String id) throws MemberNotFoundException;

    public List<Member> retrieveAllMembers();
    
}
