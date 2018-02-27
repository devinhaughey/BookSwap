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
import org.haughey.backend.Entity.UserProfile;
import org.haughey.backend.Repository.UserRepository;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

/**
 * Spring RestController to make rest interfaces for the User service.
 *
 * @author dhaugh
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Create
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public UserProfile createUser(@RequestBody UserProfile user) {
        UserProfile createdUser = userRepository.save(user);
        System.out.println("Book created with id = " + createdUser.getId());
        return createdUser;
    }

    /**
     * ReadAll
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Collection<UserProfile> list() {
        System.out.println("Collection of Users requested");
        return userRepository.findAll();
    }

    /**
     * Read
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ResponseBody
    public UserProfile readUser(@PathVariable int id) {
        System.out.println("User requested with id = " + id);
        return this.userRepository.findOne(id);
    }

    /**
     * Update
     */
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<UserProfile> updateUser(@RequestBody UserProfile user, @PathVariable int id) {
        UserProfile updatedUser = userRepository.findOne(id);

        if(updatedUser == null) {
            return ResponseEntity.notFound().build();
        }

        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setPhoneNumber(user.getPhoneNumber());
        UserProfile changedUser = userRepository.save(updatedUser);
        System.out.println("User updated with id = " + updatedUser.getId());
        return ResponseEntity.ok(updatedUser);
    }

    /**
     * Delete
     */
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable int id) {
        UserProfile user = userRepository.findOne(id);
        userRepository.delete(user);
        System.out.println("Delete User with id = " + id);
    }
}