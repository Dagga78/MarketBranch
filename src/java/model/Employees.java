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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
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
@Table(name = "employees")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Employees.findAll", query = "SELECT e FROM Employees e")
    , @NamedQuery(name = "Employees.findById", query = "SELECT e FROM Employees e WHERE e.id = :id")
    , @NamedQuery(name = "Employees.findByCompany", query = "SELECT e FROM Employees e WHERE e.company = :company")
    , @NamedQuery(name = "Employees.findByLastName", query = "SELECT e FROM Employees e WHERE e.lastName = :lastName")
    , @NamedQuery(name = "Employees.findByFirstName", query = "SELECT e FROM Employees e WHERE e.firstName = :firstName")
    , @NamedQuery(name = "Employees.findByEmailAddress", query = "SELECT e FROM Employees e WHERE e.emailAddress = :emailAddress")
    , @NamedQuery(name = "Employees.findByJobTitle", query = "SELECT e FROM Employees e WHERE e.jobTitle = :jobTitle")
    , @NamedQuery(name = "Employees.findByBusinessPhone", query = "SELECT e FROM Employees e WHERE e.businessPhone = :businessPhone")
    , @NamedQuery(name = "Employees.findByHomePhone", query = "SELECT e FROM Employees e WHERE e.homePhone = :homePhone")
    , @NamedQuery(name = "Employees.findByMobilePhone", query = "SELECT e FROM Employees e WHERE e.mobilePhone = :mobilePhone")
    , @NamedQuery(name = "Employees.findByFaxNumber", query = "SELECT e FROM Employees e WHERE e.faxNumber = :faxNumber")
    , @NamedQuery(name = "Employees.findByCity", query = "SELECT e FROM Employees e WHERE e.city = :city")
    , @NamedQuery(name = "Employees.findByStateProvince", query = "SELECT e FROM Employees e WHERE e.stateProvince = :stateProvince")
    , @NamedQuery(name = "Employees.findByZipPostalCode", query = "SELECT e FROM Employees e WHERE e.zipPostalCode = :zipPostalCode")
    , @NamedQuery(name = "Employees.findByCountryRegion", query = "SELECT e FROM Employees e WHERE e.countryRegion = :countryRegion")})
public class Employees implements Serializable {

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
    @JoinTable(name = "employee_privileges", joinColumns = {
        @JoinColumn(name = "employee_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "privilege_id", referencedColumnName = "id")})
    @ManyToMany
    private List<Privileges> privilegesList;
    @OneToMany(mappedBy = "createdBy")
    private List<PurchaseOrders> purchaseOrdersList;
    @OneToMany(mappedBy = "employeeId")
    private List<Orders> ordersList;

    public Employees() {
    }

    public Employees(Integer id) {
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
    public List<Privileges> getPrivilegesList() {
        return privilegesList;
    }

    public void setPrivilegesList(List<Privileges> privilegesList) {
        this.privilegesList = privilegesList;
    }

    @XmlTransient
    public List<PurchaseOrders> getPurchaseOrdersList() {
        return purchaseOrdersList;
    }

    public void setPurchaseOrdersList(List<PurchaseOrders> purchaseOrdersList) {
        this.purchaseOrdersList = purchaseOrdersList;
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
        if (!(object instanceof Employees)) {
            return false;
        }
        Employees other = (Employees) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Employees[ id=" + id + " ]";
    }
    
}
