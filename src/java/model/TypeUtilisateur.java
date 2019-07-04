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
@Table(name = "type_utilisateur")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TypeUtilisateur.findAll", query = "SELECT t FROM TypeUtilisateur t")
    , @NamedQuery(name = "TypeUtilisateur.findByIdTypeUtilisateur", query = "SELECT t FROM TypeUtilisateur t WHERE t.idTypeUtilisateur = :idTypeUtilisateur")
    , @NamedQuery(name = "TypeUtilisateur.findByNomTypeUtilisateur", query = "SELECT t FROM TypeUtilisateur t WHERE t.nomTypeUtilisateur = :nomTypeUtilisateur")})
public class TypeUtilisateur implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_type_utilisateur")
    private Integer idTypeUtilisateur;
    @Column(name = "nom_type_utilisateur")
    private String nomTypeUtilisateur;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTypeUtilisateur")
    private List<Utilisateur> utilisateurList;

    public TypeUtilisateur() {
    }

    public TypeUtilisateur(Integer idTypeUtilisateur) {
        this.idTypeUtilisateur = idTypeUtilisateur;
    }

    public Integer getIdTypeUtilisateur() {
        return idTypeUtilisateur;
    }

    public void setIdTypeUtilisateur(Integer idTypeUtilisateur) {
        this.idTypeUtilisateur = idTypeUtilisateur;
    }

    public String getNomTypeUtilisateur() {
        return nomTypeUtilisateur;
    }

    public void setNomTypeUtilisateur(String nomTypeUtilisateur) {
        this.nomTypeUtilisateur = nomTypeUtilisateur;
    }

    @XmlTransient
    public List<Utilisateur> getUtilisateurList() {
        return utilisateurList;
    }

    public void setUtilisateurList(List<Utilisateur> utilisateurList) {
        this.utilisateurList = utilisateurList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTypeUtilisateur != null ? idTypeUtilisateur.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TypeUtilisateur)) {
            return false;
        }
        TypeUtilisateur other = (TypeUtilisateur) object;
        if ((this.idTypeUtilisateur == null && other.idTypeUtilisateur != null) || (this.idTypeUtilisateur != null && !this.idTypeUtilisateur.equals(other.idTypeUtilisateur))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.TypeUtilisateur[ idTypeUtilisateur=" + idTypeUtilisateur + " ]";
    }
    
}
