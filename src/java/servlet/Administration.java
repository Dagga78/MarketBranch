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
import model.Categorie;
import model.Entrepot;

@WebServlet(name = "administration", urlPatterns = {"/administration"}, asyncSupported = true)
public class Administration extends HttpServlet {

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
        model.Vendeur vendeur = (model.Vendeur) session.getAttribute("vendeur");
        request.setAttribute("listSsCategorie", data.getListSsCategories());
        request.setAttribute("listCategorie", data.getListCategories());
        request.setAttribute("produits", data.getVendeurProduits(vendeur));
        request.setAttribute("stats", data.getAllConsult());
        AsyncContext asynContext = request.startAsync(request, response);
        asynContext.dispatch("/administration.jsp");
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
            case "addCategorie":
                if (data.addCategorie(request.getParameter("nom_categorie_add"))) {
                    doGet(request, response);
                } else {
                    doGet(request, response);
                }
                break;
            case "addSsCategorie":
                data.addSsCategorie(Integer.parseInt(request.getParameter("nom_categorie_categorie_addsouscat")), request.getParameter("nom_categorie_addsouscat"));
                doGet(request, response);
                break;
            case "modifyCategory":
                Categorie modifyCategoryCat = data.getCategoryById(request.getParameter("nom_categorie_modifycat"));
                modifyCategoryCat.setNomCategorie(request.getParameter("nom_categorie_modifycatnew"));
                data.modifyCategory(modifyCategoryCat);
                doGet(request, response);
                break;
            case "deleteCategory":
                Categorie deleteCategoryCat = data.getCategoryById(request.getParameter("nom_categorie_deletecat"));
                data.deleteCategory(deleteCategoryCat);
                doGet(request, response);
                break;
            case "addWarehouse":
                Entrepot warehouse = new Entrepot();
                warehouse.setAdresseEntrepot(request.getParameter("adresse_entrepot"));
                warehouse.setCityEntrepot(request.getParameter("city_entrepot"));
                warehouse.setLibelleEntrepot(request.getParameter("libelle_entrepot"));
                if (data.addWarehouse(warehouse)) {
                    doGet(request, response);
                } else {
                    doGet(request, response);
                }
                break;
            case "blockUser":

                break;
            case "disconnect":
                response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/home"));
                request.getSession().invalidate();
                break;
            default:
                doGet(request, response);
        }
    }
}
