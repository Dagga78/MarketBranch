/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Darman
 */
@Entity
@Table(name = "facture")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Facture.findAll", query = "SELECT f FROM Facture f")
    , @NamedQuery(name = "Facture.findByIdFacture", query = "SELECT f FROM Facture f WHERE f.idFacture = :idFacture")
    , @NamedQuery(name = "Facture.findByDateFacture", query = "SELECT f FROM Facture f WHERE f.dateFacture = :dateFacture")
    , @NamedQuery(name = "Facture.findByDateEchance", query = "SELECT f FROM Facture f WHERE f.dateEchance = :dateEchance")
    , @NamedQuery(name = "Facture.findByFraisPort", query = "SELECT f FROM Facture f WHERE f.fraisPort = :fraisPort")
    , @NamedQuery(name = "Facture.findByPrixTotal", query = "SELECT f FROM Facture f WHERE f.prixTotal = :prixTotal")})
public class Facture implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_facture")
    private Integer idFacture;
    @Basic(optional = false)
    @Column(name = "date_facture")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateFacture;
    @Basic(optional = false)
    @Column(name = "date_echance")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEchance;
    @Basic(optional = false)
    @Column(name = "frais_port")
    private double fraisPort;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "prix_total")
    private Double prixTotal;
    @JoinColumn(name = "id_commande", referencedColumnName = "id_commande")
    @ManyToOne(optional = false)
    private Commande idCommande;

    public Facture() {
    }

    public Facture(Integer idFacture) {
        this.idFacture = idFacture;
    }

    public Facture(Integer idFacture, Date dateFacture, Date dateEchance, double fraisPort) {
        this.idFacture = idFacture;
        this.dateFacture = dateFacture;
        this.dateEchance = dateEchance;
        this.fraisPort = fraisPort;
    }

    public Integer getIdFacture() {
        return idFacture;
    }

    public void setIdFacture(Integer idFacture) {
        this.idFacture = idFacture;
    }

    public Date getDateFacture() {
        return dateFacture;
    }

    public void setDateFacture(Date dateFacture) {
        this.dateFacture = dateFacture;
    }

    public Date getDateEchance() {
        return dateEchance;
    }

    public void setDateEchance(Date dateEchance) {
        this.dateEchance = dateEchance;
    }

    public double getFraisPort() {
        return fraisPort;
    }

    public void setFraisPort(double fraisPort) {
        this.fraisPort = fraisPort;
    }

    public Double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(Double prixTotal) {
        this.prixTotal = prixTotal;
    }

    public Commande getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(Commande idCommande) {
        this.idCommande = idCommande;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFacture != null ? idFacture.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Facture)) {
            return false;
        }
        Facture other = (Facture) object;
        if ((this.idFacture == null && other.idFacture != null) || (this.idFacture != null && !this.idFacture.equals(other.idFacture))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Facture[ idFacture=" + idFacture + " ]";
    }
    
}
