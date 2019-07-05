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
import javax.persistence.Lob;
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
@Table(name = "utilisateur")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Utilisateur.findAll", query = "SELECT u FROM Utilisateur u")
    , @NamedQuery(name = "Utilisateur.findByMailAndPasswordUtilisateur", query = "SELECT u FROM Utilisateur u WHERE u.mailUtilisateur = :mailUtilisateur AND u.passwordUtilisateur = :passwordUtilisateur")
    , @NamedQuery(name = "Utilisateur.findByIdUtilisateur", query = "SELECT u FROM Utilisateur u WHERE u.idUtilisateur = :idUtilisateur")
    , @NamedQuery(name = "Utilisateur.findByNomUtilisateur", query = "SELECT u FROM Utilisateur u WHERE u.nomUtilisateur = :nomUtilisateur")
    , @NamedQuery(name = "Utilisateur.findByPrenomUtilisateur", query = "SELECT u FROM Utilisateur u WHERE u.prenomUtilisateur = :prenomUtilisateur")
    , @NamedQuery(name = "Utilisateur.findByMailUtilisateur", query = "SELECT u FROM Utilisateur u WHERE u.mailUtilisateur = :mailUtilisateur")
    , @NamedQuery(name = "Utilisateur.findByPasswordUtilisateur", query = "SELECT u FROM Utilisateur u WHERE u.passwordUtilisateur = :passwordUtilisateur")
    , @NamedQuery(name = "Utilisateur.findByAdresseLivraison", query = "SELECT u FROM Utilisateur u WHERE u.adresseLivraison = :adresseLivraison")
    , @NamedQuery(name = "Utilisateur.findByAdresseFacturation", query = "SELECT u FROM Utilisateur u WHERE u.adresseFacturation = :adresseFacturation")
    , @NamedQuery(name = "Utilisateur.findByBlock", query = "SELECT u FROM Utilisateur u WHERE u.block = :block")})
public class Utilisateur implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUser")
    private List<LogConsultation> logConsultationList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_utilisateur")
    private Integer idUtilisateur;
    @Basic(optional = false)
    @Column(name = "nom_utilisateur")
    private String nomUtilisateur;
    @Basic(optional = false)
    @Column(name = "prenom_utilisateur")
    private String prenomUtilisateur;
    @Basic(optional = false)
    @Column(name = "mail_utilisateur")
    private String mailUtilisateur;
    @Basic(optional = false)
    @Column(name = "password_utilisateur")
    private String passwordUtilisateur;
    @Basic(optional = false)
    @Column(name = "adresse_livraison")
    private String adresseLivraison;
    @Basic(optional = false)
    @Column(name = "adresse_facturation")
    private String adresseFacturation;
    @Lob
    @Column(name = "img_utilisateur")
    private String imgUtilisateur;
    @Column(name = "block")
    private Integer block;
    @JoinColumn(name = "id_sb", referencedColumnName = "id_system_bancaire")
    @ManyToOne
    private SystemBancaire idSb;
    @JoinColumn(name = "id_type_utilisateur", referencedColumnName = "id_type_utilisateur")
    @ManyToOne(optional = false)
    private TypeUtilisateur idTypeUtilisateur;

    public Utilisateur() {
    }

    public Utilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public Utilisateur(Integer idUtilisateur, String nomUtilisateur, String prenomUtilisateur, String mailUtilisateur, String passwordUtilisateur, String adresseLivraison, String adresseFacturation) {
        this.idUtilisateur = idUtilisateur;
        this.nomUtilisateur = nomUtilisateur;
        this.prenomUtilisateur = prenomUtilisateur;
        this.mailUtilisateur = mailUtilisateur;
        this.passwordUtilisateur = passwordUtilisateur;
        this.adresseLivraison = adresseLivraison;
        this.adresseFacturation = adresseFacturation;
    }

    public Integer getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    public String getPrenomUtilisateur() {
        return prenomUtilisateur;
    }

    public void setPrenomUtilisateur(String prenomUtilisateur) {
        this.prenomUtilisateur = prenomUtilisateur;
    }

    public String getMailUtilisateur() {
        return mailUtilisateur;
    }

    public void setMailUtilisateur(String mailUtilisateur) {
        this.mailUtilisateur = mailUtilisateur;
    }

    public String getPasswordUtilisateur() {
        return passwordUtilisateur;
    }

    public void setPasswordUtilisateur(String passwordUtilisateur) {
        this.passwordUtilisateur = passwordUtilisateur;
    }

    public String getAdresseLivraison() {
        return adresseLivraison;
    }

    public void setAdresseLivraison(String adresseLivraison) {
        this.adresseLivraison = adresseLivraison;
    }

    public String getAdresseFacturation() {
        return adresseFacturation;
    }

    public void setAdresseFacturation(String adresseFacturation) {
        this.adresseFacturation = adresseFacturation;
    }

    public String getImgUtilisateur() {
        return imgUtilisateur;
    }

    public void setImgUtilisateur(String imgUtilisateur) {
        this.imgUtilisateur = imgUtilisateur;
    }

    public Integer getBlock() {
        return block;
    }

    public void setBlock(Integer block) {
        this.block = block;
    }

    public SystemBancaire getIdSb() {
        return idSb;
    }

    public void setIdSb(SystemBancaire idSb) {
        this.idSb = idSb;
    }

    public TypeUtilisateur getIdTypeUtilisateur() {
        return idTypeUtilisateur;
    }

    public void setIdTypeUtilisateur(TypeUtilisateur idTypeUtilisateur) {
        this.idTypeUtilisateur = idTypeUtilisateur;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUtilisateur != null ? idUtilisateur.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Utilisateur)) {
            return false;
        }
        Utilisateur other = (Utilisateur) object;
        if ((this.idUtilisateur == null && other.idUtilisateur != null) || (this.idUtilisateur != null && !this.idUtilisateur.equals(other.idUtilisateur))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Utilisateur[ idUtilisateur=" + idUtilisateur + " ]";
    }

    @XmlTransient
    public List<LogConsultation> getLogConsultationList() {
        return logConsultationList;
    }

    public void setLogConsultationList(List<LogConsultation> logConsultationList) {
        this.logConsultationList = logConsultationList;
    }

}
