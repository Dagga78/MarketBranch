/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Darman
 */
@Entity
@Table(name = "produit")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Produit.findAll", query = "SELECT p FROM Produit p")
    , @NamedQuery(name = "Produit.findByIdProduit", query = "SELECT p FROM Produit p WHERE p.idProduit = :idProduit")
    , @NamedQuery(name = "Produit.findByIdVendeur", query = "SELECT p FROM Produit p WHERE p.idVendeur = :idVendeur")
    , @NamedQuery(name = "Produit.findByNameProduit", query = "SELECT p FROM Produit p WHERE p.nameProduit = :nameProduit")
    , @NamedQuery(name = "Produit.findBySsCategorie", query = "SELECT p FROM Produit p WHERE p.idSousCategorieProduit = :idSousCategorieProduit")
    , @NamedQuery(name = "Produit.findByProductCode", query = "SELECT p FROM Produit p WHERE p.productCode = :productCode")
    , @NamedQuery(name = "Produit.findByDescriptionProduit", query = "SELECT p FROM Produit p WHERE p.descriptionProduit = :descriptionProduit")
    , @NamedQuery(name = "Produit.findByPrixStandartProduit", query = "SELECT p FROM Produit p WHERE p.prixStandartProduit = :prixStandartProduit")
    , @NamedQuery(name = "Produit.findByQuantiteUnitaireProduit", query = "SELECT p FROM Produit p WHERE p.quantiteUnitaireProduit = :quantiteUnitaireProduit")})
public class Produit implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProduit")
    private List<Panier> panierList;

    @JoinTable(name = "panier", joinColumns = {
        @JoinColumn(name = "id_produit", referencedColumnName = "id_produit")}, inverseJoinColumns = {
        @JoinColumn(name = "id_user", referencedColumnName = "id_utilisateur")})
    @ManyToMany
    private List<Utilisateur> utilisateurList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_produit")
    private Integer idProduit;
    @Basic(optional = false)
    @Column(name = "name_produit")
    private String nameProduit;
    @Basic(optional = false)
    @Column(name = "product_code")
    private String productCode;
    @Column(name = "description_produit")
    private String descriptionProduit;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "prix_standart_produit")
    private BigDecimal prixStandartProduit;
    @Column(name = "quantite_unitaire_produit")
    private Integer quantiteUnitaireProduit;
    @Lob
    @Column(name = "img_produit")
    private String imgProduit;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProduit")
    private List<CommandeDetails> commandeDetailsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProduit")
    private List<Stock> stockList;
    @JoinColumn(name = "id_sous_categorie_produit", referencedColumnName = "id_sous_categorie")
    @ManyToOne(optional = false)
    private SousCategorie idSousCategorieProduit;
    @JoinColumn(name = "id_vendeur", referencedColumnName = "id_vendeur")
    @ManyToOne(optional = false)
    private Vendeur idVendeur;
    @OneToMany(mappedBy = "idProduit")
    private List<CommentaireUser> commentaireUserList;

    public Produit() {
    }

    public Produit(Integer idProduit) {
        this.idProduit = idProduit;
    }

    public Produit(Integer idProduit, String nameProduit, String productCode) {
        this.idProduit = idProduit;
        this.nameProduit = nameProduit;
        this.productCode = productCode;
    }

    public Integer getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Integer idProduit) {
        this.idProduit = idProduit;
    }

    public String getNameProduit() {
        return nameProduit;
    }

    public void setNameProduit(String nameProduit) {
        this.nameProduit = nameProduit;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getDescriptionProduit() {
        return descriptionProduit;
    }

    public void setDescriptionProduit(String descriptionProduit) {
        this.descriptionProduit = descriptionProduit;
    }

    public BigDecimal getPrixStandartProduit() {
        return prixStandartProduit;
    }

    public void setPrixStandartProduit(BigDecimal prixStandartProduit) {
        this.prixStandartProduit = prixStandartProduit;
    }

    public Integer getQuantiteUnitaireProduit() {
        return quantiteUnitaireProduit;
    }

    public void setQuantiteUnitaireProduit(Integer quantiteUnitaireProduit) {
        this.quantiteUnitaireProduit = quantiteUnitaireProduit;
    }

    public String getImgProduit() {
        return imgProduit;
    }

    public void setImgProduit(String imgProduit) {
        this.imgProduit = imgProduit;
    }

    @XmlTransient
    public List<CommandeDetails> getCommandeDetailsList() {
        return commandeDetailsList;
    }

    public void setCommandeDetailsList(List<CommandeDetails> commandeDetailsList) {
        this.commandeDetailsList = commandeDetailsList;
    }

    @XmlTransient
    public List<Stock> getStockList() {
        return stockList;
    }

    public void setStockList(List<Stock> stockList) {
        this.stockList = stockList;
    }

    public SousCategorie getIdSousCategorieProduit() {
        return idSousCategorieProduit;
    }

    public void setIdSousCategorieProduit(SousCategorie idSousCategorieProduit) {
        this.idSousCategorieProduit = idSousCategorieProduit;
    }

    public Vendeur getIdVendeur() {
        return idVendeur;
    }

    public void setIdVendeur(Vendeur idVendeur) {
        this.idVendeur = idVendeur;
    }

    @XmlTransient
    public List<CommentaireUser> getCommentaireUserList() {
        return commentaireUserList;
    }

    public void setCommentaireUserList(List<CommentaireUser> commentaireUserList) {
        this.commentaireUserList = commentaireUserList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProduit != null ? idProduit.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produit)) {
            return false;
        }
        Produit other = (Produit) object;
        if ((this.idProduit == null && other.idProduit != null) || (this.idProduit != null && !this.idProduit.equals(other.idProduit))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Produit[ idProduit=" + idProduit + " ]";
    }

    @XmlTransient
    public List<Utilisateur> getUtilisateurList() {
        return utilisateurList;
    }

    public void setUtilisateurList(List<Utilisateur> utilisateurList) {
        this.utilisateurList = utilisateurList;
    }

    @XmlTransient
    public List<Panier> getPanierList() {
        return panierList;
    }

    public void setPanierList(List<Panier> panierList) {
        this.panierList = panierList;
    }
    
}
