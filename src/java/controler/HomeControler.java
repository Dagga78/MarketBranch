/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.Transactional;
import model.Categorie;
import model.Commande;
import model.CommandeDetails;
import model.Panier;
import model.Produit;
import model.SousCategorie;
import model.SystemBancaire;
import model.Utilisateur;
import model.Vendeur;
import sql.SQLConnexion;
import sql.SQLGate;

/**
 *
 * @author Darman
 */
public class HomeControler {

    EntityManager entityManager;
    EntityManagerFactory entityManagerFactory;

    @EJB
    PanierService ps;

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

}
