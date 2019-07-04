/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Darman
 */
@Entity
@Table(name = "orders")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Orders.findAll", query = "SELECT o FROM Orders o")
    , @NamedQuery(name = "Orders.findById", query = "SELECT o FROM Orders o WHERE o.id = :id")
    , @NamedQuery(name = "Orders.findByOrderDate", query = "SELECT o FROM Orders o WHERE o.orderDate = :orderDate")
    , @NamedQuery(name = "Orders.findByShippedDate", query = "SELECT o FROM Orders o WHERE o.shippedDate = :shippedDate")
    , @NamedQuery(name = "Orders.findByShipName", query = "SELECT o FROM Orders o WHERE o.shipName = :shipName")
    , @NamedQuery(name = "Orders.findByShipCity", query = "SELECT o FROM Orders o WHERE o.shipCity = :shipCity")
    , @NamedQuery(name = "Orders.findByShipStateProvince", query = "SELECT o FROM Orders o WHERE o.shipStateProvince = :shipStateProvince")
    , @NamedQuery(name = "Orders.findByShipZipPostalCode", query = "SELECT o FROM Orders o WHERE o.shipZipPostalCode = :shipZipPostalCode")
    , @NamedQuery(name = "Orders.findByShipCountryRegion", query = "SELECT o FROM Orders o WHERE o.shipCountryRegion = :shipCountryRegion")
    , @NamedQuery(name = "Orders.findByShippingFee", query = "SELECT o FROM Orders o WHERE o.shippingFee = :shippingFee")
    , @NamedQuery(name = "Orders.findByTaxes", query = "SELECT o FROM Orders o WHERE o.taxes = :taxes")
    , @NamedQuery(name = "Orders.findByPaymentType", query = "SELECT o FROM Orders o WHERE o.paymentType = :paymentType")
    , @NamedQuery(name = "Orders.findByPaidDate", query = "SELECT o FROM Orders o WHERE o.paidDate = :paidDate")
    , @NamedQuery(name = "Orders.findByTaxRate", query = "SELECT o FROM Orders o WHERE o.taxRate = :taxRate")})
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "order_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;
    @Column(name = "shipped_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date shippedDate;
    @Column(name = "ship_name")
    private String shipName;
    @Lob
    @Column(name = "ship_address")
    private String shipAddress;
    @Column(name = "ship_city")
    private String shipCity;
    @Column(name = "ship_state_province")
    private String shipStateProvince;
    @Column(name = "ship_zip_postal_code")
    private String shipZipPostalCode;
    @Column(name = "ship_country_region")
    private String shipCountryRegion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "shipping_fee")
    private BigDecimal shippingFee;
    @Column(name = "taxes")
    private BigDecimal taxes;
    @Column(name = "payment_type")
    private String paymentType;
    @Column(name = "paid_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date paidDate;
    @Lob
    @Column(name = "notes")
    private String notes;
    @Column(name = "tax_rate")
    private Double taxRate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderId")
    private List<OrderDetails> orderDetailsList;
    @OneToMany(mappedBy = "orderId")
    private List<Invoices> invoicesList;
    @OneToMany(mappedBy = "customerOrderId")
    private List<InventoryTransactions> inventoryTransactionsList;
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @ManyToOne
    private Customers customerId;
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    @ManyToOne
    private Employees employeeId;
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    @ManyToOne
    private OrdersStatus statusId;
    @JoinColumn(name = "tax_status_id", referencedColumnName = "id")
    @ManyToOne
    private OrdersTaxStatus taxStatusId;
    @JoinColumn(name = "shipper_id", referencedColumnName = "id")
    @ManyToOne
    private Shippers shipperId;

    public Orders() {
    }

    public Orders(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(Date shippedDate) {
        this.shippedDate = shippedDate;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public String getShipAddress() {
        return shipAddress;
    }

    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }

    public String getShipCity() {
        return shipCity;
    }

    public void setShipCity(String shipCity) {
        this.shipCity = shipCity;
    }

    public String getShipStateProvince() {
        return shipStateProvince;
    }

    public void setShipStateProvince(String shipStateProvince) {
        this.shipStateProvince = shipStateProvince;
    }

    public String getShipZipPostalCode() {
        return shipZipPostalCode;
    }

    public void setShipZipPostalCode(String shipZipPostalCode) {
        this.shipZipPostalCode = shipZipPostalCode;
    }

    public String getShipCountryRegion() {
        return shipCountryRegion;
    }

    public void setShipCountryRegion(String shipCountryRegion) {
        this.shipCountryRegion = shipCountryRegion;
    }

    public BigDecimal getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(BigDecimal shippingFee) {
        this.shippingFee = shippingFee;
    }

    public BigDecimal getTaxes() {
        return taxes;
    }

    public void setTaxes(BigDecimal taxes) {
        this.taxes = taxes;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Date getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(Date paidDate) {
        this.paidDate = paidDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }

    @XmlTransient
    public List<OrderDetails> getOrderDetailsList() {
        return orderDetailsList;
    }

    public void setOrderDetailsList(List<OrderDetails> orderDetailsList) {
        this.orderDetailsList = orderDetailsList;
    }

    @XmlTransient
    public List<Invoices> getInvoicesList() {
        return invoicesList;
    }

    public void setInvoicesList(List<Invoices> invoicesList) {
        this.invoicesList = invoicesList;
    }

    @XmlTransient
    public List<InventoryTransactions> getInventoryTransactionsList() {
        return inventoryTransactionsList;
    }

    public void setInventoryTransactionsList(List<InventoryTransactions> inventoryTransactionsList) {
        this.inventoryTransactionsList = inventoryTransactionsList;
    }

    public Customers getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Customers customerId) {
        this.customerId = customerId;
    }

    public Employees getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Employees employeeId) {
        this.employeeId = employeeId;
    }

    public OrdersStatus getStatusId() {
        return statusId;
    }

    public void setStatusId(OrdersStatus statusId) {
        this.statusId = statusId;
    }

    public OrdersTaxStatus getTaxStatusId() {
        return taxStatusId;
    }

    public void setTaxStatusId(OrdersTaxStatus taxStatusId) {
        this.taxStatusId = taxStatusId;
    }

    public Shippers getShipperId() {
        return shipperId;
    }

    public void setShipperId(Shippers shipperId) {
        this.shipperId = shipperId;
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
        if (!(object instanceof Orders)) {
            return false;
        }
        Orders other = (Orders) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Orders[ id=" + id + " ]";
    }
    
}
