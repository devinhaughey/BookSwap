package org.haughey.backend.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Class to generate Address objects of returned data.
 *
 * @author dhaugh
 */
@Entity
public class Address {

    private UserProfile user;

    private int id;
    private String street;
    private String city;
    private String state;
    private String postalCode;

    public Address() {

    }

    public Address(int id, String street, String city, String state, String postalCode) {
        this.id = id;
        this.street = street;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
    }


    @OneToOne
    @PrimaryKeyJoinColumn
    @JsonIgnore
    public UserProfile getUser() {
        return user;
    }

    public void setUser(UserProfile user) {
        this.user = user;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}