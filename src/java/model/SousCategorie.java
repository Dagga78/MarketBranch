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
import javax.persistence.JoinColumn;
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
@Table(name = "sous_categorie")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SousCategorie.findAll", query = "SELECT s FROM SousCategorie s")
    , @NamedQuery(name = "SousCategorie.findByCategorie", query = "SELECT s FROM SousCategorie s WHERE s.idCategorie = :idCategorie")
    , @NamedQuery(name = "SousCategorie.findByIdSousCategorie", query = "SELECT s FROM SousCategorie s WHERE s.idSousCategorie = :idSousCategorie")
    , @NamedQuery(name = "SousCategorie.findByNomSousCategorie", query = "SELECT s FROM SousCategorie s WHERE s.nomSousCategorie = :nomSousCategorie")})
public class SousCategorie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_sous_categorie")
    private Integer idSousCategorie;
    @Basic(optional = false)
    @Column(name = "nom_sous_categorie")
    private String nomSousCategorie;
    @JoinColumn(name = "id_categorie", referencedColumnName = "id_categorie")
    @ManyToOne(optional = false)
    private Categorie idCategorie;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSousCategorieProduit")
    private List<Produit> produitList;

    public SousCategorie() {
    }

    public SousCategorie(Integer idSousCategorie) {
        this.idSousCategorie = idSousCategorie;
    }

    public SousCategorie(Integer idSousCategorie, String nomSousCategorie) {
        this.idSousCategorie = idSousCategorie;
        this.nomSousCategorie = nomSousCategorie;
    }

    public Integer getIdSousCategorie() {
        return idSousCategorie;
    }

    public void setIdSousCategorie(Integer idSousCategorie) {
        this.idSousCategorie = idSousCategorie;
    }

    public String getNomSousCategorie() {
        return nomSousCategorie;
    }

    public void setNomSousCategorie(String nomSousCategorie) {
        this.nomSousCategorie = nomSousCategorie;
    }

    public Categorie getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(Categorie idCategorie) {
        this.idCategorie = idCategorie;
    }

    @XmlTransient
    public List<Produit> getProduitList() {
        return produitList;
    }

    public void setProduitList(List<Produit> produitList) {
        this.produitList = produitList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSousCategorie != null ? idSousCategorie.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SousCategorie)) {
            return false;
        }
        SousCategorie other = (SousCategorie) object;
        if ((this.idSousCategorie == null && other.idSousCategorie != null) || (this.idSousCategorie != null && !this.idSousCategorie.equals(other.idSousCategorie))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.SousCategorie[ idSousCategorie=" + idSousCategorie + " ]";
    }

}
