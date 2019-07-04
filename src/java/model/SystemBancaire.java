/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
@Table(name = "system_bancaire")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SystemBancaire.findAll", query = "SELECT s FROM SystemBancaire s")
    , @NamedQuery(name = "SystemBancaire.findByIdSystemBancaire", query = "SELECT s FROM SystemBancaire s WHERE s.idSystemBancaire = :idSystemBancaire")
    , @NamedQuery(name = "SystemBancaire.findByNumeroCarte", query = "SELECT s FROM SystemBancaire s WHERE s.numeroCarte = :numeroCarte")
    , @NamedQuery(name = "SystemBancaire.findByDateCarte", query = "SELECT s FROM SystemBancaire s WHERE s.dateCarte = :dateCarte")
    , @NamedQuery(name = "SystemBancaire.findByCryptoCarte", query = "SELECT s FROM SystemBancaire s WHERE s.cryptoCarte = :cryptoCarte")})
public class SystemBancaire implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_system_bancaire")
    private Integer idSystemBancaire;
    @Basic(optional = false)
    @Column(name = "numero_carte")
    private String numeroCarte;
    @Basic(optional = false)
    @Column(name = "date_carte")
    private String dateCarte;
    @Basic(optional = false)
    @Column(name = "crypto_carte")
    private String cryptoCarte;
    @OneToMany(mappedBy = "idSb")
    private List<Utilisateur> utilisateurList;

    public SystemBancaire() {
    }

    public SystemBancaire(Integer idSystemBancaire) {
        this.idSystemBancaire = idSystemBancaire;
    }

    public SystemBancaire(Integer idSystemBancaire, String numeroCarte, String dateCarte, String cryptoCarte) {
        this.idSystemBancaire = idSystemBancaire;
        this.numeroCarte = numeroCarte;
        this.dateCarte = dateCarte;
        this.cryptoCarte = cryptoCarte;
    }

    public Integer getIdSystemBancaire() {
        return idSystemBancaire;
    }

    public void setIdSystemBancaire(Integer idSystemBancaire) {
        this.idSystemBancaire = idSystemBancaire;
    }

    public String getNumeroCarte() {
        return numeroCarte;
    }

    public void setNumeroCarte(String numeroCarte) {
        this.numeroCarte = numeroCarte;
    }

    public String getDateCarte() {
        return dateCarte;
    }

    public void setDateCarte(String dateCarte) {
        this.dateCarte = dateCarte;
    }

    public String getCryptoCarte() {
        return cryptoCarte;
    }

    public void setCryptoCarte(String cryptoCarte) {
        this.cryptoCarte = cryptoCarte;
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
        hash += (idSystemBancaire != null ? idSystemBancaire.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SystemBancaire)) {
            return false;
        }
        SystemBancaire other = (SystemBancaire) object;
        if ((this.idSystemBancaire == null && other.idSystemBancaire != null) || (this.idSystemBancaire != null && !this.idSystemBancaire.equals(other.idSystemBancaire))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.SystemBancaire[ idSystemBancaire=" + idSystemBancaire + " ]";
    }
    
}
