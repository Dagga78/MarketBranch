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
import javax.persistence.Lob;
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
@Table(name = "commande_details")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CommandeDetails.findAll", query = "SELECT c FROM CommandeDetails c")
    , @NamedQuery(name = "CommandeDetails.findByIdCommande", query = "SELECT c FROM CommandeDetails c WHERE c.idCommande = :idCommande")
    , @NamedQuery(name = "CommandeDetails.findByIdCommandeDetails", query = "SELECT c FROM CommandeDetails c WHERE c.idCommandeDetails = :idCommandeDetails")
    , @NamedQuery(name = "CommandeDetails.findByQuantiteProduit", query = "SELECT c FROM CommandeDetails c WHERE c.quantiteProduit = :quantiteProduit")})
public class CommandeDetails implements Serializable {

    @JoinColumn(name = "id_commande", referencedColumnName = "id_commande")
    @ManyToOne
    private Commande idCommande;
    @JoinColumn(name = "id_produit", referencedColumnName = "id_produit")
    @ManyToOne(optional = false)
    private Produit idProduit;
    @JoinColumn(name = "id_status", referencedColumnName = "id_status_commande_details")
    @ManyToOne(optional = false)
    private StatusCommandeDetails idStatus;
    @JoinColumn(name = "id_transporteur", referencedColumnName = "id_transporteur")
    @ManyToOne(optional = false)
    private Transporteur idTransporteur;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_commande_details")
    private Integer idCommandeDetails;
    @Column(name = "quantite_produit")
    private Integer quantiteProduit;
    @Lob
    @Column(name = "code_promo")
    private String codePromo;

    public CommandeDetails() {
    }

    public CommandeDetails(Integer idCommandeDetails) {
        this.idCommandeDetails = idCommandeDetails;
    }

    public Integer getIdCommandeDetails() {
        return idCommandeDetails;
    }

    public void setIdCommandeDetails(Integer idCommandeDetails) {
        this.idCommandeDetails = idCommandeDetails;
    }

    public Integer getQuantiteProduit() {
        return quantiteProduit;
    }

    public void setQuantiteProduit(Integer quantiteProduit) {
        this.quantiteProduit = quantiteProduit;
    }

    public String getCodePromo() {
        return codePromo;
    }

    public void setCodePromo(String codePromo) {
        this.codePromo = codePromo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCommandeDetails != null ? idCommandeDetails.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CommandeDetails)) {
            return false;
        }
        CommandeDetails other = (CommandeDetails) object;
        if ((this.idCommandeDetails == null && other.idCommandeDetails != null) || (this.idCommandeDetails != null && !this.idCommandeDetails.equals(other.idCommandeDetails))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.CommandeDetails[ idCommandeDetails=" + idCommandeDetails + " ]";
    }

    public Commande getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(Commande idCommande) {
        this.idCommande = idCommande;
    }

    public Produit getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Produit idProduit) {
        this.idProduit = idProduit;
    }

    public StatusCommandeDetails getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(StatusCommandeDetails idStatus) {
        this.idStatus = idStatus;
    }

    public Transporteur getIdTransporteur() {
        return idTransporteur;
    }

    public void setIdTransporteur(Transporteur idTransporteur) {
        this.idTransporteur = idTransporteur;
    }
    
}
