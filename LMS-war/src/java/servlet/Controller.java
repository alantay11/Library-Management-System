/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import ejb.session.stateless.LendAndReturnSessionBeanLocal;
import ejb.session.stateless.MemberSessionBeanLocal;
import ejb.session.stateless.StaffSessionBeanLocal;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import manager.LendAndReturnManager;
import manager.MemberManager;
import manager.StaffManager;

/**
 *
 * @author Uni
 */
public class Controller extends HttpServlet {

    @EJB
    private LendAndReturnSessionBeanLocal lendAndReturnSessionBean;

    @EJB
    private MemberSessionBeanLocal memberSessionBean;

    @EJB
    private StaffSessionBeanLocal staffSessionBean;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            StaffManager staffManager = new StaffManager(staffSessionBean);
            MemberManager memberManager = new MemberManager(memberSessionBean);
            LendAndReturnManager lendAndReturnManager = new LendAndReturnManager(lendAndReturnSessionBean);
            String path = request.getPathInfo();
            path = path.split("/")[1];

            switch (path) {
                case "loginStaff": {
                    break;
                }
                case "doLoginStaff": {
                    String username = request.getParameter("username");
                    String password = request.getParameter("password");
                    staffManager.loginStaff(username, password);

                    response.sendRedirect("loggedInIndex.jsp");
                    return;
                }
                case "logoutStaff": {
                    response.sendRedirect(request.getContextPath());
                    break;
                }
                case "registerMember": {
                    String firstName = request.getParameter("firstName");
                    String lastName = request.getParameter("lastName");
                    Character gender = request.getParameter("gender").toCharArray()[0];
                    Integer age = Integer.parseInt(request.getParameter("age"));
                    String identityNo = request.getParameter("identityNo");
                    String phone = request.getParameter("phone");
                    String address = request.getParameter("address");
                    memberManager.registerMember(firstName, lastName, gender, age, identityNo, phone, address);
                    break;
                }
                case "lendBook": {
                    String identityNo = request.getParameter("identityNo");
                    String isbn = request.getParameter("isbn");
                    lendAndReturnManager.lendBook(identityNo, isbn);
                    break;
                }
                case "retrieveAllLendAndReturnsOfMember": {
                    String identityNo = request.getParameter("identityNo");
                    lendAndReturnManager.retrieveAllLendAndReturnsOfMember(identityNo);
                    break;
                }
                case "viewFineAmount": {
                    String identityNo = request.getParameter("identityNo");
                    lendAndReturnManager.retrieveAllLendAndReturnsOfMember(identityNo);

                    long lendAndReturnId = Long.parseLong(request.getParameter("lendAndReturnId"));
                    lendAndReturnManager.calculateFine(lendAndReturnId);
                    break;
                }
                case "returnBook": {
                    String identityNo = request.getParameter("identityNo");
                    lendAndReturnManager.retrieveAllLendAndReturnsOfMember(identityNo);

                    long lendAndReturnId = Long.parseLong(request.getParameter("lendAndReturnId"));
                    lendAndReturnManager.returnBook(lendAndReturnId);

                    break;
                }
                default:
                    path = "error";
                    break;
            }
            request.getRequestDispatcher("/" + path + ".jsp")
                    .forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher("/error.jsp")
                    .forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
