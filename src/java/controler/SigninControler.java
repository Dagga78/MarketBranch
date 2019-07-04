/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpSession;
import model.Utilisateur;
import model.Vendeur;
import utilities.Encode;

/**
 *
 * @author Darman
 */
public class SigninControler {

    EntityManager entityManager;
    EntityManagerFactory entityManagerFactory;

    public SigninControler() {
        entityManagerFactory = Persistence.createEntityManagerFactory("TeamworkPU");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public String checkUser(String login, String password, HttpSession session) {
        try {
            Utilisateur user = (Utilisateur) entityManager.createNamedQuery("Utilisateur.findByMailAndPasswordUtilisateur").setParameter("mailUtilisateur", login).setParameter("passwordUtilisateur", Encode.sha1(password)).getSingleResult();
            if (user != null) {
                session.setAttribute("user", user);
                if (user.getIdTypeUtilisateur().getNomTypeUtilisateur().equals("Administrateur")) {
                    return "admin";
                } else if (user.getIdTypeUtilisateur().getNomTypeUtilisateur().equals("Client")) {
                    return "client";
                }
            }
        } catch (Exception e) {
            try {
                Vendeur vendeur = (Vendeur) entityManager.createNamedQuery("Vendeur.findByEmailAndPasswordVendeur").setParameter("emailVendeur", login).setParameter("passwordVendeur", Encode.sha1(password)).getSingleResult();
                if (vendeur != null) {
                    session.setAttribute("vendeur", vendeur);
                    return "vendeur";
                }
            } catch (Exception ex) {
                return "error";
            }
        }
        return "error";
    }
}
