/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject.jp3.cars_rental;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Mahmoud_abusaqer
 */
//@Entity
//@Table(name = "booking")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "Booking.findAll", query = "SELECT b FROM Booking b"),
//    @NamedQuery(name = "Booking.findById", query = "SELECT b FROM Booking b WHERE b.id = :id"),
//    @NamedQuery(name = "Booking.findByPickupDate", query = "SELECT b FROM Booking b WHERE b.pickupDate = :pickupDate"),
//    @NamedQuery(name = "Booking.findByReturnDate", query = "SELECT b FROM Booking b WHERE b.returnDate = :returnDate"),
//    @NamedQuery(name = "Booking.findByTotalDaysRent", query = "SELECT b FROM Booking b WHERE b.totalDaysRent = :totalDaysRent"),
//    @NamedQuery(name = "Booking.findByTotalPrice", query = "SELECT b FROM Booking b WHERE b.totalPrice = :totalPrice")})
public class Booking implements Serializable {

//    private static final long serialVersionUID = 1L;
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Basic(optional = false)
//    @Column(name = "id")
    private Integer id;
//    @Column(name = "pickup_date")
//    @Temporal(TemporalType.DATE)
    private Date pickupDate;
//    @Column(name = "return_date")
//    @Temporal(TemporalType.DATE)
    private Date returnDate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
//    @Column(name = "total_days_rent")
    private Double totalDaysRent;
//    @Column(name = "total_price")
    private Double totalPrice;
//    @JoinColumn(name = "customer_id", referencedColumnName = "id")
//    @ManyToOne
    private Integer customerId;
//    @JoinColumn(name = "car_id", referencedColumnName = "id")
//    @ManyToOne
    private Integer carId;
//    @JoinColumn(name = "employee_id", referencedColumnName = "id")
//    @ManyToOne
    private Integer employeeId;

    public Booking() {
    }

    public Booking(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(Date pickupDate) {
        this.pickupDate = pickupDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Double getTotalDaysRent() {
        return totalDaysRent;
    }

    public void setTotalDaysRent(Double totalDaysRent) {
        this.totalDaysRent = totalDaysRent;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

//    @Override
//    public int hashCode() {
//        int hash = 0;
//        hash += (id != null ? id.hashCode() : 0);
//        return hash;
//    }
//
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Booking)) {
            return false;
        }
        Booking other = (Booking) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
//
//    @Override
//    public String toString() {
//        return "finalproject.jp3.cars_rental.Booking[ id=" + id + " ]";
//    }
//    
}
