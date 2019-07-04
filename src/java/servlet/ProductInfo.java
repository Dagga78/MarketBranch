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

/**
 *
 * @author Darman
 */
@WebServlet(name = "productInfo", urlPatterns = {"/productInfo"}, asyncSupported = true)
public class ProductInfo extends HttpServlet {
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
        //request.setAttribute("produits", data.getVendeurProduits(new Vendeur(1)));
        request.setAttribute("listCategories", data.getSousCategoriesByCategorie());
        HttpSession session = request.getSession();
        request.setAttribute("produit", data.getUnitProduit((String) session.getAttribute("productId")));
        AsyncContext asynContext = request.startAsync(request, response);
        asynContext.dispatch("/productInfo.jsp");
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
        request.setAttribute("test", data.getListCategories());
        HttpSession session = request.getSession();
        switch (button) {
            case "account":
                if (session.getAttribute("user") != null) {

                }
                request.setAttribute("data", data.getListCategories());
                doGet(request, response);
                break;
            case "selectedProduct":
                session.setAttribute("productId", request.getParameter("selectedProduct"));
                response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/productInfo"));
                doGet(request, response);
                break;
            case "employe":
                request.setAttribute("data", data.getListCategories());
                request.getRequestDispatcher("/listEmployes.jsp").forward(request, response);
                break;
            case "client":
                request.setAttribute("data", data.getListCategories());
                request.getRequestDispatcher("/listClients.jsp").forward(request, response);
                break;
            default:
                doGet(request, response);
        }
    }
}
