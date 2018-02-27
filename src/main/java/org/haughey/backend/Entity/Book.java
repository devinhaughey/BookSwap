package org.haughey.backend.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;

/**
 * Class to generate Book objects of returned data.
 *
 * @author dhaugh
 */

@Entity
public class Book {

    private String title;
    private String author;

    private String genre;
    private int isbn;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    public Book() {

    }

    public Book (String title, String author, String genre, int isbn, int id) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.isbn = isbn;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toString()
    {
        return "\nAuthor: " + author + "\nTitle: " + title + "\nGenre: " + genre + "\nISBN: " + isbn;
    }
}
