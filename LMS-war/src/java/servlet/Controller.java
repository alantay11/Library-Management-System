/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import ejb.session.stateless.StaffSessionBeanLocal;
import exception.InvalidLoginException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import manager.StaffManager;

/**
 *
 * @author Uni
 */
public class Controller extends HttpServlet {

    @EJB
    private StaffSessionBeanLocal staffSessionBean;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            StaffManager staffManager = new StaffManager(staffSessionBean);
            String path = request.getPathInfo();
            path = path.split("/")[1];
            
            switch (path) {
                case "loginStaff":
                    // fill in the stuff here
                    String username = request.getParameter("username");
                    String password = request.getParameter("password");
                    staffManager.loginStaff(username, password);
                    break;
                case "logoutStaff": {
                    response.sendRedirect(request.getContextPath()
                            + "/Controller/loginStaff");
                    break;
                }
            }
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
