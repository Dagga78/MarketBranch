/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import controler.HomeControler;
import java.io.IOException;
import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Utilisateur;

@WebServlet(name = "account", urlPatterns = {"/account"}, asyncSupported = true)
public class Account extends HttpServlet {

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
        HomeControler data = new HomeControler();
        HttpSession session = request.getSession();
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        System.out.println("servlet.Account.doGet()" + user);
        request.setAttribute("listCategories", data.getListCategories());
        if (user.getIdSb() != null) {
            request.setAttribute("sysBank", data.getSystemBancaireUniq(user.getIdSb().getIdSystemBancaire().toString()));
            request.setAttribute("commandes", data.getSousCommandesByCommande(user));
        }
        AsyncContext asynContext = request.startAsync(request, response);
        asynContext.dispatch("/account.jsp");
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
        HomeControler data = new HomeControler();
        HttpSession session = request.getSession();
        switch (button) {
            case "home":
                request.setAttribute("data", data.getListCategories());
                response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/home"));
                break;
            case "panier":
                request.setAttribute("data", data.getListCategories());
                response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/panier"));
                break;
            case "facture":
                session.setAttribute("factureId", request.getParameter("factureId"));
                request.getRequestDispatcher("/infoFacture.jsp").forward(request, response);
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
