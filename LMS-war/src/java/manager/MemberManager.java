/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import ejb.session.stateless.MemberSessionBeanLocal;
import entity.Member;
import exception.EntityManagerException;
import exception.MemberNotFoundException;

/**
 *
 * @author Uni
 */
public class MemberManager {

    MemberSessionBeanLocal memberSessionBeanLocal;

    public MemberManager() {
    }

    public MemberManager(MemberSessionBeanLocal memberSessionBeanLocal) {
        this.memberSessionBeanLocal = memberSessionBeanLocal;
    }

    public Member registerMember(String firstName, String lastName, Character gender,
            Integer age, String identityNo, String phone, String address) throws EntityManagerException {

        return memberSessionBeanLocal.registerMember(firstName, lastName, gender, age, identityNo, phone, address);
    }
    
     private Member retrieveMemberwithID(String id) throws MemberNotFoundException {
        return memberSessionBeanLocal.retrieveMemberwithID(id);
    }
}
