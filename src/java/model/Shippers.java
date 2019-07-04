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
import javax.persistence.Lob;
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
@Table(name = "shippers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Shippers.findAll", query = "SELECT s FROM Shippers s")
    , @NamedQuery(name = "Shippers.findById", query = "SELECT s FROM Shippers s WHERE s.id = :id")
    , @NamedQuery(name = "Shippers.findByCompany", query = "SELECT s FROM Shippers s WHERE s.company = :company")
    , @NamedQuery(name = "Shippers.findByLastName", query = "SELECT s FROM Shippers s WHERE s.lastName = :lastName")
    , @NamedQuery(name = "Shippers.findByFirstName", query = "SELECT s FROM Shippers s WHERE s.firstName = :firstName")
    , @NamedQuery(name = "Shippers.findByEmailAddress", query = "SELECT s FROM Shippers s WHERE s.emailAddress = :emailAddress")
    , @NamedQuery(name = "Shippers.findByJobTitle", query = "SELECT s FROM Shippers s WHERE s.jobTitle = :jobTitle")
    , @NamedQuery(name = "Shippers.findByBusinessPhone", query = "SELECT s FROM Shippers s WHERE s.businessPhone = :businessPhone")
    , @NamedQuery(name = "Shippers.findByHomePhone", query = "SELECT s FROM Shippers s WHERE s.homePhone = :homePhone")
    , @NamedQuery(name = "Shippers.findByMobilePhone", query = "SELECT s FROM Shippers s WHERE s.mobilePhone = :mobilePhone")
    , @NamedQuery(name = "Shippers.findByFaxNumber", query = "SELECT s FROM Shippers s WHERE s.faxNumber = :faxNumber")
    , @NamedQuery(name = "Shippers.findByCity", query = "SELECT s FROM Shippers s WHERE s.city = :city")
    , @NamedQuery(name = "Shippers.findByStateProvince", query = "SELECT s FROM Shippers s WHERE s.stateProvince = :stateProvince")
    , @NamedQuery(name = "Shippers.findByZipPostalCode", query = "SELECT s FROM Shippers s WHERE s.zipPostalCode = :zipPostalCode")
    , @NamedQuery(name = "Shippers.findByCountryRegion", query = "SELECT s FROM Shippers s WHERE s.countryRegion = :countryRegion")})
public class Shippers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "company")
    private String company;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "email_address")
    private String emailAddress;
    @Column(name = "job_title")
    private String jobTitle;
    @Column(name = "business_phone")
    private String businessPhone;
    @Column(name = "home_phone")
    private String homePhone;
    @Column(name = "mobile_phone")
    private String mobilePhone;
    @Column(name = "fax_number")
    private String faxNumber;
    @Lob
    @Column(name = "address")
    private String address;
    @Column(name = "city")
    private String city;
    @Column(name = "state_province")
    private String stateProvince;
    @Column(name = "zip_postal_code")
    private String zipPostalCode;
    @Column(name = "country_region")
    private String countryRegion;
    @Lob
    @Column(name = "web_page")
    private String webPage;
    @Lob
    @Column(name = "notes")
    private String notes;
    @Lob
    @Column(name = "attachments")
    private byte[] attachments;
    @OneToMany(mappedBy = "shipperId")
    private List<Orders> ordersList;

    public Shippers() {
    }

    public Shippers(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getBusinessPhone() {
        return businessPhone;
    }

    public void setBusinessPhone(String businessPhone) {
        this.businessPhone = businessPhone;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateProvince() {
        return stateProvince;
    }

    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }

    public String getZipPostalCode() {
        return zipPostalCode;
    }

    public void setZipPostalCode(String zipPostalCode) {
        this.zipPostalCode = zipPostalCode;
    }

    public String getCountryRegion() {
        return countryRegion;
    }

    public void setCountryRegion(String countryRegion) {
        this.countryRegion = countryRegion;
    }

    public String getWebPage() {
        return webPage;
    }

    public void setWebPage(String webPage) {
        this.webPage = webPage;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public byte[] getAttachments() {
        return attachments;
    }

    public void setAttachments(byte[] attachments) {
        this.attachments = attachments;
    }

    @XmlTransient
    public List<Orders> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<Orders> ordersList) {
        this.ordersList = ordersList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Shippers)) {
            return false;
        }
        Shippers other = (Shippers) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Shippers[ id=" + id + " ]";
    }
    
}
