/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import controler.HomeControler;
import java.io.IOException;
import java.math.BigDecimal;
import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Produit;
import model.SousCategorie;
import model.Utilisateur;

@WebServlet(name = "vendeur", urlPatterns = {"/vendeur"}, asyncSupported = true)
public class Vendeur extends HttpServlet {

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
        request.setAttribute("produits", data.getVendeurProduits(vendeur));
        AsyncContext asynContext = request.startAsync(request, response);
        asynContext.dispatch("/espaceVendeur.jsp");
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
            case "createProduct":
                Produit product = new Produit();
                product.setDescriptionProduit(request.getParameter("descriptionProduit"));
                product.setNameProduit(request.getParameter("nomProduit"));
                product.setProductCode(request.getParameter("codeProduit"));
                product.setPrixStandartProduit((BigDecimal.valueOf(Double.valueOf(request.getParameter("prixProduit")))));
                product.setQuantiteUnitaireProduit(Integer.parseInt(request.getParameter("quantiteProduit")));
                SousCategorie ssCategorieProduct = data.getSousCategorieById(request.getParameter("ssCategorieProduit"));
                product.setIdSousCategorieProduit(ssCategorieProduct);
                product.setImgProduit(request.getParameter("imgProduitUrl"));
                product.setIdVendeur((model.Vendeur) session.getAttribute("vendeur"));
                if (data.addProduit(product)) {
                    response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/vendeur"));
                } else {
                    response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/vendeur"));
                }
                break;
            case "modifyProduct":
                Produit produitModify;
                produitModify = new Produit(Integer.parseInt(request.getParameter("selectedProduct")));
                produitModify.setNameProduit(request.getParameter("nomProduitModify_" + request.getParameter("selectedProduct")));
                produitModify.setDescriptionProduit(request.getParameter("descriptionProduitModify_" + request.getParameter("selectedProduct")));
                produitModify.setProductCode(request.getParameter("codeProduitModify_" + request.getParameter("selectedProduct")));
                produitModify.setPrixStandartProduit((BigDecimal.valueOf(Double.valueOf(request.getParameter("prixProduitModify_" + request.getParameter("selectedProduct"))))));
                produitModify.setQuantiteUnitaireProduit(Integer.parseInt(request.getParameter("quantiteProduitModify_" + request.getParameter("selectedProduct"))));
                SousCategorie ssCategorieProductModify = data.getSousCategorieById(request.getParameter("ssCategorieProduitModify_" + request.getParameter("selectedProduct")));
                produitModify.setIdSousCategorieProduit(ssCategorieProductModify);
                model.Vendeur vend = (model.Vendeur) session.getAttribute("vendeur");
                produitModify.setIdVendeur(vend);
                if (data.updateProduit(produitModify, vend.getIdVendeur().toString())) {
                    response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/vendeur"));
                } else {
                    response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/vendeur"));
                }
                break;
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
            default:
                doGet(request, response);
        }
    }
}
