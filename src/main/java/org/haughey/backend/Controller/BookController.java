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
import org.haughey.backend.Entity.Book;
import org.haughey.backend.Repository.BookRepository;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

/**
 * Spring RestController to make rest interfaces for the Book service.
 *
 * @author dhaugh
 */
@RestController
@RequestMapping("/books")
public class BookController {

    private final BookRepository bookRepository;

    @Autowired
    BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * Create
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Book create(@RequestBody Book book) {
        Book createdBook = bookRepository.save(book);
        System.out.println("Book created with id = " + createdBook.getId());
        return createdBook;
    }

    /**
     * ReadAll
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Collection<Book> list() {
        System.out.println("Collection of Book requested");
        return bookRepository.findAll();
    }

    /**
     * Read
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ResponseBody
    public Book read(@PathVariable int id) {
        System.out.println("Book requested with id = " + id);
        return this.bookRepository.findOne(id);
    }

    /**
     * Update
     */
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Book> updateBook(@RequestBody Book book, @PathVariable int id) {
        Book updatedBook = bookRepository.findOne(id);
        if(updatedBook == null) {
            return ResponseEntity.notFound().build();
        }
        updatedBook.setTitle(book.getTitle());
        updatedBook.setAuthor(book.getAuthor());
        updatedBook.setGenre(book.getGenre());
        updatedBook.setIsbn(book.getIsbn());
        Book changedBook = bookRepository.save(updatedBook);
        System.out.println("Book updated with id = " + updatedBook.getId());
        return ResponseEntity.ok(updatedBook);
    }

    /**
     * Delete
     */
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable int id) {
        Book book = bookRepository.findOne(id);
        bookRepository.delete(book);
        System.out.println("Delete Book with id = " + id);
    }
}