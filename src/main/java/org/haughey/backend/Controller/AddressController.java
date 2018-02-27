package org.haughey.backend.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.haughey.backend.Database.DatabaseConnection;
import org.haughey.backend.Entity.Address;
import org.haughey.backend.Repository.AddressRepository;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

/**
 * Spring RestController to make rest interfaces for the Address service.
 *
 * @author dhaugh
 */
@RestController
@RequestMapping("/addresses")
public class AddressController {

    private final AddressRepository addressRepository;

    @Autowired
    AddressController(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    /**
     * Create
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Address createAddress(@RequestBody Address address) {
        Address createdAddress = addressRepository.save(address);
        System.out.println("Address created with id = " + createdAddress.getId());
        return createdAddress;
    }

    /**
     * ReadAll
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Collection<Address> list() {
        System.out.println("Collection of Addresses requested");
        return addressRepository.findAll();
    }

    /**
     * Read
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ResponseBody
    public Address readAddress(@PathVariable int id) {
        System.out.println("Address requested with id = " + id);
        return this.addressRepository.findOne(id);
    }

    /**
     * Update
     */
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Address> updateAddress(@RequestBody Address address, @PathVariable int id) {
        Address updatedAddress = addressRepository.findOne(id);

        if(updatedAddress == null) {
            return ResponseEntity.notFound().build();
        }

        updatedAddress.setStreet(address.getStreet());
        updatedAddress.setCity(address.getCity());
        updatedAddress.setState(address.getState());
        updatedAddress.setPostalCode(address.getPostalCode());
        Address changedAddress = addressRepository.save(updatedAddress);
        System.out.println("Address updated with id = " + updatedAddress.getId());
        return ResponseEntity.ok(updatedAddress);
    }

    /**
     * Delete
     */
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteAddress(@PathVariable int id) {
        Address address = addressRepository.findOne(id);
        addressRepository.delete(address);
        System.out.println("Delete Address with id = " + id);
    }
}