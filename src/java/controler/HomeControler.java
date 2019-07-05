/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Categorie;
import model.Commande;
import model.CommandeDetails;
import model.Entrepot;
import model.LogConsultation;
import model.Panier;
import model.Produit;
import model.SousCategorie;
import model.SystemBancaire;
import model.Transporteur;
import model.Utilisateur;
import model.Vendeur;
import sql.SQLConnexion;
import sql.SQLGate;
import utilities.Encode;

/**
 *
 * @author Darman
 */
public class HomeControler {

    EntityManager entityManager;
    EntityManagerFactory entityManagerFactory;

    public HomeControler() {
        entityManagerFactory = Persistence.createEntityManagerFactory("TeamworkPU");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public List<Categorie> getListCategories() {
        List<Categorie> categories = entityManager.createNamedQuery("Categorie.findAll").getResultList();
        return categories;
    }

    public List<SousCategorie> getListSsCategories() {
        List<SousCategorie> categories = entityManager.createNamedQuery("SousCategorie.findAll").getResultList();
        return categories;
    }

    public Map<Produit, Integer> getAllConsult() {
        List<LogConsultation> log = entityManager.createNamedQuery("LogConsultation.findAll").getResultList();
        Map<Produit, Integer> map = new HashMap<>();
        for (LogConsultation logConsultation : log) {
            if (!map.containsKey(logConsultation.getIdProduit())) {
                map.put(logConsultation.getIdProduit(), 1);
            } else {
                Integer i = map.get(logConsultation.getIdProduit());
                map.put(logConsultation.getIdProduit(), i++);
            }
        }
        return map;
    }

    public Categorie getCategoryById(String id) {
        Categorie category = (Categorie) entityManager.createNamedQuery("Categorie.findByIdCategorie").setParameter("idCategorie", Integer.parseInt(id)).getSingleResult();
        return category;
    }

    public SousCategorie getSousCategorieById(String id) {
        SousCategorie ssCategorie = (SousCategorie) entityManager.createNamedQuery("SousCategorie.findByIdSousCategorie").setParameter("idSousCategorie", Integer.parseInt(id)).getSingleResult();
        return ssCategorie;
    }

    public Produit getUnitProduit(String id) {
        Produit produit = (Produit) entityManager.createNamedQuery("Produit.findByIdProduit").setParameter("idProduit", Integer.parseInt(id)).getSingleResult();
        return produit;
    }

    public SystemBancaire getSystemBancaireUniq(String id) {
        SystemBancaire sysbank = (SystemBancaire) entityManager.createNamedQuery("SystemBancaire.findByIdSystemBancaire").setParameter("idSystemBancaire", Integer.parseInt(id)).getSingleResult();
        return sysbank;
    }

    public List<Produit> getAllProduitsBySsCategorie(SousCategorie ssCategorie) {
        List<Produit> produits = entityManager.createNamedQuery("Produit.findBySsCategorie").setParameter("idSousCategorieProduit", ssCategorie).getResultList();
        return produits;
    }

    public List<Produit> getAllProduits() {
        List<Produit> produits = entityManager.createNamedQuery("Produit.findAll").getResultList();
        return produits;
    }

    public List<Panier> getAllPanierByUser(Utilisateur user) {
        List<Panier> panier = entityManager.createNamedQuery("Panier.findByIdUser").setParameter("idUser", user).getResultList();
        return panier;
    }

    public Map<Commande, List<CommandeDetails>> getSousCommandesByCommande(Utilisateur user) {
        Map<Commande, List<CommandeDetails>> map = new HashMap<>();
        List<Commande> listCommandes = entityManager.createNamedQuery("Commande.findByIdUtilisateur").setParameter("idUtilisateur", user).getResultList();
        for (Commande commande : listCommandes) {
            if (!map.containsKey(commande)) {
                map.put(commande, new LinkedList<>());
            }
            List<CommandeDetails> detailCommandes = entityManager.createNamedQuery("CommandeDetails.findByIdCommande").setParameter("idCommande", commande).getResultList();
            for (CommandeDetails detailCommande : detailCommandes) {
                map.get(commande).add(detailCommande);
            }
        }
        return map;
    }

    public List<Utilisateur> getAllUser() {
        List<Utilisateur> user = entityManager.createNamedQuery("Utilisateur.findAll").getResultList();
        return user;
    }

    public Map<Categorie, List<SousCategorie>> getSousCategoriesByCategorie() {
        Map<Categorie, List<SousCategorie>> map = new HashMap<>();
        List<Categorie> listCategories = entityManager.createNamedQuery("Categorie.findAll").getResultList();
        for (Categorie category : listCategories) {
            if (!map.containsKey(category)) {
                map.put(category, new LinkedList<>());
            }
            List<SousCategorie> subCategories = entityManager.createNamedQuery("SousCategorie.findByCategorie").setParameter("idCategorie", category).getResultList();
            for (SousCategorie subCategory : subCategories) {
                map.get(category).add(subCategory);
            }
        }
        return map;
    }

    public List<Transporteur> getAllTransporteur() {
        List<Transporteur> tr = entityManager.createNamedQuery("Transporteur.findAll").getResultList();
        return tr;
    }

    public List<Produit> getVendeurProduits(Vendeur vendeur) {
        List<Produit> produits = entityManager.createNamedQuery("Produit.findByIdVendeur").setParameter("idVendeur", vendeur).getResultList();
        return produits;
    }

    public boolean deleteSsCategory(SousCategorie ssCat) {
        String request = "DELETE FROM `northwind`.`sous_categorie` WHERE `id_sous_categorie`='" + ssCat.getIdSousCategorie() + "';";
        SQLConnexion sql = SQLGate.getConnexion("root", "");
        if (sql.executeUpdate(request) == -1) {
            sql.rollback();
            return false;
        }
        sql.commit();
        return true;
    }

    public boolean deleteCategory(Categorie cat) {
        String request = "DELETE FROM `northwind`.`categorie` WHERE `id_categorie`='" + cat.getIdCategorie() + "';";
        SQLConnexion sql = SQLGate.getConnexion("root", "");
        if (sql.executeUpdate(request) == -1) {
            sql.rollback();
            return false;
        }
        sql.commit();
        return true;
    }

    public boolean modifyCategory(Categorie cat) {
        String request = "UPDATE `northwind`.`categorie` SET `nom_categorie`='" + cat.getNomCategorie() + "' WHERE `id_categorie`='" + cat.getIdCategorie() + "';";
        SQLConnexion sql = SQLGate.getConnexion("root", "");
        if (sql.executeUpdate(request) == -1) {
            sql.rollback();
            return false;
        }
        sql.commit();
        return true;
    }

    public boolean addSsCategorie(Integer nomCategorie, String idSsCategorie) {
        String request = "INSERT INTO `northwind`.`sous_categorie` (`nom_sous_categorie`, `id_categorie`) VALUES ('" + idSsCategorie + "', '" + nomCategorie + "');";
        SQLConnexion sql = SQLGate.getConnexion("root", "");
        if (sql.executeUpdate(request) == -1) {
            sql.rollback();
            return false;
        }
        sql.commit();
        return true;
    }

    public boolean addCategorie(String nomCategorie) {
        String request = "INSERT INTO `northwind`.`categorie` (`nom_categorie`) VALUES ('" + nomCategorie + "');";
        System.out.println("request" + request);
        SQLConnexion sql = SQLGate.getConnexion("root", "");
        if (sql.executeUpdate(request) == -1) {
            sql.rollback();
            return false;
        }
        sql.commit();
        return true;
    }

    public boolean addPanier(Panier paner) {
        String request = "INSERT INTO `northwind`.`panier` (`id_user`, `id_produit`, `quantite_produit`) VALUES ('" + paner.getIdUser().getIdUtilisateur() + "', '" + paner.getIdProduit().getIdProduit() + "', '" + paner.getQuantiteProduit() + "');";
        SQLConnexion sql = SQLGate.getConnexion("root", "");
        if (sql.executeUpdate(request) == -1) {
            sql.rollback();
            return false;
        }
        sql.commit();
        return true;
    }

    public boolean addProduit(Produit product) {
        String request = "INSERT INTO `northwind`.`produit` (`name_produit`, `product_code`, `description_produit`, `prix_standart_produit`, `quantite_unitaire_produit`, `id_sous_categorie_produit`, `id_vendeur`, `img_produit`) VALUES ('" + product.getNameProduit() + "', '" + product.getProductCode() + "', '" + product.getDescriptionProduit() + "', '" + product.getPrixStandartProduit() + "', '" + product.getQuantiteUnitaireProduit() + "', '" + product.getIdSousCategorieProduit().getIdSousCategorie() + "', '" + product.getIdVendeur().getIdVendeur() + "', '" + product.getImgProduit() + "');";
        SQLConnexion sql = SQLGate.getConnexion("root", "");
        if (sql.executeUpdate(request) == -1) {
            sql.rollback();
            return false;
        }
        sql.commit();
        return true;
    }

    public boolean addWarehouse(Entrepot warehouse) {
        String request = "INSERT INTO `northwind`.`entrepot` (`adresse_entrepot`, `city_entrepot`, `libelle_entrepot`) VALUES ('" + warehouse.getAdresseEntrepot() + "', '" + warehouse.getCityEntrepot() + "', '" + warehouse.getLibelleEntrepot() + "');";
        SQLConnexion sql = SQLGate.getConnexion("root", "");
        if (sql.executeUpdate(request) == -1) {
            sql.rollback();
            return false;
        }
        sql.commit();
        return true;
    }

    public Commande commandOfPaner(Commande commande) {
        utilities.Time date = new utilities.Time();
        String dateCommande = date.format(utilities.Time.SQL);
        Date dateCommande2 = date.getDate();
        for (int i = 0; i < 10; i++) {
            date.add(2);
        }
        String dateLivraison = date.format(utilities.Time.SQL);
        Date dateLivraison2 = date.getDate();
        String request = "INSERT INTO `northwind`.`commande` (`date_commande`, `date_livraison`, `id_utilisateur`) VALUES ('" + dateCommande + "', '" + dateLivraison + "', '" + commande.getIdUtilisateur().getIdUtilisateur() + "');";
        SQLConnexion sql = SQLGate.getConnexion("root", "");
        if (sql.executeUpdate(request) == -1) {
            sql.rollback();
            return null;
        }
        sql.commit();
        List<Commande> commandeReturn = entityManager.createNamedQuery("Commande.findByAll").setParameter("idUtilisateur", commande.getIdUtilisateur()).setParameter("dateLivraison", dateLivraison2).setParameter("dateCommande", dateCommande2).getResultList();
        return commandeReturn.get(commandeReturn.size() - 1);
    }

    public boolean addCommandDetails(List<CommandeDetails> list) {
        List<String> listRequests;
        listRequests = new ArrayList<>();
        for (CommandeDetails commandeDetails : list) {
            listRequests.add("INSERT INTO `northwind`.`commande_details` (`id_commande`, `id_produit`, `quantite_produit`, `code_promo`, `id_transporteur`, `id_status`) VALUES "
                    + "('" + commandeDetails.getIdCommande().getIdCommande() + "', '" + commandeDetails.getIdProduit().getIdProduit() + "', '" + commandeDetails.getQuantiteProduit()
                    + "', '" + commandeDetails.getCodePromo() + "', '" + commandeDetails.getIdTransporteur().getIdTransporteur() + "', '" + commandeDetails.getIdStatus().getIdStatusCommandeDetails() + "');");
        }
        SQLConnexion sql = SQLGate.getConnexion("root", "");
        for (String req : listRequests) {
            if (sql.executeUpdate(req) == -1) {
                sql.rollback();
                return false;
            }
            sql.commit();
        }
        return true;
    }

    public Transporteur getTransporteurById(String id) {
        Transporteur transporteur = (Transporteur) entityManager.createNamedQuery("Transporteur.findByIdTransporteur").setParameter("idTransporteur", Integer.parseInt(id)).getSingleResult();
        return transporteur;
    }

    public boolean addLogConsult(String produitId, String userId) {
        String req = "INSERT INTO `northwind`.`log_consultation` (`id_user`, `id_produit`) VALUES ('" + userId + "', '" + produitId + "');";
        SQLConnexion sql = SQLGate.getConnexion("root", "");
        if (sql.executeUpdate(req) == -1) {
            sql.rollback();
            return false;
        }
        sql.commit();
        return true;
    }

    public boolean deletePanierByUserId(String id) {
        String request = "DELETE FROM `northwind`.`panier` WHERE `id_user`='" + id + "';";
        SQLConnexion sql = SQLGate.getConnexion("root", "");
        if (sql.executeUpdate(request) == -1) {
            sql.rollback();
            return false;
        }
        sql.commit();
        return true;
    }

    public boolean updateProduit(Produit pr, String id) {
        String req = "UPDATE `northwind`.`produit` SET "
                + "`name_produit`='" + pr.getNameProduit() + "', "
                + "`product_code`='" + pr.getProductCode() + "', "
                + "`description_produit`='" + pr.getDescriptionProduit() + "', "
                + "`prix_standart_produit`='" + pr.getPrixStandartProduit() + "', "
                + "`quantite_unitaire_produit`='" + pr.getQuantiteUnitaireProduit() + "', "
                + "`id_sous_categorie_produit`='" + pr.getIdSousCategorieProduit().getIdSousCategorie() + "', "
                + "`id_vendeur`='" + id + "' "
                + "WHERE `id_produit`='" + pr.getIdProduit() + "';";
        SQLConnexion sql = SQLGate.getConnexion("root", "");
        if (sql.executeUpdate(req) == -1) {
            sql.rollback();
            return false;
        }
        sql.commit();
        return true;
    }

    public boolean addUser(String P_Nom, String P_Prenom, String P_Password, String P_mail, String P_adresse) {
        try {
            String request = "INSERT INTO `northwind`.`utilisateur` (`nom_utilisateur`, `prenom_utilisateur`, `password_utilisateur`, `mail_utilisateur`, `adresse_livraison`, `id_sb`, `id_type_utilisateur`) VALUES ('" + P_Nom + "', '" + P_Prenom + "', '" + Encode.sha1(P_Password) + "', '" + P_mail + "', '" + P_adresse + "', '" + "1" + "', '" + "1" + "');";
            SQLConnexion sql = SQLGate.getConnexion("root", "");
            if (sql.executeUpdate(request) == -1) {
                sql.rollback();
                return false;
            }
            sql.commit();
            return true;
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(HomeControler.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(HomeControler.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }
}
