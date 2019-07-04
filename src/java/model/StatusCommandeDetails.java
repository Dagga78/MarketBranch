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
@Table(name = "status_commande_details")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StatusCommandeDetails.findAll", query = "SELECT s FROM StatusCommandeDetails s")
    , @NamedQuery(name = "StatusCommandeDetails.findByIdStatusCommandeDetails", query = "SELECT s FROM StatusCommandeDetails s WHERE s.idStatusCommandeDetails = :idStatusCommandeDetails")
    , @NamedQuery(name = "StatusCommandeDetails.findByNomStatusCommandeDetails", query = "SELECT s FROM StatusCommandeDetails s WHERE s.nomStatusCommandeDetails = :nomStatusCommandeDetails")})
public class StatusCommandeDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_status_commande_details")
    private Integer idStatusCommandeDetails;
    @Basic(optional = false)
    @Column(name = "nom_status_commande_details")
    private String nomStatusCommandeDetails;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idStatus")
    private List<CommandeDetails> commandeDetailsList;

    public StatusCommandeDetails() {
    }

    public StatusCommandeDetails(Integer idStatusCommandeDetails) {
        this.idStatusCommandeDetails = idStatusCommandeDetails;
    }

    public StatusCommandeDetails(Integer idStatusCommandeDetails, String nomStatusCommandeDetails) {
        this.idStatusCommandeDetails = idStatusCommandeDetails;
        this.nomStatusCommandeDetails = nomStatusCommandeDetails;
    }

    public Integer getIdStatusCommandeDetails() {
        return idStatusCommandeDetails;
    }

    public void setIdStatusCommandeDetails(Integer idStatusCommandeDetails) {
        this.idStatusCommandeDetails = idStatusCommandeDetails;
    }

    public String getNomStatusCommandeDetails() {
        return nomStatusCommandeDetails;
    }

    public void setNomStatusCommandeDetails(String nomStatusCommandeDetails) {
        this.nomStatusCommandeDetails = nomStatusCommandeDetails;
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
        hash += (idStatusCommandeDetails != null ? idStatusCommandeDetails.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StatusCommandeDetails)) {
            return false;
        }
        StatusCommandeDetails other = (StatusCommandeDetails) object;
        if ((this.idStatusCommandeDetails == null && other.idStatusCommandeDetails != null) || (this.idStatusCommandeDetails != null && !this.idStatusCommandeDetails.equals(other.idStatusCommandeDetails))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.StatusCommandeDetails[ idStatusCommandeDetails=" + idStatusCommandeDetails + " ]";
    }
    
}
