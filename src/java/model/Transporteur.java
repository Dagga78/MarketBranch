/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "transporteur")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transporteur.findAll", query = "SELECT t FROM Transporteur t")
    , @NamedQuery(name = "Transporteur.findByIdTransporteur", query = "SELECT t FROM Transporteur t WHERE t.idTransporteur = :idTransporteur")
    , @NamedQuery(name = "Transporteur.findByNomTransporteur", query = "SELECT t FROM Transporteur t WHERE t.nomTransporteur = :nomTransporteur")
    , @NamedQuery(name = "Transporteur.findByAdresseTransporteur", query = "SELECT t FROM Transporteur t WHERE t.adresseTransporteur = :adresseTransporteur")
    , @NamedQuery(name = "Transporteur.findByCityTransporteur", query = "SELECT t FROM Transporteur t WHERE t.cityTransporteur = :cityTransporteur")
    , @NamedQuery(name = "Transporteur.findBySiteWebTransporteur", query = "SELECT t FROM Transporteur t WHERE t.siteWebTransporteur = :siteWebTransporteur")})
public class Transporteur implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_transporteur")
    private Integer idTransporteur;
    @Basic(optional = false)
    @Column(name = "nom_transporteur")
    private String nomTransporteur;
    @Basic(optional = false)
    @Column(name = "adresse_transporteur")
    private String adresseTransporteur;
    @Basic(optional = false)
    @Column(name = "city_transporteur")
    private String cityTransporteur;
    @Column(name = "site_web_transporteur")
    private String siteWebTransporteur;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTransporteur")
    private List<CommandeDetails> commandeDetailsList;

    public Transporteur() {
    }

    public Transporteur(Integer idTransporteur) {
        this.idTransporteur = idTransporteur;
    }

    public Transporteur(Integer idTransporteur, String nomTransporteur, String adresseTransporteur, String cityTransporteur) {
        this.idTransporteur = idTransporteur;
        this.nomTransporteur = nomTransporteur;
        this.adresseTransporteur = adresseTransporteur;
        this.cityTransporteur = cityTransporteur;
    }

    public Integer getIdTransporteur() {
        return idTransporteur;
    }

    public void setIdTransporteur(Integer idTransporteur) {
        this.idTransporteur = idTransporteur;
    }

    public String getNomTransporteur() {
        return nomTransporteur;
    }

    public void setNomTransporteur(String nomTransporteur) {
        this.nomTransporteur = nomTransporteur;
    }

    public String getAdresseTransporteur() {
        return adresseTransporteur;
    }

    public void setAdresseTransporteur(String adresseTransporteur) {
        this.adresseTransporteur = adresseTransporteur;
    }

    public String getCityTransporteur() {
        return cityTransporteur;
    }

    public void setCityTransporteur(String cityTransporteur) {
        this.cityTransporteur = cityTransporteur;
    }

    public String getSiteWebTransporteur() {
        return siteWebTransporteur;
    }

    public void setSiteWebTransporteur(String siteWebTransporteur) {
        this.siteWebTransporteur = siteWebTransporteur;
    }

    @XmlTransient
    public List<CommandeDetails> getCommandeDetailsList() {
        return commandeDetailsList;
    }

    public void setCommandeDetailsList(List<CommandeDetails> commandeDetailsList) {
        this.commandeDetailsList = commandeDetailsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTransporteur != null ? idTransporteur.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transporteur)) {
            return false;
        }
        Transporteur other = (Transporteur) object;
        if ((this.idTransporteur == null && other.idTransporteur != null) || (this.idTransporteur != null && !this.idTransporteur.equals(other.idTransporteur))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Transporteur[ idTransporteur=" + idTransporteur + " ]";
    }
    
}
