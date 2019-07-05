/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import controler.HomeControler;
import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.WebServiceRef;
import model.Commande;
import model.CommandeDetails;
import model.StatusCommandeDetails;
import model.SystemBancaire;
import model.Utilisateur;

@WebServlet(name = "panier", urlPatterns = {"/panier"}, asyncSupported = true)
public class Panier extends HttpServlet {

    @WebServiceRef(wsdlLocation = "http://localhost:8080/SystemeBancaireWS/SystemeBancaireWS?WSDL")
    bean.SystemeBancaireWS_Service SBCms;

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
        if (user != null) {
            request.setAttribute("sysBank", data.getSystemBancaireUniq(user.getIdSb().getIdSystemBancaire().toString()));
            request.setAttribute("commandes", data.getSousCommandesByCommande(user));
            request.setAttribute("paniers", data.getAllPanierByUser(user));
            request.setAttribute("transporteurs", data.getAllTransporteur());
        }
        AsyncContext asynContext = request.startAsync(request, response);
        asynContext.dispatch("/panier.jsp");
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
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        switch (button) {
            case "account":
                if (session.getAttribute("user") != null) {
                    response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/account"));
                } else {
                    response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/signin"));
                }
                break;
            case "command":
                Commande command = new Commande();
                command.setIdUtilisateur(user);
                if (user.getIdSb() != null) {
                    SystemBancaire sysBank = user.getIdSb();
                    String userName = user.getNomUtilisateur();
                    String userSurname = user.getPrenomUtilisateur();
                    Float price;
                    price = Float.parseFloat(session.getAttribute("salaryPanier").toString());
                    bean.SystemeBancaire system = SBCms.getSystemeBancaireWSPort().verifpaiement(userName, userSurname, sysBank.getNumeroCarte(), sysBank.getDateCarte(), sysBank.getCryptoCarte(), price);

                    if (system != null) {
                        Commande commandReturn = data.commandOfPaner(command);
                        if (commandReturn != null) {
                            List<model.Panier> paners = data.getAllPanierByUser(user);
                            List<model.CommandeDetails> commandDetailsList;
                            commandDetailsList = new ArrayList<>();
                            for (model.Panier paner : paners) {
                                CommandeDetails detail = new CommandeDetails();
                                detail.setCodePromo(request.getParameter("codePromo"));
                                detail.setQuantiteProduit(paner.getQuantiteProduit());
                                detail.setIdStatus(new StatusCommandeDetails(2));
                                detail.setIdProduit(paner.getIdProduit());
                                detail.setIdCommande(commandReturn);
                                detail.setIdTransporteur(data.getTransporteurById(request.getParameter("transporteur")));
                                commandDetailsList.add(detail);
                            }
                            if (data.addCommandDetails(commandDetailsList)) {
                                data.deletePanierByUserId(user.getIdUtilisateur().toString());
                                response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/account"));
                                break;
                            } else {
                                doGet(request, response);
                                break;
                            }
                        }
                    } else {
                        doGet(request, response);
                        break;
                    }
                } else {
                    session.setAttribute("error", "Vous n'avez pas de carte bleue");
                    doGet(request, response);
                    break;
                }
            case "disconnect":
                response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/home"));
                request.getSession().invalidate();
                break;
            case "home":
                response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/home"));
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
