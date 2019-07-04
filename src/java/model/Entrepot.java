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
@Table(name = "entrepot")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Entrepot.findAll", query = "SELECT e FROM Entrepot e")
    , @NamedQuery(name = "Entrepot.findByIdEntrepot", query = "SELECT e FROM Entrepot e WHERE e.idEntrepot = :idEntrepot")
    , @NamedQuery(name = "Entrepot.findByAdresseEntrepot", query = "SELECT e FROM Entrepot e WHERE e.adresseEntrepot = :adresseEntrepot")
    , @NamedQuery(name = "Entrepot.findByCityEntrepot", query = "SELECT e FROM Entrepot e WHERE e.cityEntrepot = :cityEntrepot")
    , @NamedQuery(name = "Entrepot.findByLibelleEntrepot", query = "SELECT e FROM Entrepot e WHERE e.libelleEntrepot = :libelleEntrepot")})
public class Entrepot implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_entrepot")
    private Integer idEntrepot;
    @Basic(optional = false)
    @Column(name = "adresse_entrepot")
    private String adresseEntrepot;
    @Basic(optional = false)
    @Column(name = "city_entrepot")
    private String cityEntrepot;
    @Basic(optional = false)
    @Column(name = "libelle_entrepot")
    private String libelleEntrepot;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEntrepot")
    private List<Stock> stockList;

    public Entrepot() {
    }

    public Entrepot(Integer idEntrepot) {
        this.idEntrepot = idEntrepot;
    }

    public Entrepot(Integer idEntrepot, String adresseEntrepot, String cityEntrepot, String libelleEntrepot) {
        this.idEntrepot = idEntrepot;
        this.adresseEntrepot = adresseEntrepot;
        this.cityEntrepot = cityEntrepot;
        this.libelleEntrepot = libelleEntrepot;
    }

    public Integer getIdEntrepot() {
        return idEntrepot;
    }

    public void setIdEntrepot(Integer idEntrepot) {
        this.idEntrepot = idEntrepot;
    }

    public String getAdresseEntrepot() {
        return adresseEntrepot;
    }

    public void setAdresseEntrepot(String adresseEntrepot) {
        this.adresseEntrepot = adresseEntrepot;
    }

    public String getCityEntrepot() {
        return cityEntrepot;
    }

    public void setCityEntrepot(String cityEntrepot) {
        this.cityEntrepot = cityEntrepot;
    }

    public String getLibelleEntrepot() {
        return libelleEntrepot;
    }

    public void setLibelleEntrepot(String libelleEntrepot) {
        this.libelleEntrepot = libelleEntrepot;
    }

    @XmlTransient
    public List<Stock> getStockList() {
        return stockList;
    }

    public void setStockList(List<Stock> stockList) {
        this.stockList = stockList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEntrepot != null ? idEntrepot.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Entrepot)) {
            return false;
        }
        Entrepot other = (Entrepot) object;
        if ((this.idEntrepot == null && other.idEntrepot != null) || (this.idEntrepot != null && !this.idEntrepot.equals(other.idEntrepot))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Entrepot[ idEntrepot=" + idEntrepot + " ]";
    }
    
}
