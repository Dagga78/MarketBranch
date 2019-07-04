/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Darman
 */
@Entity
@Table(name = "panier")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Panier.findAll", query = "SELECT p FROM Panier p")
    , @NamedQuery(name = "Panier.findByIdUser", query = "SELECT p FROM Panier p WHERE p.idUser = :idUser")
    , @NamedQuery(name = "Panier.findByIdPanier", query = "SELECT p FROM Panier p WHERE p.idPanier = :idPanier")
    , @NamedQuery(name = "Panier.findByQuantiteProduit", query = "SELECT p FROM Panier p WHERE p.quantiteProduit = :quantiteProduit")})
public class Panier implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_panier")
    private Integer idPanier;
    @Column(name = "quantite_produit")
    private Integer quantiteProduit;
    @JoinColumn(name = "id_produit", referencedColumnName = "id_produit")
    @ManyToOne(optional = false)
    private Produit idProduit;
    @JoinColumn(name = "id_user", referencedColumnName = "id_utilisateur")
    @ManyToOne(optional = false)
    private Utilisateur idUser;

    public Panier() {
    }

    public Panier(Integer idPanier) {
        this.idPanier = idPanier;
    }

    public Integer getIdPanier() {
        return idPanier;
    }

    public void setIdPanier(Integer idPanier) {
        this.idPanier = idPanier;
    }

    public Integer getQuantiteProduit() {
        return quantiteProduit;
    }

    public void setQuantiteProduit(Integer quantiteProduit) {
        this.quantiteProduit = quantiteProduit;
    }

    public Produit getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Produit idProduit) {
        this.idProduit = idProduit;
    }

    public Utilisateur getIdUser() {
        return idUser;
    }

    public void setIdUser(Utilisateur idUser) {
        this.idUser = idUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPanier != null ? idPanier.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Panier)) {
            return false;
        }
        Panier other = (Panier) object;
        if ((this.idPanier == null && other.idPanier != null) || (this.idPanier != null && !this.idPanier.equals(other.idPanier))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Panier[ idPanier=" + idPanier + " ]";
    }

}
