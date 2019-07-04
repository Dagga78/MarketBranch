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
import model.SousCategorie;
import model.Utilisateur;
import model.Vendeur;

@WebServlet(name = "home", urlPatterns = {"/home"}, asyncSupported = true)
public class Home extends HttpServlet {

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
        //request.setAttribute("produits", data.getVendeurProduits(new Vendeur(1)));
        request.setAttribute("listCategories", data.getSousCategoriesByCategorie());
        if (session.getAttribute("user") instanceof Utilisateur) {
            Utilisateur user = (Utilisateur) session.getAttribute("user");
            request.setAttribute("user", session.getAttribute("user"));
            request.setAttribute("typeUser", user.getIdTypeUtilisateur().getNomTypeUtilisateur());
        } else if (session.getAttribute("user") instanceof Vendeur) {
            Vendeur user = (Vendeur) session.getAttribute("user");
            request.setAttribute("user", session.getAttribute("user"));
            request.setAttribute("typeUser", "Vendeur");
        }
        request.setAttribute("produits", data.getAllProduits());
        AsyncContext asynContext = request.startAsync(request, response);
        asynContext.dispatch("/home.jsp");
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
            case "account":
                if (session.getAttribute("user") != null) {
                    response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/account"));
                } else {
                    response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/signin"));
                }
                break;
            case "selectedProduct":
                session.setAttribute("productId", request.getParameter("selectedProduct"));
                response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/productInfo"));
                doGet(request, response);
                break;
            case "panier":
                if (session.getAttribute("user") != null) {
                    response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/panier"));
                } else {
                    response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/signin"));
                }
                break;
            case "addPaner":
                if (session.getAttribute("user") != null) {
                    model.Panier paner = new model.Panier();
                    Utilisateur user = (Utilisateur) session.getAttribute("user");
                    paner.setIdProduit(data.getUnitProduit(request.getParameter("selectedProduct")));
                    paner.setIdUser(user);
                    paner.setQuantiteProduit(1);
                    data.addPanier(paner);
                    response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/panier"));
                } else {
                    response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/signin"));
                }
                break;
            case "disconnect":
                response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/home"));
                request.getSession().invalidate();
                break;
            case "filterSsCategorie":
                SousCategorie ssCategorie = data.getSousCategorieById(request.getParameter("ssCategorie"));
                request.setAttribute("produits", data.getAllProduitsBySsCategorie(ssCategorie));
                request.setAttribute("listCategories", data.getSousCategoriesByCategorie());
                AsyncContext asynContext = request.startAsync(request, response);
                asynContext.dispatch("/home.jsp");
                request.getSession().invalidate();
                break;
            case "administration":
                response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/administration"));
                break;
            default:
                doGet(request, response);
        }
    }
}
