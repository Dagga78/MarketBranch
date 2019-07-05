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
@Table(name = "log_consultation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LogConsultation.findAll", query = "SELECT l FROM LogConsultation l")
    , @NamedQuery(name = "LogConsultation.findByIdLogConsultation", query = "SELECT l FROM LogConsultation l WHERE l.idLogConsultation = :idLogConsultation")})
public class LogConsultation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_log_consultation")
    private Integer idLogConsultation;
    @JoinColumn(name = "id_user", referencedColumnName = "id_utilisateur")
    @ManyToOne(optional = false)
    private Utilisateur idUser;
    @JoinColumn(name = "id_produit", referencedColumnName = "id_produit")
    @ManyToOne(optional = false)
    private Produit idProduit;

    public LogConsultation() {
    }

    public LogConsultation(Integer idLogConsultation) {
        this.idLogConsultation = idLogConsultation;
    }

    public Integer getIdLogConsultation() {
        return idLogConsultation;
    }

    public void setIdLogConsultation(Integer idLogConsultation) {
        this.idLogConsultation = idLogConsultation;
    }

    public Utilisateur getIdUser() {
        return idUser;
    }

    public void setIdUser(Utilisateur idUser) {
        this.idUser = idUser;
    }

    public Produit getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Produit idProduit) {
        this.idProduit = idProduit;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLogConsultation != null ? idLogConsultation.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LogConsultation)) {
            return false;
        }
        LogConsultation other = (LogConsultation) object;
        if ((this.idLogConsultation == null && other.idLogConsultation != null) || (this.idLogConsultation != null && !this.idLogConsultation.equals(other.idLogConsultation))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.LogConsultation[ idLogConsultation=" + idLogConsultation + " ]";
    }
    
}
