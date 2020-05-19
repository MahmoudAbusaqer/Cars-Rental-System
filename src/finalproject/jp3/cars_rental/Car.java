/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject.jp3.cars_rental;

import java.io.Serializable;
import java.math.BigDecimal;
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
//@Table(name = "car")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "Car.findAll", query = "SELECT c FROM Car c"),
//    @NamedQuery(name = "Car.findById", query = "SELECT c FROM Car c WHERE c.id = :id"),
//    @NamedQuery(name = "Car.findByModel", query = "SELECT c FROM Car c WHERE c.model = :model"),
//    @NamedQuery(name = "Car.findByBrand", query = "SELECT c FROM Car c WHERE c.brand = :brand"),
//    @NamedQuery(name = "Car.findByNumDoors", query = "SELECT c FROM Car c WHERE c.numDoors = :numDoors"),
//    @NamedQuery(name = "Car.findByNumSeats", query = "SELECT c FROM Car c WHERE c.numSeats = :numSeats"),
//    @NamedQuery(name = "Car.findByColor", query = "SELECT c FROM Car c WHERE c.color = :color"),
//    @NamedQuery(name = "Car.findBySpeed", query = "SELECT c FROM Car c WHERE c.speed = :speed"),
//    @NamedQuery(name = "Car.findByPricePerDay", query = "SELECT c FROM Car c WHERE c.pricePerDay = :pricePerDay")})
public class Car implements Serializable {

//    private static final long serialVersionUID = 1L;
//    @Id
//    @Basic(optional = false)
//    @Column(name = "id")
    private Integer id;
//    @Column(name = "model")
    private String model;
//    @Column(name = "brand")
    private String brand;
//    @Column(name = "num_doors")
    private Integer numDoors;
//    @Column(name = "num_seats")
    private Integer numSeats;
//    @Column(name = "color")
    private String color;
//    @Column(name = "speed")
    private Integer speed;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
//    @Column(name = "price_per_day")
    private Double pricePerDay;
//    @OneToMany(mappedBy = "carId")
    private String available;
//    private List<Booking> bookingList;
//    @OneToMany(mappedBy = "carId")
//    private List<BookedCars> bookedCarsList;
//    @OneToMany(mappedBy = "carId")
//    private List<AvailableCars> availableCarsList;

    public Car() {
    }

    public Car(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getNumDoors() {
        return numDoors;
    }

    public void setNumDoors(Integer numDoors) {
        this.numDoors = numDoors;
    }

    public Integer getNumSeats() {
        return numSeats;
    }

    public void setNumSeats(Integer numSeats) {
        this.numSeats = numSeats;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(Double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }
//
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
//    @XmlTransient
//    public List<AvailableCars> getAvailableCarsList() {
//        return availableCarsList;
//    }
//
//    public void setAvailableCarsList(List<AvailableCars> availableCarsList) {
//        this.availableCarsList = availableCarsList;
//    }

//    @Override
//    public int hashCode() {
//        int hash = 0;
//        hash += (id != null ? id.hashCode() : 0);
//        return hash;
//    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Car)) {
            return false;
        }
        Car other = (Car) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

//    @Override
//    public String toString() {
//        return "finalproject.jp3.cars_rental.Car[ id=" + id + " ]";
//    }
//    
}
