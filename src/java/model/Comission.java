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
@Table(name = "comission")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comission.findAll", query = "SELECT c FROM Comission c")
    , @NamedQuery(name = "Comission.findByIdComission", query = "SELECT c FROM Comission c WHERE c.idComission = :idComission")
    , @NamedQuery(name = "Comission.findByPourcentageComission", query = "SELECT c FROM Comission c WHERE c.pourcentageComission = :pourcentageComission")})
public class Comission implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_comission")
    private Integer idComission;
    @Basic(optional = false)
    @Column(name = "pourcentage_comission")
    private double pourcentageComission;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCommission")
    private List<Vendeur> vendeurList;

    public Comission() {
    }

    public Comission(Integer idComission) {
        this.idComission = idComission;
    }

    public Comission(Integer idComission, double pourcentageComission) {
        this.idComission = idComission;
        this.pourcentageComission = pourcentageComission;
    }

    public Integer getIdComission() {
        return idComission;
    }

    public void setIdComission(Integer idComission) {
        this.idComission = idComission;
    }

    public double getPourcentageComission() {
        return pourcentageComission;
    }

    public void setPourcentageComission(double pourcentageComission) {
        this.pourcentageComission = pourcentageComission;
    }

    @XmlTransient
    public List<Vendeur> getVendeurList() {
        return vendeurList;
    }

    public void setVendeurList(List<Vendeur> vendeurList) {
        this.vendeurList = vendeurList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idComission != null ? idComission.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comission)) {
            return false;
        }
        Comission other = (Comission) object;
        if ((this.idComission == null && other.idComission != null) || (this.idComission != null && !this.idComission.equals(other.idComission))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Comission[ idComission=" + idComission + " ]";
    }
    
}
