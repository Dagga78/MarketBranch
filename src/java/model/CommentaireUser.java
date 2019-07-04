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
@Table(name = "commentaire_user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CommentaireUser.findAll", query = "SELECT c FROM CommentaireUser c")
    , @NamedQuery(name = "CommentaireUser.findByIdCommentaireUser", query = "SELECT c FROM CommentaireUser c WHERE c.idCommentaireUser = :idCommentaireUser")
    , @NamedQuery(name = "CommentaireUser.findByNomCommentaireUser", query = "SELECT c FROM CommentaireUser c WHERE c.nomCommentaireUser = :nomCommentaireUser")})
public class CommentaireUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_commentaire_user")
    private Integer idCommentaireUser;
    @Basic(optional = false)
    @Column(name = "nom_commentaire_user")
    private String nomCommentaireUser;
    @JoinColumn(name = "id_produit", referencedColumnName = "id_produit")
    @ManyToOne
    private Produit idProduit;

    public CommentaireUser() {
    }

    public CommentaireUser(Integer idCommentaireUser) {
        this.idCommentaireUser = idCommentaireUser;
    }

    public CommentaireUser(Integer idCommentaireUser, String nomCommentaireUser) {
        this.idCommentaireUser = idCommentaireUser;
        this.nomCommentaireUser = nomCommentaireUser;
    }

    public Integer getIdCommentaireUser() {
        return idCommentaireUser;
    }

    public void setIdCommentaireUser(Integer idCommentaireUser) {
        this.idCommentaireUser = idCommentaireUser;
    }

    public String getNomCommentaireUser() {
        return nomCommentaireUser;
    }

    public void setNomCommentaireUser(String nomCommentaireUser) {
        this.nomCommentaireUser = nomCommentaireUser;
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
        hash += (idCommentaireUser != null ? idCommentaireUser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CommentaireUser)) {
            return false;
        }
        CommentaireUser other = (CommentaireUser) object;
        if ((this.idCommentaireUser == null && other.idCommentaireUser != null) || (this.idCommentaireUser != null && !this.idCommentaireUser.equals(other.idCommentaireUser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.CommentaireUser[ idCommentaireUser=" + idCommentaireUser + " ]";
    }
    
}
