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
//@Table(name = "booked_cars")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "BookedCars.findAll", query = "SELECT b FROM BookedCars b"),
//    @NamedQuery(name = "BookedCars.findById", query = "SELECT b FROM BookedCars b WHERE b.id = :id"),
//    @NamedQuery(name = "BookedCars.findByEndDate", query = "SELECT b FROM BookedCars b WHERE b.endDate = :endDate")})
public class BookedCars implements Serializable {

    private static final long serialVersionUID = 1L;
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Basic(optional = false)
//    @Column(name = "id")
    private Integer id;
//    @Column(name = "end_date")
//    @Temporal(TemporalType.DATE)
    private Date endDate;
//    @JoinColumn(name = "customer_id", referencedColumnName = "id")
//    @ManyToOne
    private Integer customerId;
//    @JoinColumn(name = "car_id", referencedColumnName = "id")
//    @ManyToOne
    private Integer carId;

    public BookedCars() {
    }

    public BookedCars(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
        if (!(object instanceof BookedCars)) {
            return false;
        }
        BookedCars other = (BookedCars) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
//
//    @Override
//    public String toString() {
//        return "finalproject.jp3.cars_rental.BookedCars[ id=" + id + " ]";
//    }
//    
}
