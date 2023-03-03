/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.singleton;

import ejb.session.stateless.BookSessionBeanLocal;
import ejb.session.stateless.MemberSessionBeanLocal;
import ejb.session.stateless.StaffSessionBeanLocal;
import entity.Book;
import entity.Member;
import entity.Staff;
import enumeration.GenderEnumeration;
import exception.EntityManagerException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Uni
 */
@Singleton
@LocalBean
@Startup

public class DataInitSessionBean {

    @EJB
    private StaffSessionBeanLocal staffSessionBean;

    @EJB
    private MemberSessionBeanLocal memberSessionBean;

    @EJB
    private BookSessionBeanLocal bookSessionBean;
    @PersistenceContext(unitName = "LMS-ejbPU")
    private EntityManager em;

    public DataInitSessionBean() {
    }

    @PostConstruct
    public void postConstruct() {
        try {
            if (em.find(Staff.class, 1l) == null) {
                //Staff
                Staff eric = new Staff();
                eric.setFirstName("Eric");
                eric.setLastName("Some");
                eric.setUsername("eric");
                eric.setPassword("password");
                staffSessionBean.createStaff(eric);

                Staff sarah = new Staff();
                sarah.setFirstName("Sarah");
                sarah.setLastName("Brightman");
                sarah.setUsername("sarah");
                sarah.setPassword("password");
                staffSessionBean.createStaff(sarah);

                //Book
                Book book1 = new Book();
                book1.setTitle("Anna Karenina");
                book1.setIsbn("0451528611");
                book1.setAuthor("Leo Tolstoy");
                bookSessionBean.createBook(book1);

                Book book2 = new Book();
                book2.setTitle("Madame Bovary");
                book2.setIsbn("979-8649042031 ");
                book2.setAuthor("Gustave Flaubert");
                bookSessionBean.createBook(book2);

                Book book3 = new Book();
                book3.setTitle("Hamlet");
                book3.setIsbn("1980625026");
                book3.setAuthor("William Shakespeare");
                bookSessionBean.createBook(book3);

                Book book4 = new Book();
                book4.setTitle("The Hobbit");
                book4.setIsbn("9780007458424");
                book4.setAuthor("J R R Tolkien");
                bookSessionBean.createBook(book4);

                Book book5 = new Book();
                book5.setTitle("Great Expectations");
                book5.setIsbn("1521853592");
                book5.setAuthor("Charles Dickens");
                bookSessionBean.createBook(book5);

                Book book6 = new Book();
                book6.setTitle("Pride and Prejudice");
                book6.setIsbn("979-8653642272");
                book6.setAuthor("Jane Austen");
                bookSessionBean.createBook(book6);

                Book book7 = new Book();
                book7.setTitle("Wuthering Heights");
                book7.setIsbn("3961300224");
                book7.setAuthor("Emily Brontë");
                bookSessionBean.createBook(book7);

                //Member
                Member tony = new Member();
                tony.setFirstName("Tony");
                tony.setLastName("Shade");
                tony.setGender(GenderEnumeration.Male);
                tony.setAge(31);
                tony.setIdentityNo("S8900678A");
                tony.setPhone("83722773");
                tony.setAddress("13 Jurong East, Ave 3");
                memberSessionBean.createMember(tony);

                Member dewi = new Member();
                dewi.setFirstName("Dewi");
                dewi.setLastName("Tan");
                dewi.setGender(GenderEnumeration.Female);
                dewi.setAge(35);
                dewi.setIdentityNo("S8581028X");
                dewi.setPhone("94602711");
                dewi.setAddress("15 Computing Dr");
                memberSessionBean.createMember(dewi);
            }
        } catch (EntityManagerException ex) {
            Logger.getLogger(DataInitSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
