/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import controler.SigninControler;
import java.io.IOException;
import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "signin", urlPatterns = {"/signin"}, asyncSupported = true)
public class Signin extends HttpServlet {

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
        AsyncContext asynContext = request.startAsync(request, response);
        asynContext.dispatch("/signin.jsp");
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
        String button = request.getParameter("button");
        SigninControler data = new SigninControler();
        HttpSession session = request.getSession();
        String userConnect = data.checkUser(request.getParameter("email"), request.getParameter("password"), session);
        System.out.println("servlet.Signin.doPost()" + userConnect);
        switch (button) {
            case "signin":
                if (userConnect.equals("vendeur")) {
                    response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/vendeur"));
                } else if (userConnect.equals("admin")) {
                    response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/home"));
                } else if (userConnect.equals("client")) {
                    response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/home"));
                } else {
                    doGet(request, response);
                }
                break;
            case "inscription":
                response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/inscription"));
                break;
            default:
                doGet(request, response);
        }
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
