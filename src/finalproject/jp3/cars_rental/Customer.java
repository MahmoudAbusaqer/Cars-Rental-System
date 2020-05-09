/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject.jp3.cars_rental;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Mahmoud_abusaqer
 */
//@Entity
//@Table(name = "customer")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c"),
//    @NamedQuery(name = "Customer.findById", query = "SELECT c FROM Customer c WHERE c.id = :id"),
//    @NamedQuery(name = "Customer.findByFirstName", query = "SELECT c FROM Customer c WHERE c.firstName = :firstName"),
//    @NamedQuery(name = "Customer.findByLastName", query = "SELECT c FROM Customer c WHERE c.lastName = :lastName"),
//    @NamedQuery(name = "Customer.findByAddress", query = "SELECT c FROM Customer c WHERE c.address = :address"),
//    @NamedQuery(name = "Customer.findByPhone", query = "SELECT c FROM Customer c WHERE c.phone = :phone"),
//    @NamedQuery(name = "Customer.findBySpeed", query = "SELECT c FROM Customer c WHERE c.speed = :speed"),
//    @NamedQuery(name = "Customer.findByGender", query = "SELECT c FROM Customer c WHERE c.gender = :gender")})
public class Customer implements Serializable {

//    private static final long serialVersionUID = 1L;
//    @Id
//    @Basic(optional = false)
//    @Column(name = "id")
    private Integer id;
//    @Column(name = "first_name")
    private String firstName;
//    @Column(name = "last_name")
    private String lastName;
//    @Column(name = "address")
    private String address;
//    @Column(name = "phone")
    private String phone;
//    @Column(name = "speed")
    private Integer speed;
//    @Column(name = "gender")
    private String gender;
//    @OneToMany(mappedBy = "customerId")
//    private List<Booking> bookingList;
//    @OneToMany(mappedBy = "customerId")
//    private List<BookedCars> bookedCarsList;

    public Customer() {
    }

    public Customer(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

//    @XmlTransient
//    public List<Booking> getBookingList() {
//        return bookingList;
//    }
//
//    public void setBookingList(List<Booking> bookingList) {
//        this.bookingList = bookingList;
//    }
//
//    @XmlTransient
//    public List<BookedCars> getBookedCarsList() {
//        return bookedCarsList;
//    }
//
//    public void setBookedCarsList(List<BookedCars> bookedCarsList) {
//        this.bookedCarsList = bookedCarsList;
//    }
//
//    @Override
//    public int hashCode() {
//        int hash = 0;
//        hash += (id != null ? id.hashCode() : 0);
//        return hash;
//    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Customer)) {
            return false;
        }
        Customer other = (Customer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

//    @Override
//    public String toString() {
//        return "finalproject.jp3.cars_rental.Customer[ id=" + id + " ]";
//    }
    
}
